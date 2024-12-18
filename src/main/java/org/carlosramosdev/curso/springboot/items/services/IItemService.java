package org.carlosramosdev.curso.springboot.items.services;

import org.carlosramosdev.curso.springboot.items.models.Item;
import org.carlosramosdev.curso.springboot.items.models.Product;

import java.util.List;
import java.util.Optional;

public interface IItemService {
    List<Item> findAll();
    Optional<Item> findById(Long id);
    Product save(Product product);
    Product update(Product product, Long id);
    void deleteById(Long id);
}
