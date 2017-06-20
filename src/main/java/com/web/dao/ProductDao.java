package com.web.dao;

import java.util.List;

import com.web.entity.Product;
import com.web.model.PaginationResult;
import com.web.model.ProductInfo;

public interface ProductDao {
	public Product findProductById(Integer productId);
	public Product findProductByName(String productName);
	public ProductInfo getProductById(Integer productId);
	public void save(ProductInfo productInfo);
	public PaginationResult<ProductInfo> queryProducts(int page,
            int maxResult, int maxNavigationPage);
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
            int maxNavigationPage, String likeName);
}
