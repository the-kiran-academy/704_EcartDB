package com.jbk.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.model.FinalProduct;
import com.jbk.model.Product;

public interface ProductService {

	public int addProduct(Product product);
	public Product getProductById(long productId);
	
	public FinalProduct getFinalProduct(long productId);
	
	public List<Product> getAllProductByCategoryName(long categoryId);
	
	public String uploadSheet(MultipartFile file);
}
