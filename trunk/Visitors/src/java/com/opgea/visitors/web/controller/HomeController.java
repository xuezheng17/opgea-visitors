/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="home")
public class HomeController {
 
    @RequestMapping(method= RequestMethod.GET)
    public String show(){
        return "home";
    }
}
