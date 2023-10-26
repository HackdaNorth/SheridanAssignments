//package com.sliverstyle.SliverStyleWebApp.Controllers;
//
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.sliverstyle.SliverStyleWebApp.DomainObjects.Product;
//import com.sliverstyle.SliverStyleWebApp.UseCaseManagers.ProductManager;
//
//@RestController
//@RequestMapping(value = "/api")
//public class ProductDispatcher {
//	
//	private final ProductManager productManager;
//
//	public ProductDispatcher(ProductManager productManager) {
//		this.productManager = productManager;
//	}
//
//	@GetMapping("/products")
//	public ResponseEntity<List<Product>> getAllProducts(Product product) {
//		System.out.println("Getting all Products. ProductDispatcher");
//		List<Product> listProducts = productManager.getAllProducts();
//		
//		return ResponseEntity.ok(productManager.getAllProducts());
//	}
//
//	@SuppressWarnings("rawtypes")
//	@GetMapping("/find/{productName}")
//	public ResponseEntity getProductByName(@PathVariable String productName) {
//		System.out.println("Getting product by name???? ProductDispatcher ");
//		return ResponseEntity.ok(productManager.getProductByName(productName));
//	}
//	
//
//	public void deleteProduct(Product product) {
//		
//	}

//}

//
//@PostMapping("/add/{productName}{productQuantity}{productRetailPrice}{productModel}{productGrade}{productPrice}")
////PostMapping
//public ResponseEntity<?> addProduct( @RequestBody String productName, int productQuantity, int productRetailPrice, String productModel, String productGrade, int productPrice) {
//	System.out.println("Adding a  Products. PD");
//	productManager.addProduct(productName, productQuantity, productRetailPrice, productModel, productGrade, productPrice);
//	return ResponseEntity.status(HttpStatus.CREATED).build();
//}
//
//@PutMapping("/products/{product}")
//public ResponseEntity<?> updateProduct(Product product) {
//	System.out.println("Updating a  Products. PD");
//	productManager.updateProduct(product);
//	return ResponseEntity.ok().build();
//}
//	

