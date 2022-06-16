package com.project.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
public class CartItem {
    private Item item;
    private Integer amount;
}
