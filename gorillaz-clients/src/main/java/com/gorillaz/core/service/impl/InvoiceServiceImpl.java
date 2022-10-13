package com.gorillaz.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorillaz.core.model.entity.Invoice;
import com.gorillaz.core.repository.InvoiceDAO;
import com.gorillaz.core.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	
	@Autowired
	InvoiceDAO invoiceDAO;

	@Override
	public Invoice createInvoice(Invoice invoice) {
		return invoiceDAO.save(invoice);
	}
	
	@Override
	public Invoice getInvoice(Long id) {
		return null;
	}

	@Override
	public List<Invoice> getInvoices() {
		return null;
	}

	@Override
	public Invoice updateInvoice(Invoice invoice, Long id) {
		return null;
	}

	@Override
	public void deleteInvoice(Long id) {
	}

}
