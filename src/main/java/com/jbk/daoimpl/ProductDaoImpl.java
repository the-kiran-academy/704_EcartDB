package com.jbk.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.ProductDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.entity.ProductEntity;
import com.jbk.entity.SupplierEntity;
import com.jbk.model.Category;
import com.jbk.service.CategoryService;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addProduct(ProductEntity productEntity) {
		// for left side auto complete >> alt + shift + L
		int status = 0;
		try (Session session = sessionFactory.openSession()) {

			ProductEntity dbProduct = session.get(ProductEntity.class, productEntity.getProductId());

			if (dbProduct == null) {
				session.save(productEntity);
				session.beginTransaction().commit();
				status = 1; // 1 means added
			} else {
				status = 2; // 2 means already exists
			}

		} catch (Exception e) {
			status = 3; // something went wrong
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public ProductEntity getProductById(long productId) {
		ProductEntity dbProduct = null;
		try (Session session = sessionFactory.openSession()) {

			dbProduct = session.get(ProductEntity.class, productId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbProduct;
	}

	@Override
	public List<ProductEntity> getAllProductByCategoryName(long categoryId) {
		List<ProductEntity> list = null;
		try (Session session = sessionFactory.openSession()) {

			Criteria criteria = session.createCriteria(ProductEntity.class);
			criteria.add(Restrictions.eq("category.categoryId", categoryId));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
