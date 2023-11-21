package com.jbk.dao;

import com.jbk.entity.SupplierEntity;

public interface SupplierDao {

	public int addSupplier(SupplierEntity supplierEntity);
	public SupplierEntity getSupplierById(long supplierId);
}
