package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.entity.Product;
import com.spring.boot.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@RequestMapping("/addProduct")
	public ModelAndView addProduct(@RequestParam("pId") int pId, @RequestParam("pName") String pName, @RequestParam("pPrice") double pPrice)
	{
		Product prod = new Product();
		prod.setProductId(pId);
		prod.setProductName(pName);
		prod.setProductprice(pPrice); //after completing all the properties, the object 'Product prod= new Product' is ready with value
		productService.addProduct(prod); //invoke Model or Service or Action class where they will handle the business logic and returns the result back to controller then run the next command which is 'return new ModelAndView("SuccessProduct.jsp")
		return new ModelAndView("successProduct.jsp");
	}
}
