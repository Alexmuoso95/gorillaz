package com.gorillaz.core.service;

import java.util.List;

import com.gorillaz.core.model.entity.Invoice;

public interface InvoiceService {
	public Invoice getInvoice(Long id);
	public List<Invoice> getInvoices();
	public Invoice createInvoice(Invoice client);
	public Invoice updateInvoice(Invoice client, Long id);
	public void deleteInvoice(Long id);
}
