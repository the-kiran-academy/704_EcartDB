package com.jbk.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public int updateCategory(Category category) {

		return dao.updateCategory(mapper.map(category, CategoryEntity.class));
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		
		CategoryEntity categoryEntity = dao.getCategoryByName(categoryName);
		if(categoryEntity!=null) {
		return	mapper.map(categoryEntity, Category.class);
		}
	
		return null;
	}

	@Override
	public List<Category> getAllCategory() {
		List<CategoryEntity> list = dao.getAllCategory();
//		List<Category> modelList=new ArrayList<Category>();
//		for (CategoryEntity categoryEntity : list) {
//			
//			modelList.add(mapper.map(categoryEntity, Category.class));
//		}
//		
//		return modelList;
		
		return list.stream().map(categoryEntity -> mapper.map(categoryEntity, Category.class))
				.collect(Collectors.toList());
	}

}
