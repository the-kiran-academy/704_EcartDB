package com.jbk.service;

import java.util.List;

import com.jbk.model.Category;

public interface CategoryService {

	public int addCategory(Category category);

	public Category getCategoryById(long categoryId);

	public int deleteCategoryById(long categoryId);

	public int updateCategory(Category category);
	
	public Category getCategoryByName(String categoryName);
	
	public List<Category> getAllCategory();
}
