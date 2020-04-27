package com.telusko.service;

import com.telusko.dao.IDaoInterface;
import com.telusko.dao.MysqlDatabaseOperation;
import com.telusko.inputvalidation.InputValidation;
import com.telusko.pojo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Component("clientImpl")
public class ClientService {

    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Client, MysqlDatabaseOperation> daoInterface;
    MysqlDatabaseOperation<Client> mysqlDatabaseOperation = MysqlDatabaseOperation.getInstance();

    public String create(Client client, Model model) {
        String ID = "clientId";
        String message = "";
        ClientService clientService = new ClientService();
        try {
            LinkedHashMap<String, String> data = client.clientData();
            String id = client.getId();
            boolean valid = clientService.inputValidation(client, model);
            if (valid) {
                LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
                checkData.put(ID, id);
                boolean checkId = daoInterface.isIdPresent(client, mysqlDatabaseOperation, checkData);
                if (!checkId) {
                    daoInterface.create(client, mysqlDatabaseOperation, data);
                    message = "Record saved successfully!";
                } else {
                    message = "Id already present";
                }
            }
        } catch (Exception e) {
            model.addAttribute("exceptionMessage", e);
        }
        return message;
    }

    public String retrieve(Client client, Model model) {
        boolean success = false;

        String column = "clientId";
        String message = "";
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        ClientService clientService = new ClientService();
        String id = client.getId();
        checkData.put(column, id);
        try {
            boolean valid = clientService.userIdValidation(client, model);
            if (valid) {
                boolean checkId = daoInterface.isIdPresent(client, mysqlDatabaseOperation, checkData);
                if (checkId) {
                    LinkedHashMap<String, String> viewData = daoInterface.retrieve(client, mysqlDatabaseOperation, checkData);
                    model.addAttribute("view", viewData);
                    success = true;
                } else {
                    message = "Id is not present";
                }
            }
        } catch (Exception e) {
            model.addAttribute("exp", e);
        }
        model.addAttribute("success", success);
        return message;
    }

    public void retrieveAll(Client client, Model model) {
        try {
            List<LinkedHashMap<String, String>> data = daoInterface.retrieveAll(client, mysqlDatabaseOperation);
            model.addAttribute("data", data);
        } catch (Exception e) {
            model.addAttribute("e", e);
        }
    }

    public String update(Client client, Model model) {
        String idName = "clientId";
        boolean success = false;
        String message = "";
        ClientService clientService = new ClientService();
        try {
            boolean valid = clientService.inputValidation(client, model);
            if (valid) {
                LinkedHashMap<String, String> data = client.clientData();
                String id = data.get(idName);
                LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
                checkData.put(idName, id);

                boolean checkId = daoInterface.isIdPresent(client, mysqlDatabaseOperation, checkData);
                if (checkId) {
                    daoInterface.update(client, mysqlDatabaseOperation, data, idName);
                    message = "Record update successfully!";
                    success = true;

                }
                else{
                    message = "Id is not present";
                }
            }
        } catch (Exception e) {
            //String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
            model.addAttribute("e", e);
        }
        model.addAttribute("success",success);
        return message;
    }

    public void delete(Client client, Model model, HttpServletRequest request) {
        String idName = "clientId";
        String id = request.getParameter(idName);
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        checkData.put(idName, id);
        try {
            daoInterface.delete(client, mysqlDatabaseOperation, checkData);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
    }

    private boolean inputValidation(Client client, Model model) throws IOException {
        InputValidation inputValidation = new InputValidation();
        try {
            inputValidation.userIdValidator(client.getId());
            inputValidation.userNameValidator(client.getName());
            inputValidation.userAddressValidator(client.getAddress());
            return true;
        } catch (Exception e) {
            model.addAttribute("exception", e);
            return false;
        }
    }

    private boolean userIdValidation(Client client, Model model) {
        InputValidation inputValidation = new InputValidation();
        try {
            inputValidation.userIdValidator(client.getId());
            return true;
        } catch (Exception e) {
            model.addAttribute("exception", e);
            return false;
        }
    }


}
