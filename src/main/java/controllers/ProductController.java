package controllers;

import api.services.CategoryService;
import api.services.ProductService;
import dto.CategoryDTO;
import dto.ProductDTO;
import model.Category;
import model.Product;

import java.util.Collection;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public ProductDTO add(Product product){
       return productService.create(product);
    }

    public Collection<ProductDTO> getAll(){
        return productService.getAll();
    }

    public void save(){
        productService.save();
    }


}
