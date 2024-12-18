package org.carlosramosdev.curso.springboot.items.client;

import org.carlosramosdev.curso.springboot.items.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "products")
public interface IProductFeignClient {

    @GetMapping
    List<Product> list();

    @GetMapping("/{id}")
    Product findById(@PathVariable Long id);

    @PostMapping
    Product save(@RequestBody Product product);

    @PutMapping("/{id}")
    Product update(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
