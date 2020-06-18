/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.productmvctitles.controller;

import com.mycompany.productmvctitles.entities.Contact;
import com.mycompany.productmvctitles.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author linhtn
 */
@Controller
public class ContactController {

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact1") Contact product, BindingResult result) {
        return "redirect:contact.html";
    }

    @RequestMapping("/contact")
    public String showContacts(Model m) {
        m.addAttribute("command", new Contact());
        return "contact_page";
    }
}
