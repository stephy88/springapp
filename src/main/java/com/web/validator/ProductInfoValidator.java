package com.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.dao.ProductDao;
import com.web.entity.Product;
import com.web.model.ProductInfo;

@Component
public class ProductInfoValidator implements Validator{
    
	@Autowired
	private ProductDao productDao;
	
	public boolean supports(Class<?> clazz) {
		return clazz == ProductInfo.class;
	}

	public void validate(Object target, Errors errors) {
		ProductInfo productInfo = (ProductInfo) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");
		
		String productName = productInfo.getName();
		if(productInfo.isNewProduct())
		{
			Product product = productDao.findProductByName(productName);
			if(product != null)
			{
				errors.rejectValue("name", "Duplicate.productForm.name");
			}
		}
	}

}
