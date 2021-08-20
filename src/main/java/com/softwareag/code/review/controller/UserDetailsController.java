package com.softwareag.code.review.controller;

import com.softwareag.code.review.dao.UserDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController {
    @Autowired
    private UserDetailsDao dao;
}
