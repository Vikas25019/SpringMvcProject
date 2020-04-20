package com.telusko.controller;

import com.telusko.pojo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String sayHello(@ModelAttribute("client") Client client){
       client.setAddress("address");
       client.setId("id");
       client.setName("name");
        return "createclient";
    }
    @RequestMapping(value = "/saveClient", method = RequestMethod.GET)
    public String save(@ModelAttribute("client") Client client){
        return "createclient";
    }
}
