package com.jbk.dao;

import com.jbk.entity.CategoryEntity;

public interface CategoryDao {

	public int addCategory(CategoryEntity category);
	public CategoryEntity getCategoryById(long categoryId);
	public int deleteCategoryById(long categoryId);
}
