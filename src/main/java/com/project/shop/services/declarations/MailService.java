package com.project.shop.services.declarations;

import java.util.List;

import com.project.shop.models.Order;
import com.project.shop.models.OrderDTO;
import com.project.shop.models.User;

public interface MailService {
    void send(Order order, User user,List<Order> listOrder);
    
    void sendCode(int code,String title,String email);
}
