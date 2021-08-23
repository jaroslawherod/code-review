package com.softwareag.code.review.controller;

import com.google.common.hash.BloomFilter;
import com.softwareag.code.review.dao.UserDetailsDao;
import com.softwareag.code.review.model.UserDetails;
import com.softwareag.code.review.service.OrderService;
import com.softwareag.code.review.session.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static org.springframework.http.ResponseEntity.*;

@RestController
public class ShopCartController {

    @Autowired
    private UserDetailsDao dao;
    @Autowired
    private ShoppingCart cart;
    @Autowired
    private OrderService orderService;


    @GetMapping("/user-details")
    public ResponseEntity<String> getUserDetails(@RequestParam String id) {
        UserDetails details = dao.getUserDetails(id);
        if (details == null) {
            return null;
        }
        final var json = "{" +
                "\"username\":" + details.getUsername() + "," +
                " \"email\":" + details.getEmail() + "," +
                "\"password\":" + details.getPasword()
                + "}";
        return ok(json);
    }

    @GetMapping("/items")
    public ResponseEntity<String> items(
            @RequestParam("userId") String userId,
            @RequestParam(value = "itemId", required = false) String itemId
    ) {
        if (dao.getUserDetails(userId) == null) {
            return notFound().build();
        }
        if (itemId == null) {
            final String json = cart.getItems().entrySet()
                    .stream()
                    .map((entry) -> String.format("\"%s\":%s", entry.getKey(), entry.getValue()))
                    .collect(joining(",", "{", "}"));
            return ok(json);
        } else {
            synchronized (cart){
                cart.setUser(userId);
                cart.addItem(itemId);
            }
            return noContent().build();
        }
    }


    @PostMapping("/order")
    public DeferredResult<String> createOrder(){
        DeferredResult<String> result = new DeferredResult<>();
        synchronized (cart){
            new Thread(()->{
              result.setResult(orderService.createOrder(
                      cart.getUser(),
                      cart.getItems()
                      ));
              cart.clear();
            });
        }
        return result;
    }


}
