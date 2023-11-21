package com.jbk.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jbk.dao.SupplierDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.entity.SupplierEntity;

@Repository
public class SupplierDaoImpl implements SupplierDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addSupplier(SupplierEntity supplierEntity) {
		// for left side auto complete >> alt + shift + L
				int status = 0;
				try (Session session = sessionFactory.openSession()) {

					SupplierEntity dbSupplier = session.get(SupplierEntity.class, supplierEntity.getSupplierId());

					if (dbSupplier == null) {
						session.save(supplierEntity);
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
	public SupplierEntity getSupplierById(long supplierId) {
		SupplierEntity dbSupplier = null;
		try (Session session = sessionFactory.openSession()) {

			dbSupplier = session.get(SupplierEntity.class, supplierId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbSupplier;
	}

}
