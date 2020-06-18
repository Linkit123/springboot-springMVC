/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.productmvctitles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author linhtn
 */
@Controller
public class HomeController {
    private String rootUrl = "http://localhost:1122/";
    private int timeOut = 60000;
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printWelcome(Model m) {
        String mes = "HELOOOOOO there!";
        m.addAttribute("message", mes);
        return "hello_page";
    }
}
