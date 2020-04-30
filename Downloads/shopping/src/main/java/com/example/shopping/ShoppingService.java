package com.example.shopping;

import com.example.shopping.constant.Items;
import com.example.shopping.dto.Cart;
import com.example.shopping.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ShoppingService {
    private final Items items;
    private final ServletContext context;
    private final String keyCart = "cart";

    @Autowired
    public ShoppingService(Items items, ServletContext context) {
        this.items = items;
        this.context = context;
    }

    public Cart addItem(int id){
        Cart cart = null;
        Random rand = new Random();

        Item addedItem = items.getItems().stream().filter(value -> value.getId() == id).findFirst().orElseThrow();
        Item selectedItem = Item.builder().id(addedItem.getId()).name(addedItem.getName()).price(addedItem.getPrice()).code(rand.nextInt(100)).build();
        if (context.getAttribute(keyCart) == null) {
            cart = new Cart();
            cart.setItems(new ArrayList<>());
        } else {
            cart = (Cart)context.getAttribute(keyCart);
        }

        cart.getItems().add(selectedItem);
        cart.setTotal(cart.getTotal() + 1);
        cart.setTotalPrice(cart.getTotalPrice() + selectedItem.getPrice());
        context.setAttribute(keyCart, cart);
        return cart;
    }

    public Cart deleteItem(int code){
        Cart cart = null;
        if (context.getAttribute(keyCart) == null) {
            return null;
        } else {
            cart = (Cart)context.getAttribute(keyCart);
        }

        List<Item> items = cart.getItems().stream().filter(value -> value.getCode() != code).collect(Collectors.toList());
        cart.setItems(items);
        cart.setTotalPrice(items.stream().mapToInt(Item::getPrice).sum());
        cart.setTotal(items.size());
        context.setAttribute(keyCart, cart);
        return cart;
    }
}
