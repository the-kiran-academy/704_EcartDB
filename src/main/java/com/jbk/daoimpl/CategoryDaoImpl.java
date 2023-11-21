package com.jbk.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.CategoryEntity;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addCategory(CategoryEntity category) {
		// for left side auto complete >> alt + shift + L
		int status = 0;
		try (Session session = sessionFactory.openSession()) {

			CategoryEntity dbCategory = session.get(CategoryEntity.class, category.getCategoryId());

			if (dbCategory == null) {
				session.save(category);
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
	public CategoryEntity getCategoryById(long categoryId) {
		CategoryEntity dbCategory = null;
		try (Session session = sessionFactory.openSession()) {

			dbCategory = session.get(CategoryEntity.class, categoryId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbCategory;
	}

	@Override
	public int deleteCategoryById(long categoryId) {
		int status = 0;
		try (Session session = sessionFactory.openSession()) {

			CategoryEntity categoryEntity = session.get(CategoryEntity.class, categoryId);
			if (categoryEntity != null) {
				session.delete(categoryEntity);
				session.beginTransaction().commit();
				status = 1;
			} else {
				status = 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = 3;
		}
		return status;
	}

	@Override
	public int updateCategory(CategoryEntity categoryEntity) {
		int status = 0;
		try (Session session = sessionFactory.openSession()) {

			CategoryEntity dbCategoryEntity = session.get(CategoryEntity.class, categoryEntity.getCategoryId());
			if (dbCategoryEntity != null) {
				session.update(categoryEntity);
				session.beginTransaction().commit();
				status = 1;
			} else {
				status = 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = 3;
		}
		return status;
	}

	@Override
	public CategoryEntity getCategoryByName(String categoryName) {
		CategoryEntity categoryEntity = null;
		try (Session session = sessionFactory.openSession()) {

			// session // crieria //Restriction //Projection // HQL

			Criteria criteria = session.createCriteria(CategoryEntity.class);

			criteria.add(Restrictions.eq("categoryName", categoryName));

			categoryEntity = (CategoryEntity) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoryEntity;
	}

	@Override
	public List<CategoryEntity> getAllCategory() {
		List<CategoryEntity> list = null;
		try (Session session = sessionFactory.openSession()) {

			Criteria criteria = session.createCriteria(CategoryEntity.class);

			criteria.addOrder(Order.desc("categoryName"));

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
