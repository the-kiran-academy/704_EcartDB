package com.jbk.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.model.Category;
import com.jbk.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao dao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public int addCategory(Category category) {

		CategoryEntity entity = mapper.map(category, CategoryEntity.class);

		return dao.addCategory(entity);
	}

	@Override
	public Category getCategoryById(long categoryId) {

		CategoryEntity categoryEntity = dao.getCategoryById(categoryId);
		Category categoryModel = null;
		if (categoryEntity != null) {
			categoryModel = mapper.map(categoryEntity, Category.class);
		}

		return categoryModel;
	}

	@Override
	public int deleteCategoryById(long categoryId) {

		return dao.deleteCategoryById(categoryId);
	}

}
