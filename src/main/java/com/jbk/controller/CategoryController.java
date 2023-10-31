package com.jbk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.exceptiions.ResourceAlreadyExistException;
import com.jbk.exceptiions.ResourceNotFoundException;
import com.jbk.model.Category;
import com.jbk.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@PostMapping("/add-category")
	public ResponseEntity<String> addCategory(@RequestBody @Valid Category category) {
		
	int status=	service.addCategory(category);
		if(status==1) {
			return new ResponseEntity<String>("Category Added", HttpStatus.CREATED);
		}else if(status==2) {
			throw new ResourceAlreadyExistException("Category Already Exists With Id ="+category.getCategoryId());
		}else {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/get-category-by-id/{categoryId}")
	public ResponseEntity<Object> getCategoryById(@PathVariable long categoryId) {
		
		Category category = service.getCategoryById(categoryId);
		if(category!=null) {
			return new ResponseEntity<Object>(category,HttpStatus.FOUND);
		}else {
			// throw exception
			throw new ResourceNotFoundException("Category Not Exists with Id = "+categoryId);
		}
	}
	
	@DeleteMapping("/delete-category-by-id")
	public ResponseEntity<String> deleteCategoryById(@RequestParam long categoryId){
		
		int status=service.deleteCategoryById(categoryId);
		if(status==1) {
			return new ResponseEntity<String>("Category Deleted", HttpStatus.MOVED_PERMANENTLY);
		}else if(status==2) {
			throw new ResourceAlreadyExistException("Category Not Exists With Id ="+categoryId);
		}else {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
