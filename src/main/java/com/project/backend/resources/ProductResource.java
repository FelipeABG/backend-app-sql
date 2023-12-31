package com.project.backend.resources;

import com.project.backend.entities.Product;
import com.project.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource extends Resource{

    //Attributes
    @Autowired
    private ProductService service;

    //Methods
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product product){
        product = service.insert(product);
        return ResponseEntity.created(getUri(product)).body(product);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update (@PathVariable Long id, @RequestBody Product product){
        product = service.update(id, product);
        return ResponseEntity.ok().body(product);
    }

}
