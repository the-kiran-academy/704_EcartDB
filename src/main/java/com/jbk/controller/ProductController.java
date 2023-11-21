package com.jbk.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.exceptiions.ResourceAlreadyExistException;
import com.jbk.exceptiions.ResourceNotFoundException;
import com.jbk.model.Category;
import com.jbk.model.FinalProduct;
import com.jbk.model.Product;
import com.jbk.service.CategoryService;
import com.jbk.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/add-product")
	public ResponseEntity<String> addProduct(@RequestBody @Valid Product product) {

		int status = service.addProduct(product);
		if (status == 1) {
			return new ResponseEntity<String>("Product Added", HttpStatus.CREATED);
		} else if (status == 2) {
			throw new ResourceAlreadyExistException("Product Already Exists With Id =" + product.getProductId());
		} else {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/get-product-by-id/{productId}")
	public ResponseEntity<Object> getProductById(@PathVariable long productId) {

		Product product = service.getProductById(productId);
		if (product != null) {
			return new ResponseEntity<Object>(product, HttpStatus.FOUND);
		} else {
			// throw exception
			throw new ResourceNotFoundException("Product Not Exists with Id = " + productId);
		}
	}

	@GetMapping("/get-final-product")
	public ResponseEntity<Object> getFinalProduct(@RequestParam long productId) {

		FinalProduct finalProduct = service.getFinalProduct(productId);
		if (finalProduct != null) {
			return new ResponseEntity<Object>(finalProduct, HttpStatus.FOUND);
		} else {
			// throw exception
			throw new ResourceNotFoundException("Product Not Exists with Id = " + productId);
		}
	}

	@GetMapping("get-all-products-by-category-name")
	public ResponseEntity<Object> getAllProductByCategoryName(@RequestParam String categoryName) {

		Category category = categoryService.getCategoryByName(categoryName);

		if (category != null) {
			List<Product> list = service.getAllProductByCategoryName(category.getCategoryId());

			if (!list.isEmpty()) {
				return new ResponseEntity<Object>(list, HttpStatus.FOUND);
			} else {
				throw new ResourceNotFoundException("Product Not Exists with Category Name = " + categoryName);
			}
		} else {
			throw new ResourceNotFoundException("Catrgory Not Exists with Category Name = " + categoryName);
		}

	}

	@PostMapping("/upload-sheet")
	public ResponseEntity<String> uploadSheet(@RequestParam MultipartFile myFile) {

	String msg = service.uploadSheet(myFile);
	
		return new ResponseEntity<String>(msg,HttpStatus.OK);

	}
}
