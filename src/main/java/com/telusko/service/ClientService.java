package com.telusko.service;

import com.telusko.dao.IDaoInterface;
import com.telusko.dao.MysqlDatabaseOperation;
import com.telusko.inputvalidation.InputValidation;
import com.telusko.pojo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;

@Component("clientImpl")
public class ClientService {
    final String LOCATION = "createclient.html";
    final String MESSAGE = "<script>alert('%s');</script>";

    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Client, MysqlDatabaseOperation> daoInterface;
    MysqlDatabaseOperation<Client> mysqlDatabaseOperation = MysqlDatabaseOperation.getInstance();

    @Autowired
    @Qualifier("validation")
    InputValidation inputValidation;

    public void create(Client client,Model model) throws IOException {
        ClientService clientService = new ClientService();
        LinkedHashMap<String, String> data = client.clientData();
        model.addAttribute("client",data);


        try {
            boolean valid = clientService.inputValidation(client,model);
            if(valid){
                daoInterface.create(client, mysqlDatabaseOperation, data,model);
            }
        }catch (Exception e) {
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
        }
    }

    public void retrieve(Client client,Model model) {
        String column = "clientId";
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        String id = client.getId();
        checkData.put(column, id);
        try{
            LinkedHashMap<String, String> viewData = daoInterface.retrieve(client, mysqlDatabaseOperation, checkData);
            //view data
            model.addAttribute("view",viewData);

        }catch(Exception e){
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
            model.addAttribute("e",e);
        }
    }

    public void retrieveAll(Client client,Model model) {
        try {
            List<LinkedHashMap<String,String>> data = daoInterface.retrieveAll(client,mysqlDatabaseOperation);
            model.addAttribute("data",data);
        } catch (Exception e) {
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
            model.addAttribute("e",e);
        }
    }

    public void update(Client client,Model model) {
        String idName = "clientId";
        LinkedHashMap<String, String> data = client.clientData();
        try{
            daoInterface.update(client, mysqlDatabaseOperation, data,idName);
        }catch(Exception e){
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
            model.addAttribute("e",e);
        }
    }

    private boolean inputValidation(Client client,Model model) throws IOException {
        InputValidation inputValidation = new InputValidation();

        try {
            inputValidation.userIdValidator(client.getId());
            inputValidation.userNameValidator(client.getName());
            inputValidation.userAddressValidator(client.getAddress());
            return true;
        } catch (Exception e) {
            String result = String.format(MESSAGE, e, LOCATION);
            model.addAttribute("ex",result);
            return false;
        }
    }

}
