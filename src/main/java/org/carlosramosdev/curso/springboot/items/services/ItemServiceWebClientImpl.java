package org.carlosramosdev.curso.springboot.items.services;

import org.carlosramosdev.curso.springboot.items.models.Item;
import org.carlosramosdev.curso.springboot.items.models.Product;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;

@Service
public class ItemServiceWebClientImpl implements IItemService {

    private final WebClient.Builder client;
    private final String url = "http://products";

    public ItemServiceWebClientImpl(WebClient.Builder client) {
        this.client = client;
    }

    @Override
    public List<Item> findAll() {
        return this.client.build().get()
                .uri(url).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(Product.class)
                .map(product -> new Item(product, new Random().nextInt(10 + 1)))
                .collectList().block();
    }

    @Override
    public Optional<Item> findById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        try {
            return Optional.ofNullable(client.build().get()
                    .uri(url+"/{id}", params).accept(MediaType.APPLICATION_JSON)
                    .retrieve().bodyToMono(Product.class)
                    .map(product -> new Item(product, new Random().nextInt(10 + 1)))
                    .block());
        } catch (WebClientResponseException e) {
            return Optional.empty();
        }

    }
}
