package com.project.shop.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addToCart(CartItem item) {
        items.add(item);
    }

    public void removeFromCart(Integer item) {
        this.items = this.items.stream()
                .filter(e -> e.getItem().getId().intValue() != item)
                .collect(Collectors.toList());
    }
}
