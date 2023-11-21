package com.jbk.dao;

import java.util.List;

import com.jbk.entity.ProductEntity;

public interface ProductDao {

	public int addProduct(ProductEntity productEntity);
	
	public ProductEntity getProductById(long productId);
	
	public List<ProductEntity> getAllProductByCategoryName(long categoryId);
}
