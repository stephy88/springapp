package com.web.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.web.dao.ProductDao;
import com.web.entity.Product;
import com.web.model.PaginationResult;
import com.web.model.ProductInfo;

@Transactional
public class ProductDaoImpl implements ProductDao{
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	public void save(ProductInfo productInfo) {
		String productName = productInfo.getName();
		boolean isNew = false;
		Product product = null;
		
		if(productName != null)
		{
			product = findProductByName(productName);
		}
		
		if(product == null)
		{
			isNew = true;
			product = new Product();
			if (productInfo.getFileData() != null) {
	            byte[] image = productInfo.getFileData().getBytes();
	            if (image != null && image.length > 0) {
	                product.setImage(image);
	            }
	        }
			product.setProductName(productInfo.getName());
			product.setCreatedDate(new Date());
			product.setPrice(productInfo.getPrice());
		}
		
		/*Product product = new Product();
		product.setProductName(productInfo.getName());
		product.setPrice(productInfo.getPrice());*/
		if(isNew)
		{
			sessionFactory.getCurrentSession().persist(product);
		}
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().persist(product);
	}

	public Product findProductById(Integer productId) {
		Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("productId", productId));
        return (Product) crit.uniqueResult();
	}

	public ProductInfo getProductById(Integer productId) {
		Product product = findProductById(productId);
		
		if(product == null)
		{
			return null;
		}
		
		return new ProductInfo(product.getProductId(), product.getProductName(), product.getPrice());
	}

	public Product findProductByName(String productName) {
		Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("productName", productName));
        
        return (Product) crit.uniqueResult();
	}

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
		// TODO Auto-generated method stub
		return null;
	}

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductInfo.class.getName()
				+ "(p.productId, p.productName, p.price) " + " from "
				+ Product.class.getName() + " p ";
		if(likeName != null && likeName.length() > 0)
		{
			sql += " Where lower(p.productName) like :likeName "; 
		}
		sql += " order by p.createdDate desc ";
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(sql);
		if(likeName != null && likeName.length() > 0)
		{
			query.setParameter("likeName", "%" + likeName.toLowerCase());
		}
		
        return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage)	;
	}

}
