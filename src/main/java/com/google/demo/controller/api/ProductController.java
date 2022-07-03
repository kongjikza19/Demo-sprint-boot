package com.google.demo.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.demo.controller.request.ProductRequest;
import com.google.demo.exception.ProductNotFoundException;
import com.google.demo.exception.ValidationException;
import com.google.demo.model.ProductModel;
import com.google.demo.repository.ProductRepository;
import com.google.demo.service.ProductService;
import com.google.demo.service.StorageService;
import com.google.demo.util.Dateutils;

import lombok.extern.slf4j.Slf4j;

//@Controller
@RestController
@RequestMapping("/saytest")
//@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = { "https://www.w3schools.com","https://www.w3schoolsDEV.com" })
//@Slf4j
public class ProductController {

	private ProductService productService;

//	@Autowired
//	Dateutils dateutils;

//	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

//	private final AtomicLong couter = new AtomicLong();
//	private List<ProductModel> products = new ArrayList<>();
//	private StorageService storageService;
//	private ProductRepository productRepository;
//
//	ProductController(StorageService storageService, ProductRepository productRepository) {
//		this.storageService = storageService;
//		this.productRepository = productRepository;
//	}

	ProductController(ProductService productService) {
		this.productService = productService;

	}

//	@RequestMapping(path = "/say",method = RequestMethod.GET)
//	@ResponseBody
	@GetMapping("")
//	@CrossOrigin
	public List<ProductModel> say() {
//		log.error("test error");
//		log.warn("test error");
//		return products;
//		return productRepository.findAll();

		return productService.getAllProducts();
	}

//	@RequestMapping(path = "/say",method = RequestMethod.DELETE)
//	@ResponseBody
	@GetMapping("/{id}")
	public ProductModel say1(@PathVariable long id) {
//		return products.stream().filter(result -> result.getId() == id).findFirst()
//				.orElseThrow(() -> new ProductNotFoundException(id));

//		return productRepository.findById(id).stream().filter(result -> result.getId() == id).findFirst()
//				.orElseThrow(() -> new ProductNotFoundException(id));

		return productService.getProductById(id);
	}
//	
////	@RequestMapping(path = "/say",method = RequestMethod.DELETE)
////	@ResponseBody
//	@GetMapping({"/say/{id}/name/{name}","/sayname/{id}"})
//	public String say2(@PathVariable(value = "id") long id11,@PathVariable(value = "name",required = false) String name1) {
//		return name1 + "TestTest" + id11;
//	}
//	
//	@GetMapping({"/print"})
//	public String say3(@RequestParam String name) {
//		return "TestTest" + name;
//	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ProductModel say2(@Valid ProductRequest productRequest /* @RequestBody ProductModel productModel */,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField() + ":" + fieldError.getDefaultMessage());
			});
		}

//		String filename = storageService.store(productRequest.getImage());
////		ProductModel data = new ProductModel(couter.incrementAndGet(), productRequest.getName(), filename,
////				productRequest.getPrice(), productRequest.getStock());
////		productModel.setId(couter.incrementAndGet());
////		products.add(data);
//		
//		ProductModel data = new ProductModel().setName(productRequest.getName()).setImage(filename).setPrice(productRequest.getPrice())
//				.setStock(productRequest.getStock());
//		return productRepository.save(data);

		return productService.createProduct(productRequest);
	}

	@PutMapping("{id}")
	public ProductModel say2(/* @RequestBody ProductModel productModel, */ @Valid ProductRequest productRequest,
			BindingResult bindingResult, @PathVariable long id) {

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(fieldError -> {
				throw new ValidationException(fieldError.getField() + ":" + fieldError.getDefaultMessage());
			});
		}

//		products.stream().filter(result -> result.getId() == id).findFirst().ifPresentOrElse(result -> {
//			result.setImage(productModel.getImage());
//			result.setName(productModel.getName());
//			result.setPrice(productModel.getPrice());
//			result.setStock(productModel.getStock());
//		}, () -> {
//			throw new ProductNotFoundException(id);
//		});

//		productRepository.findById(id).stream().filter(result -> result.getId() == id).findFirst()
//				.ifPresentOrElse(result -> {
//					result.setImage(productModel.getImage());
//					result.setName(productModel.getName());
//					result.setPrice(productModel.getPrice());
//					result.setStock(productModel.getStock());
//					productRepository.save(result);
//				}, () -> {
//					throw new ProductNotFoundException(id);
//				});

		return productService.updateProduct(productRequest, id);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void say3(@PathVariable long id) {
//		products.stream().filter(result -> result.getId() == id).findFirst().ifPresentOrElse(result -> {
//			products.remove(result);
//		}, () -> {
//			throw new ProductNotFoundException(id);
//		});

//		productRepository.findById(id).stream().filter(result -> result.getId() == id).findFirst()
//				.ifPresentOrElse(result -> productRepository.delete(result), () -> {
//					throw new ProductNotFoundException(id);
//				});

		productService.deleteProduct(id);
	}

	@GetMapping(path = "/search", params = "name")
	public ProductModel searchProductByName(@RequestParam String name) {
		return productService.getProductByName(name);
	}

	@GetMapping(path = "/search", params = { "name", "stock" })
	public List<ProductModel> say3(@RequestParam String name, @RequestParam int stock) {
		return productService.getProductByNameAndStock(name, stock);
	}
	
	@GetMapping("/out-of-stock")
	public List<ProductModel> checkOutOfStock() {
		return productService.getProductOutOfStock();
	}

	@GetMapping(path = "/search", params = { "name", "price" })
	public List<ProductModel> searchProductByNameAndPrice(@RequestParam String name, @RequestParam int price) {
		return productService.getProductByNameAndPrice(name, price);
	}

}
