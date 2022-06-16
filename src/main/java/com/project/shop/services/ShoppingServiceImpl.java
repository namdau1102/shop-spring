package com.project.shop.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.shop.models.*;
import com.project.shop.repositories.*;
import com.project.shop.services.declarations.MailService;
import com.project.shop.services.declarations.ShoppingService;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ShoppingServiceImpl implements ShoppingService {
    private final Cart cart;

    private final OrderRepository orderRepository;

    private final PetRepository petRepository;

    private final AccessoryRepository accessoryRepository;

    private final FeedRepository feedRepository;

    private final OrderTypeRepository orderTypeRepository;

    private final UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    public ShoppingServiceImpl(Cart cart, OrderRepository orderRepository, PetRepository petRepository, AccessoryRepository accessoryRepository, FeedRepository feedRepository, OrderTypeRepository orderTypeRepository, UserRepository userRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.petRepository = petRepository;
        this.accessoryRepository = accessoryRepository;
        this.feedRepository = feedRepository;
        this.orderTypeRepository = orderTypeRepository;
        this.userRepository = userRepository;
    }


    public void addToCart(CartItem item) {
        if (hasDuplicates(item))
            return;
        cart.addToCart(item);
    }


    @Transactional
    @SuppressWarnings("deprecation")
    public void createOrder() {
    	List<Order> listOrder = new ArrayList<Order>();
        int orderId = generateOrderId();
        for (CartItem cartItem : cart.getItems()) {
            Item item = cartItem.getItem();
            item.setAmount(cartItem.getAmount());
            if (item instanceof Pet) {
                Pet soldPet = (Pet) item;
                soldPet.setAmount(1);
                soldPet.setSold(true);
                petRepository.saveAndFlush(soldPet);
            } else if (item instanceof Accessory) {
                Accessory accessory = accessoryRepository.findById(item.getId()).get();
                if (accessory.getAmount() <= item.getAmount())
                    item.setAmount(accessory.getAmount());
                accessory.setAmount(accessory.getAmount() - item.getAmount());
                accessoryRepository.save(accessory);
            } else if (item instanceof Feed) {
                Feed feed = feedRepository.findById(item.getId()).get();
                if (feed.getAmount() <= item.getAmount())
                    item.setAmount(feed.getAmount());
                feed.setAmount(feed.getAmount() - item.getAmount());
                feedRepository.save(feed);
            }
            Order order = new Order();
            order.setAmount(Float.valueOf(item.getAmount()));
            order.setItem(cartItem.getItem());
            order.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            order.setDate(DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.GERMANY).format(LocalDateTime.now()));
            order.setOrderId(orderId);
            order.setOrderType(orderTypeRepository.findOrderTypeByOrderStatus(OrderType.OrderStatus.ORDER_ORDERED));
           
            orderRepository.saveAndFlush(order);
            listOrder.add(order);
            send(order, order.getUser(),listOrder);

            clearCart();
        }
    }

    private void send(Order order, User user,List<Order> listOrder) {
        mailService.send(order, user,listOrder);
    }

    @Override
    public void deleteFromCart(Integer item) {
        cart.removeFromCart(item);
    }

    @Override
    public List<List<Order>> getUserOrders() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Integer> orderList = orderRepository.findAllByUser(user)
                .stream()
                .map(Order::getOrderId)
                .distinct()
                .collect(Collectors.toList());
        return getOrdersById(orderList);
    }

    @Override
    public List<List<Order>> getAllOrders() {
        List<Integer> orderList = orderRepository.findAll()
                .stream()
                .map(Order::getOrderId)
                .distinct()
                .collect(Collectors.toList());

        return getOrdersById(orderList);
    }


    private List<List<Order>> getOrdersById(List<Integer> orderList) {
        return orderList.stream()
                .map(orderRepository::findAllByOrderId)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void changeOrderStatus(int id) {
        List<Order> orders = orderRepository.findAllByOrderId(id);
        for (Order order : orders)
            if (order.getOrderType() == orderTypeRepository.findOrderTypeByOrderStatus(OrderType.OrderStatus.ORDER_ORDERED))
                order.setOrderType(orderTypeRepository.findOrderTypeByOrderStatus(OrderType.OrderStatus.ORDER_COMPLETED));
            else if (order.getOrderType() == orderTypeRepository.findOrderTypeByOrderStatus(OrderType.OrderStatus.ORDER_COMPLETED))
                order.setOrderType(orderTypeRepository.findOrderTypeByOrderStatus(OrderType.OrderStatus.ORDER_ORDERED));

        orderRepository.saveAll(orders);
    }


    @Override
    @Transactional
    public void cancelOrder(int id) {
        List<Order> list = orderRepository.findAllByOrderId(id);
        list.forEach(e -> e.setOrderType(orderTypeRepository.findOrderTypeByOrderStatus(OrderType.OrderStatus.ORDER_CANCELED)));
        orderRepository.saveAll(list);
    }

    @Override
    public boolean isUserDetailsCorrect() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userFromDb = userRepository.findById(currentUser.getId()).get();
        Address address = userFromDb.getAddress();
        return !StringUtils.isBlank(address.getCity()) && !StringUtils.isBlank(address.getHouse_number())
                && !StringUtils.isBlank(address.getName()) && !StringUtils.isBlank(address.getPostcode())
                && !StringUtils.isBlank(address.getSurname()) && address.getPhoneNumber() != null
                && !StringUtils.isBlank(address.getStreet());
    }


    @Override
    public String existsInDatabase() throws RuntimeException {
        String name = null;

        for (CartItem x : cart.getItems()) {
            if (x.getItem() instanceof Feed) {
                Feed pet = feedRepository.findById(x.getItem().getId()).get();
                if (pet.getAmount() < x.getAmount())
                    name = pet.getName();
            }
            if (x.getItem() instanceof Accessory) {
                Accessory pet = accessoryRepository.findById(x.getItem().getId()).get();
                if (pet.getAmount() < x.getAmount())
                    name = pet.getName();
            }
        }

        if (!StringUtils.isBlank(name))
            removeFromCart(name);

        return name;
    }


    private void removeFromCart(String name) {
        List<CartItem> list = cart.getItems()
                .stream()
                .filter(e -> !e.getItem().getName().equals(name))
                .collect(Collectors.toList());
        cart.setItems(list);
    }


    private Integer generateOrderId() {
        Random rand = new Random();
        Integer id = rand.nextInt(10000000);
        while (orderRepository.existsByOrderId(id))
            id = rand.nextInt();
        return id;
    }


    private void clearCart() {
        cart.setItems(new ArrayList<>());
    }


    private boolean hasDuplicates(CartItem item) {
        CartItem duplicate = cart.getItems()
                .stream()
                .filter(e -> e.getItem().getName().equals(item.getItem().getName()))
                .findFirst()
                .orElse(null);

        if (duplicate != null) {
            duplicate.setAmount(duplicate.getAmount() + item.getAmount());
            return true;
        }
        return false;
    }


    @Override
    public Cart getCart() {
        return cart;
    }
}
