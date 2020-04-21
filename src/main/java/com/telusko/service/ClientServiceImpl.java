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
public class ClientServiceImpl implements ICRUDService{
    final String LOCATION = "createclient.html";
    final String MESSAGE = "<script>alert('%s'); location ='%s';</script>";

    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Client, MysqlDatabaseOperation> daoInterface;

    MysqlDatabaseOperation<Client> mysqlDatabaseOperation = MysqlDatabaseOperation.getInstance();

    @Override
    public void create(Client client,Model model) {
        LinkedHashMap<String, String> data = client.clientData();
        try {
            daoInterface.create(client, mysqlDatabaseOperation, data,model);
        }catch (Exception e) {
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
        }
    }

    @Override
    public void retrieve(Client client,Model model) {
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        String id = client.getId();
        checkData.put("id", id);
        try{
            LinkedHashMap<String, String> viewData = daoInterface.retrieve(client, mysqlDatabaseOperation, checkData);
            //view data
            model.addAttribute("view",viewData);

        }catch(Exception e){
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
        }
    }
}
