package com.softwareag.code.review.session;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCart {
    private String user;
    private Map<String,Integer> items = new HashMap<>();

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void addItem(String itemId) {
        if(items.containsKey(itemId)){
            items.computeIfPresent(itemId,(key,count)->++count);
        }else{
            items.put(itemId,0);
        }

    }

    public String getUser() {
        return user;
    }

    public void clear() {
        user = null;
        items.clear();
    }
}
