package com.jbk.service;

import com.jbk.model.Supplier;

public interface SupplierService {
	public int addSupplier(Supplier supplier);

	public Supplier getSupplierById(long supplierId);
}
