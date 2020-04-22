package com.telusko.service;

import com.telusko.pojo.Client;
import org.springframework.ui.Model;

public interface ICRUDService {
    public void create(Client client, Model model);
    public void retrieve(Client client,Model model);
    public void retrieveAll(Client client,Model model);
}
