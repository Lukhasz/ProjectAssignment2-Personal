package se.jensenyh.javacourse.saltmerch.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensenyh.javacourse.saltmerch.backend.model.ColorVariant;
import se.jensenyh.javacourse.saltmerch.backend.model.Product;
import se.jensenyh.javacourse.saltmerch.backend.service.ProductService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3010")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    //Opted for a switch for GetMapping({hats, jackets, tshirts, bags, id}) instead of separate endpoints
    @GetMapping("/{variable}")
    public Object getAllFromSpecificCategoryOrID(@PathVariable("variable") String str) {

        switch (str) {
            case "hats", "jackets", "tshirts", "bags":
                return productService.getAllFromSpecificCategory(str);
            default:
                int number = Integer.parseInt(str);
                return productService.getSpecificProduct(number);
        }
    }

    //Opted for a switch for PostMapping({hats, jackets, tshirts, bags}) instead of separate endpoints
    @PostMapping("/{variable}")
    public ResponseEntity<Product> addProduct(@PathVariable("variable") String str,
                                              @RequestBody Product prod) {

        switch (str) {
            case "hats", "jackets", "tshirts", "bags":
                productService.addProduct(prod, str);
                return new ResponseEntity<>(prod, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductMetadata(@PathVariable("id") int id,
                                                         @RequestBody Product body) {
        productService.updateProductMetadata(id, body);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/{id}/variants")
    public ResponseEntity<Product> createVariantForSpecificProduct(@PathVariable("id") int id,
                                                                   @RequestBody ColorVariant body) {
        productService.createVariantForSpecificProduct(id, body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/{id}/variants/stock")
    public ResponseEntity<Product> restockSpecificSizeOfVariant(@PathVariable("id") int id,
                                                                @RequestParam String size,
                                                                String color,
                                                                int quantity) {
        productService.restockSpecificSizeOfVariant(id, size, color, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{productId}/variants")
    public ResponseEntity<Product> deleteVariant(@PathVariable("productId") int pid,
                                                 @RequestParam String color) {
        productService.deleteVariant(pid, color);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
