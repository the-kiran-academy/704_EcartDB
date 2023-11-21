package com.jbk.dao;

import java.util.List;

import com.jbk.entity.CategoryEntity;
public interface CategoryDao {

	public int addCategory(CategoryEntity category);
	public CategoryEntity getCategoryById(long categoryId);
	public int deleteCategoryById(long categoryId);
	public int updateCategory(CategoryEntity categoryEntity );
	public CategoryEntity getCategoryByName(String categoryName);
	
	public List<CategoryEntity> getAllCategory();
}
