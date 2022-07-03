package com.google.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.google.demo.controller.request.ProductRequest;
import com.google.demo.exception.ProductNotFoundException;
import com.google.demo.model.ProductModel;
import com.google.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private StorageService storageService;
	private ProductRepository productRepository;

	ProductServiceImpl(StorageService storageService, ProductRepository productRepository) {
		this.storageService = storageService;
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductModel> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public ProductModel getProductById(long id) {

		Optional<ProductModel> productModel = productRepository.findById(id);
		if (productModel.isPresent()) {
			return productModel.get();
		}
		throw new ProductNotFoundException(id);
	}

	@Override
	public ProductModel createProduct(ProductRequest productRequest) {
		String filename = storageService.store(productRequest.getImage());

		ProductModel data = new ProductModel().setName(productRequest.getName()).setImage(filename)
				.setPrice(productRequest.getPrice()).setStock(productRequest.getStock());
		return productRepository.save(data);
	}

	@Override
	public ProductModel updateProduct(ProductRequest productRequest, long id) {
		String filename = storageService.store(productRequest.getImage());

		Optional<ProductModel> productModel = productRepository.findById(id);
		if (productModel.isPresent()) {
			ProductModel existingProduct = productModel.get();
			if (filename != null) {
				existingProduct.setImage(filename);
			}
			existingProduct.setName(productRequest.getName());
			existingProduct.setPrice(productRequest.getPrice());
			existingProduct.setStock(productRequest.getStock());
			return productRepository.save(existingProduct);
		}
		throw new ProductNotFoundException(id);
	}

	@Override
	public void deleteProduct(long id) {
		try {
			productRepository.deleteById(id);
		} catch (Exception e) {
			throw new ProductNotFoundException(id);
		}
	}

	@Override
	public ProductModel getProductByName(String name) {
		Optional<ProductModel> productModel = productRepository.findTopByName(name);
		if (productModel.isPresent()) {
			return productModel.get();
		}
		throw new ProductNotFoundException(name);
	}

	@Override
	public List<ProductModel> getProductByNameAndStock(String name, int stock) {
		return productRepository.findByNameContainingAndStockGreaterThanOrderByStockDesc(name, stock);

	}

	@Override
	public List<ProductModel> getProductOutOfStock() {
		return productRepository.checkOutOfStock();
	}

	@Override
	public List<ProductModel> getProductByNameAndPrice(String name, int price) {
		return productRepository.searchNameAndPrice(name, price);
	}
	
	
	
}
