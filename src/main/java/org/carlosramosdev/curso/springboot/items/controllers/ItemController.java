package org.carlosramosdev.curso.springboot.items.controllers;

import org.carlosramosdev.curso.springboot.items.models.Item;
import org.carlosramosdev.curso.springboot.items.services.IItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                           @RequestParam(name="token-request", required = false) String tokenRequest) {
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
}
