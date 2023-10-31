package com.jbk.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		int status=0;
		try (Session session = sessionFactory.openSession()) {
			
			CategoryEntity categoryEntity =session.get(CategoryEntity.class, categoryId);
			if(categoryEntity!=null) {
				session.delete(categoryEntity);
				session.beginTransaction().commit();
				status=1;
			}else {
				status=2;
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			status=3;
		}
		return status;
	}

}
