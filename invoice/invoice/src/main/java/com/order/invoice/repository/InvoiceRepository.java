package com.order.invoice.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.order.invoice.entites.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    // public CommonDetailsDto getCommonDetails(String token, List<String> keys)  {
    //     // your DB or API call should be here
    // }

}
