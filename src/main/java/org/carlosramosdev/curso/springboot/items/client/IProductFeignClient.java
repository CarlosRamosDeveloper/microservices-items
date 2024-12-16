package org.carlosramosdev.curso.springboot.items.client;

import org.carlosramosdev.curso.springboot.items.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url="localhost:8001/api/products", name = "products")
public interface IProductFeignClient {

    @GetMapping
    List<Product> list();

    @GetMapping("/{id}")
    Product findById(@PathVariable Long id);
}
