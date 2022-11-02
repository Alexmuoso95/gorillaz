package com.gorillaz.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorillaz.core.model.entity.Invoice;

public interface InvoiceDAO extends JpaRepository<Invoice, Long> {

	List<Invoice> findAllByClientId(Long clientId);
}
