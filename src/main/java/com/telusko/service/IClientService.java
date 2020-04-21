package com.telusko.service;

import com.telusko.pojo.Client;
import org.springframework.ui.Model;

public interface IClientService {
    public void create(Client client, Model model);
}
