package com.project.shop.services.declarations;

import com.project.shop.models.Cart;
import com.project.shop.models.CartItem;
import com.project.shop.models.Item;
import com.project.shop.models.Order;

import java.util.List;

public interface ShoppingService {
    void addToCart(CartItem cartItem);

    void createOrder();

    void deleteFromCart(Integer item);

    List<List<Order>> getUserOrders();

    List<List<Order>> getAllOrders();

    Cart getCart();

    void changeOrderStatus(int id);

    String existsInDatabase() throws RuntimeException;

    void cancelOrder(int id);

    boolean isUserDetailsCorrect();
}
