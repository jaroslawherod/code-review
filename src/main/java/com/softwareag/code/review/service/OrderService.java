package com.softwareag.code.review.service;

import java.util.Map;

public interface OrderService {

     String createOrder(String userId, Map<String,Integer>items);
}
