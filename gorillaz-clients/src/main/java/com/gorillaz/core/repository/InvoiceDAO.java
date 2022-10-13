package com.gorillaz.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gorillaz.core.model.entity.Invoice;

public interface InvoiceDAO extends JpaRepository<Invoice, Long> {

}
