package com.gorillaz.core.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorillaz.core.model.entity.Invoice;
import com.gorillaz.core.model.response.ResponseMessage;
import com.gorillaz.core.service.InvoiceService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/clients/invoices")
@CrossOrigin(origins= {"http://localhost:4200"})
@Api(value = "InvoiceController Api")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@PostMapping
	public ResponseEntity<?> createInvoice(@RequestBody Invoice invoice){
		return new ResponseEntity<>(invoiceService.createInvoice(invoice),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getInvoices(){
		return new ResponseEntity<>(invoiceService.getInvoices(),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getInvoice(@PathVariable Long id){
		Invoice invoice = invoiceService.getInvoice(id);
		if(invoice==null) {
			 return new ResponseEntity<>(new ResponseMessage("Factura con el id ".concat(id.toString()).concat(" no existe."),404),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(invoiceService.getInvoice(id),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateInvoice(@Valid @RequestBody Invoice invoice, @PathVariable Long id){
		return new ResponseEntity<>(invoiceService.updateInvoice(invoice,id),HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
		log.info("Factura DELETED ");
		invoiceService.deleteInvoice(id);
		return new ResponseEntity<>(new ResponseMessage("Factura id ".concat(id.toString()).concat(" fue eliminada con exito."), 200), HttpStatus.OK);
	}
}

