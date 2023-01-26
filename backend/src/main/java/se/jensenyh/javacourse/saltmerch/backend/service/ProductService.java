package se.jensenyh.javacourse.saltmerch.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jensenyh.javacourse.saltmerch.backend.model.ColorVariant;
import se.jensenyh.javacourse.saltmerch.backend.model.Product;
import se.jensenyh.javacourse.saltmerch.backend.repository.ProductRepository;

import java.util.List;


//Used a Service Class for separation between Repository and Controller
@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.selectAll();
    }

    public List<Product> getAllFromSpecificCategory(String str) {
        return productRepository.selectAllOfCategory(str);
    }

    public Product getSpecificProduct(int id) {
        return productRepository.getEntireProduct(id);
    }

    public void addProduct(Product product, String category) {
        productRepository.insertProductAndProps(product, category);
    }

    public void updateProductMetadata(int id, Product product) {
        productRepository.updateProductMeta(id, product);
    }

    public void createVariantForSpecificProduct(int id, ColorVariant body) {
        productRepository.addVariant(id, body);
    }

    public void restockSpecificSizeOfVariant(int id, String size, String color, int quantity) {
        productRepository.restockSize(id, size, color, quantity);
    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

    public void deleteVariant(int pid, String color) {
        productRepository.deleteVariant(pid, color);
    }

}
