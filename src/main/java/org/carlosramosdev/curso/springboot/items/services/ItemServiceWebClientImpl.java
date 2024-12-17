package org.carlosramosdev.curso.springboot.items.services;

import org.carlosramosdev.curso.springboot.items.models.Item;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                .retrieve().bodyToFlux(Item.class).collectList().block();
    }

    @Override
    public Optional<Item> findById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return Optional.ofNullable(client.build().get()
                .uri(url+"/{id}", params).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Item.class).block());
    }
}
