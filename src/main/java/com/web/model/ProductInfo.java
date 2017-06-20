package com.web.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.web.entity.Product;
@XmlRootElement
public class ProductInfo {
	private Integer code;
    private String name;
    private double price;
 
    private boolean newProduct=false;
    
    // Upload file.
    private CommonsMultipartFile fileData;
    
    public ProductInfo()
    {
    	
    }
    
    public ProductInfo(Product product)
    {
    	this.code = product.getProductId();
    	this.name = product.getProductName();
    	this.price = product.getPrice();
    }
    
    public ProductInfo(Integer code, String name, double price)
    {
    	this.code = code;
    	this.name = name;
    	this.price = price;
    }
    
    @XmlElement
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
    
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	@XmlElement
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
    
    
}
