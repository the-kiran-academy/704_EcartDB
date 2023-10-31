package com.jbk.service;

import com.jbk.model.Category;

public interface CategoryService {
	
	public int addCategory(Category category);
	public Category getCategoryById(long categoryId);
	
	public int deleteCategoryById(long categoryId);

}
