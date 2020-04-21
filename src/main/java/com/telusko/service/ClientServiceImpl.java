package com.telusko.service;

import com.telusko.dao.IDaoInterface;
import com.telusko.dao.MysqlDatabaseOperation;
import com.telusko.pojo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.LinkedHashMap;

@Component("clientImpl")
public class ClientServiceImpl implements IClientService{
    final String LOCATION = "createclient.html";
    final String MESSAGE = "<script>alert('%s'); location ='%s';</script>";

    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Client, MysqlDatabaseOperation> daoInterface;

    @Autowired
    MysqlDatabaseOperation<Client> mysqlDatabaseOperation;

    @Override
    public void create(Client client,Model model) {
        String address = client.getAddress();
        model.addAttribute("addressName",address);
        LinkedHashMap<String, String> data = client.clientData();
        model.addAttribute("data",data);
        try {
            daoInterface.create(client, mysqlDatabaseOperation, data,model);
        }catch (Exception e) {
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
        }
    }
}
