package org.carlosramosdev.curso.springboot.items.controllers;

import org.carlosramosdev.curso.springboot.items.models.Item;
import org.carlosramosdev.curso.springboot.items.models.Product;
import org.carlosramosdev.curso.springboot.items.services.IItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    private final IItemService service;

    public ItemController(@Qualifier("itemServiceWebClientImpl") IItemService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Item> list(@RequestParam(name="name", required=false) String name,
                           @RequestHeader(name="token-request", required = false) String tokenRequest) {
        if (name != null){

            System.out.println(name);
        }
        if (tokenRequest != null) {
            System.out.println(tokenRequest);
        }
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        Optional<Item> item = service.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        }
        return ResponseEntity.status(404).body(Collections.singletonMap(
                "message","No existe ningún producto asignado a este valor"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product product, @PathVariable long id) {
        return service.update(product, id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }
}
