package com.google.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.google.demo.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

	// SELECT * FROM Product WHERE name = 'foo' LIMIT 1
	Optional<ProductModel> findTopByName(String name);

	// SELECT * FROM Product WHERE name LIKE '%foo%' AND stock > x order by stock
	// DESC
	List<ProductModel> findByNameContainingAndStockGreaterThanOrderByStockDesc(String name, int stock);

	// JPQL
//	@Query("SELCT p FROM Product p WHERE STOCK = 0")
	@Query(value = "SELECT * FROM PRODUCT_MODEL WHERE STOCK = 0", nativeQuery = true)
	List<ProductModel> checkOutOfStock();

	@Query(value = "SELECT * FROM PRODUCT_MODEL WHERE name LIKE %:product_name% AND price > :price", nativeQuery = true)
	List<ProductModel> searchNameAndPrice(@Param("product_name") String name, int price);
}
