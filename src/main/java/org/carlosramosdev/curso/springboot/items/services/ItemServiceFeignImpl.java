package org.carlosramosdev.curso.springboot.items.services;

import org.carlosramosdev.curso.springboot.items.client.IProductFeignClient;
import org.carlosramosdev.curso.springboot.items.models.Item;
import org.carlosramosdev.curso.springboot.items.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ItemServiceFeignImpl implements IItemService{

    private final IProductFeignClient client;

    public ItemServiceFeignImpl(IProductFeignClient client) {
        this.client = client;
    }

    @Override
    public List<Item> findAll() {
        return client.findAll().stream()
                .map(product -> new Item(product, new Random().nextInt(10 + 1)))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findById(Long id) {
        Product product = client.findById(id);
        if (product != null) {
            return Optional.of(new Item(client.findById(id), new Random().nextInt(10 + 1)));
        }
        return Optional.empty();
    }
}
