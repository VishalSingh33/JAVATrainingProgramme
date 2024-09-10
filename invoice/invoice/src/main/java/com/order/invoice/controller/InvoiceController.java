package com.order.invoice.controller;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.order.invoice.dto.InvoiceDto;
import com.order.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@CrossOrigin("*")
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping(value = "/generate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> generateInvoicePdf(@RequestBody InvoiceDto invoiceDto)
            throws IOException, DocumentException {

        byte[] pdfBytes = invoiceService.generateInvoicePdf(invoiceDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        // Create a unique PDF file name using the formatted date and time
        String fileName = "invoice" + formattedDateTime + ".pdf";
        // Set Content-Disposition header to include the unique filename
        headers.setContentDispositionFormData("inline", fileName);
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

}