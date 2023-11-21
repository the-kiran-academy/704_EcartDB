package com.jbk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.exceptiions.ResourceAlreadyExistException;
import com.jbk.exceptiions.ResourceNotFoundException;
import com.jbk.model.Category;
import com.jbk.model.Supplier;
import com.jbk.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService service;
	
	@PostMapping("/add-supplier")
	public ResponseEntity<String> addSupplier(@RequestBody @Valid Supplier supplier) {
		
	int status=	service.addSupplier(supplier);
		if(status==1) {
			return new ResponseEntity<String>("Supplier Added", HttpStatus.CREATED);
		}else if(status==2) {
			throw new ResourceAlreadyExistException("Supplier Already Exists With Id ="+supplier.getSupplierId());
		}else {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/get-supplier-by-id/{supplierId}")
	public ResponseEntity<Object> getSupplierById(@PathVariable long supplierId) {
		
		Supplier supplier = service.getSupplierById(supplierId);
		if(supplier!=null) {
			return new ResponseEntity<Object>(supplier,HttpStatus.FOUND);
		}else {
			// throw exception
			throw new ResourceNotFoundException("Supplier Not Exists with Id = "+supplierId);
		}
	}

}
