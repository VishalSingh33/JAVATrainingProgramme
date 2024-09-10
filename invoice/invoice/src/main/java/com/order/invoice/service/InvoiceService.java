package com.order.invoice.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.order.invoice.dto.InvoiceDto;
import com.order.invoice.dto.InvoiceItemDto;
import com.order.invoice.entites.Invoice;
import com.order.invoice.entites.InvoiceItem;
import com.order.invoice.entites.User;
import com.order.invoice.exception.InvoiceException;
import com.order.invoice.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@EnableCaching
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public byte[] generateInvoicePdf(InvoiceDto invoiceDto) throws IOException, DocumentException {

        logger.debug("ORDE_INVC - Begin - InvoiceDto:{}" + invoiceDto);

        Invoice invoice = convertToEntity(invoiceDto);
        return createPdfFromInvoice(invoice);
    }

    private Invoice convertToEntity(InvoiceDto invoiceDto) {

        Invoice invoice = new Invoice();
        User user = new User();
        List<InvoiceItem> invoiceItemList = new ArrayList<>();

        // Set general invoice details
        String invoiceId = UUID.randomUUID().toString();
        invoice.setInvoiceId(invoiceId);
        invoice.setOrderNumber(invoiceDto.getOrderNumber());
        invoice.setPanCard(invoiceDto.getPanCard());
        invoice.setGstRegistrationNo(invoiceDto.getGstRegistrationNo());
        invoice.setDeliveryPartner(invoiceDto.getDeliveryPartner());
        // Set user details
        user.setUserName(invoiceDto.getUserName());
        user.setMobile(invoiceDto.getMobile());
        user.setHouseNo(invoiceDto.getHouseNo());
        user.setStreetAddress(invoiceDto.getStreetAddress());
        user.setLandmark(invoiceDto.getLandmark());
        user.setCity(invoiceDto.getCity());
        user.setState(invoiceDto.getState());
        user.setPostalCode(invoiceDto.getPostalCode());
        user.setCountry(invoiceDto.getCountry());
        invoice.setUser(user);
        invoice.setDiscountAmount(invoiceDto.getDiscountAmount());
        invoice.setTotalTaxableAmount(invoiceDto.getTotalTaxableAmount());
        invoice.setTotalTaxAmount(invoiceDto.getTotalTaxAmount());
        invoice.setGrandTotal(invoiceDto.getGrandTotal());
        invoice.setPaymentMode(invoiceDto.getPaymentMode());
        invoice.setInvoiceDate(LocalDateTime.now());

        for (InvoiceItemDto itemDto : invoiceDto.getInvoiceItems()) {
            InvoiceItem item = new InvoiceItem();
            item.setProductId(itemDto.getProductId());
            item.setProductName(itemDto.getProductName());
            item.setMRP(itemDto.getMRP());
            item.setQuantity(itemDto.getQuantity());
            item.setSellingPrice(itemDto.getSellingPrice());
            item.setTotal(itemDto.getTotal());
            item.setDiscount(itemDto.getDiscount());
            item.setTaxableAmount(itemDto.getTaxableAmount());
            item.setCgstRate(itemDto.getCgstRate());
            item.setCgstAmount(itemDto.getCgstAmount());
            item.setSgstRate(itemDto.getSgstRate());
            item.setSgstAmount(itemDto.getSgstAmount());
            item.setTotalAmount(itemDto.getTotalAmount());

            item.setInvoice(invoice); // Associate the item with the invoice
            invoiceItemList.add(item); // Add to the list
        }
        // Set the list of items in the invoice
        invoice.setInvoiceItems(invoiceItemList);
        invoiceRepository.save(invoice);
        return invoice;
    }

    private byte[] createPdfFromInvoice(Invoice invoice) throws DocumentException, IOException {

    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        // Set font styles
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
        Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
        Font redFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.RED);

        // Add company logo
        // Image logo = Image.getInstance("path/to/logo.png");
        // logo.scaleToFit(100, 50);  // Adjust size
        // document.add(logo);

        // Add invoice title
        Paragraph title = new Paragraph("Invoice", boldFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add general invoice details
        document.add(new Paragraph("Invoice Number: " + invoice.getInvoiceId(), regularFont));
        document.add(new Paragraph("Order Number: " + invoice.getOrderNumber(), regularFont));
        document.add(new Paragraph("Delivery Partner: " + invoice.getDeliveryPartner(), regularFont));
        document.add(new Paragraph("PAN Number: " + invoice.getPanCard(), regularFont));
        document.add(new Paragraph("GST Registration No: " + invoice.getGstRegistrationNo(), regularFont));
        
        // Add user details
        document.add(new Paragraph("\nCustomer Details", boldFont));
        document.add(new Paragraph("Customer Name: " + invoice.getUser().getUserName(), regularFont));
        document.add(new Paragraph("Mobile: " + invoice.getUser().getMobile(), regularFont));
        document.add(new Paragraph("Address: " + invoice.getUser().getHouseNo() + ", " +
                invoice.getUser().getStreetAddress() + ", " + invoice.getUser().getLandmark() + ", " +
                invoice.getUser().getCity() + ", " + invoice.getUser().getState() + ", " +
                invoice.getUser().getPostalCode() + ", " + invoice.getUser().getCountry(), regularFont));
        document.add(new Paragraph("Payment Mode: " + invoice.getPaymentMode(), regularFont));
        document.add(new Paragraph("Date Time: " + LocalDateTime.now(), regularFont));

        // Product table setup
        PdfPTable table = new PdfPTable(8);  // 8 columns
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Table headers
        Stream.of("S.No.", "Product ID", "Product Name", "Qty", "Selling Price", "Total", "CGST", "SGST")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, boldFont));
                    table.addCell(header);
                });

        // Adding product details in rows
        int count = 0;
        for (InvoiceItem invo : invoice.getInvoiceItems()) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(++count), regularFont)));
            table.addCell(new PdfPCell(new Phrase(invo.getProductId(), regularFont)));
            table.addCell(new PdfPCell(new Phrase(invo.getProductName(), regularFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(invo.getQuantity()), regularFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(invo.getSellingPrice()), regularFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(invo.getTotal()), regularFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(invo.getCgstAmount()), regularFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(invo.getSgstAmount()), regularFont)));
        }

        document.add(table);

        // Add payment and amount details
        document.add(new Paragraph("Discount: " + invoice.getDiscountAmount(), regularFont));
        document.add(new Paragraph("Total Taxable Amount: " + invoice.getTotalTaxableAmount(), regularFont));
        document.add(new Paragraph("Total Tax Amount: " + invoice.getTotalTaxAmount(), regularFont));
        Paragraph grandTotal = new Paragraph("Grand Total: " + invoice.getGrandTotal(), redFont);
        grandTotal.setAlignment(Element.ALIGN_RIGHT);
        document.add(grandTotal);

        document.close();
        return baos.toByteArray();
    } catch (InvoiceException e) {
        throw new RuntimeException("Error while generating PDF", e);
    }
}


}
