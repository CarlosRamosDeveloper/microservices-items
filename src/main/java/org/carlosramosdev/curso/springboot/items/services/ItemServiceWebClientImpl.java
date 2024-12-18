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

    public ItemServiceWebClientImpl(WebClient.Builder client) {
        this.client = client;
    }

    @Override
    public List<Item> findAll() {
        return this.client.build().get()
                .accept(MediaType.APPLICATION_JSON)
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
                    .uri("/{id}", params).accept(MediaType.APPLICATION_JSON)
                    .retrieve().bodyToMono(Product.class)
                    .map(product -> new Item(product, new Random().nextInt(10 + 1)))
                    .block());
        } catch (WebClientResponseException e) {
            return Optional.empty();
        }

    }

    @Override
    public Product save(Product product) {
        return client.build().post().contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product).retrieve().bodyToMono(Product.class).block();
    }

    @Override
    public Product update(Product product, Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        client.build().put()
                .uri("/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .retrieve().bodyToMono(Product.class).block();


        return null;
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        client.build()
                .delete().uri("/{id}", params)
                .retrieve().bodyToMono(Void.class).block();
    }
}
