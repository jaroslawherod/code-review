package com.softwareag.code.review.dao.impl;

import com.softwareag.code.review.dao.UserDetailsDao;
import com.softwareag.code.review.model.UserDetails;

public class InMemoryUserDetailsDao implements UserDetailsDao {
    @Override
    public UserDetails getUserDetails(String id) {
        switch (id){
            case  "0":
                return new UserDetails("user1","user1@gmail.com","mysecretpass");
            case  "1":
                return new UserDetails("user2","user2@gmail.com","mysecretpass2");
        }
        return null;
    }
}
