package com.web.config;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.web.dao.ProductDao;
import com.web.dao.impl.ProductDaoImpl;
import com.web.entity.Product;

public class Main {
public static void main(String[] args)
{
	ProductDaoImpl productDaoImpl = new ProductDaoImpl();
	
	/*List<Product> products = productDaoImpl.findProductByName("Stefan");
	System.out.println(products.size());*/
	
	Product product = productDaoImpl.findProductById(1);
	System.out.println(product.getProductName());
}
}
