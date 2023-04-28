package API_Demo.Springboot.Controller;


import API_Demo.Springboot.Models.Product;
import API_Demo.Springboot.Models.ResponseObject;
import API_Demo.Springboot.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    @Autowired
    private ProductRepository repository;
    @GetMapping("/hehe")
    public String homepage()
    {
        return "category";
    }
    @GetMapping("/getAll")
    //this request is: http://localhost:8080/api/v1/Products
    List<Product> getAllProducts(){
        return repository.findAll(); //where is data
    }
    //you must save this to database

    //Get detail product
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id)
    {
        Optional<Product> foundProduct = repository.findById(id);

        return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Query product successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","Cannot find product with id = "+id, "")
                );
    }
    //
    @PostMapping("/insertProduct")
    //Postman: Raw, JSON
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct)
    {
            List<Product> foundProducts = repository.findByProductName(newProduct.getProductName());
            if(foundProducts.size() > 0)
            {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Product name already used", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert product successfully", repository.save(newProduct))
            );
    }

    //upsert = update if found, other insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id)
    {
        Product updatedProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setPrice(newProduct.getPrice());
                    product.setYear(newProduct.getYear());
                    product.setUrl(newProduct.getUrl());

                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Upsert product successfully", updatedProduct)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id)
    {
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find product to delete","")
        );
    }
}
