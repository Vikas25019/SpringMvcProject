package com.telusko.controller;

import com.telusko.pojo.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreateClientController {
    @RequestMapping("/create")
    public String createClientPage(Model model){
        Client client = new Client();
        client.setId("1");
        model.addAttribute("client",client);
        return "createclient";
    }
}
