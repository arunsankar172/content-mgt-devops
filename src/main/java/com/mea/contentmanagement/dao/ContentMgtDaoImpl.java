package com.mea.contentmanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mea.contentmanagement.domain.Page;
import com.mea.contentmanagement.domain.Project;
import com.mea.contentmanagement.exception.ContentMgtException;

@Repository
@EnableTransactionManagement
public class ContentMgtDaoImpl implements ContentMgtDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger LOG = LogManager.getLogger(ContentMgtDaoImpl.class);

	@Override
	public Page getProject() {
		Page page = new Page();
		long projectId=1;
		try {
			if (projectId != 0) {
				page = entityManager.find(Page.class,null);
			} else {
				page = (Page) entityManager
						.createQuery("select pg from Control c join c.pageId pg")
						.getSingleResult();
			}
		} catch (HibernateException e) {
			throw new ContentMgtException("NO_DATA_FOUND");
		}
		return null;
	}

}
