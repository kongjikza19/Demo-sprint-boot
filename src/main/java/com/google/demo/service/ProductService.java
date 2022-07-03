package com.google.demo.service;

import java.util.List;
import com.google.demo.controller.request.ProductRequest;
import com.google.demo.model.ProductModel;

public interface ProductService {

	List<ProductModel> getAllProducts();

	ProductModel getProductById(long id);

	ProductModel createProduct(ProductRequest productRequest);

	ProductModel updateProduct(ProductRequest productRequest, long id);

	void deleteProduct(long id);

	ProductModel getProductByName(String name);

	List<ProductModel> getProductByNameAndStock(String name, int stock);
	
	List<ProductModel> getProductOutOfStock();
	
	List<ProductModel> getProductByNameAndPrice(String name, int price);

}
