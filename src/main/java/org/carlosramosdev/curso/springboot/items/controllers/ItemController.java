package org.carlosramosdev.curso.springboot.items.controllers;

import org.carlosramosdev.curso.springboot.items.models.Item;
import org.carlosramosdev.curso.springboot.items.services.IItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    private final IItemService service;

    public ItemController(@Qualifier("itemServiceWebClientImpl") IItemService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Item> list(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> findById(@PathVariable long id){
        Optional<Item> item = service.findById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
