package com.jbk.serviceimpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.entity.SupplierEntity;
import com.jbk.model.Category;
import com.jbk.model.Charges;
import com.jbk.model.FinalProduct;
import com.jbk.model.Product;
import com.jbk.model.Supplier;
import com.jbk.service.CategoryService;
import com.jbk.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProductDao dao;

	@Override
	public int addProduct(Product product) {

		if(product.getProductId()<=0) {
			String productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
			product.setProductId(Long.parseLong(productId));
		}
		
		ProductEntity productEntity = mapper.map(product, ProductEntity.class);

		return dao.addProduct(productEntity);

	}

	@Override
	public Product getProductById(long productId) {

		ProductEntity productEntity = dao.getProductById(productId);
		Product productModel = null;
		if (productEntity != null) {
			productModel = mapper.map(productEntity, Product.class);
		}

		return productModel;
	}

	@Override
	public FinalProduct getFinalProduct(long productId) {

		Product productModel = getProductById(productId);

		FinalProduct finalProduct = new FinalProduct();

		finalProduct.setProductId(productId);
		finalProduct.setProductName(productModel.getProductName());
		finalProduct.setProductPrice(productModel.getProductPrice());
		finalProduct.setProductQty(productModel.getProductQty());
		finalProduct.setCategory(productModel.getCategory());
		finalProduct.setSupplier(productModel.getSupplier());

		Charges charges = new Charges();

		double gstAmout = productModel.getProductPrice() * productModel.getCategory().getGst() / 100;
		double deliveryCharges = productModel.getCategory().getDeliveryCharge();

		double discountAmount = productModel.getProductPrice() * productModel.getCategory().getDiscount() / 100;

		double finalProductPrice = (productModel.getProductPrice() - discountAmount) + gstAmout + deliveryCharges;

		charges.setGstAmount(gstAmout);
		charges.setDeliveryCharges(deliveryCharges);

		finalProduct.setCharges(charges);
		finalProduct.setDiscountAmount(discountAmount);
		finalProduct.setFinalProductPrice(finalProductPrice);
		return finalProduct;
	}

	@Override
	public List<Product> getAllProductByCategoryName(long categoryId) {
		List<ProductEntity> list = dao.getAllProductByCategoryName(categoryId);

		return list.stream().map(productEntity -> mapper.map(productEntity, Product.class))
				.collect(Collectors.toList());
	}

	public List<Product> readExcelData(String filePath) {
		List<Product> list = new ArrayList<>();
		try {

			Workbook workbook = new XSSFWorkbook(filePath);

			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rows = sheet.rowIterator();
			Product product = null;
			while (rows.hasNext()) {
				Row row = (Row) rows.next();
				int rowNum = row.getRowNum();

				if (rowNum == 0) {
					continue;
				}

				product = new Product();
				String productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
				product.setProductId(Long.parseLong(productId)+rowNum);

				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {
					Cell cell = (Cell) cells.next();

					int column = cell.getColumnIndex();

					switch (column) {
					case 0: {
						product.setProductName(cell.getStringCellValue());
						break;
					}

					case 1: {
						Supplier supplier = new Supplier();
						supplier.setSupplierId((long) cell.getNumericCellValue());
						product.setSupplier(supplier);
						break;
					}

					case 2: {

						Category category = new Category();
						category.setCategoryId((long) cell.getNumericCellValue());
						product.setCategory(category);
						break;
					}

					case 3: {
						product.setProductQty((int) cell.getNumericCellValue());
						break;
					}

					case 4: {
						product.setProductPrice(cell.getNumericCellValue());
					}

					}

				}
				
				
				
				list.add(product);
			}
			
			for (Product prd:list) {
				System.out.println(prd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public String uploadSheet(MultipartFile myFile) {
		String msg = null;
		try {
			String fileName = myFile.getOriginalFilename();
			FileOutputStream fos = new FileOutputStream("src/main/resources/" + fileName);
			byte[] data = myFile.getBytes();
			fos.write(data);

			List<Product>list = readExcelData("src/main/resources/" + fileName);
			
			for (Product product : list) {
				int status = addProduct(product);
		System.out.println(status);
		
		msg="added";
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return msg;
	}

}
