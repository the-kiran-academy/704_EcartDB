package com.jbk.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.entity.SupplierEntity;
import com.jbk.model.Category;
import com.jbk.model.Supplier;
import com.jbk.service.SupplierService;
@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	SupplierDao dao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public int addSupplier(Supplier supplier) {
		SupplierEntity entity = mapper.map(supplier, SupplierEntity.class);
		return dao.addSupplier(entity);
	}

	@Override
	public Supplier getSupplierById(long supplierId) {

		SupplierEntity supplierEntity = dao.getSupplierById(supplierId);
		Supplier supplierModel = null;
		if (supplierEntity != null) {
			supplierModel = mapper.map(supplierEntity, Supplier.class);
		}

		return supplierModel;
	}

}
