package com.telusko.service;

import com.telusko.dao.DaoImplementation;
import com.telusko.dao.IDaoInterface;
import com.telusko.dao.MysqlDatabaseOperation;
import com.telusko.pojo.Client;
import com.telusko.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

@Component("mapping")
public class MappingDatabase {
    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<MappingDatabase, MysqlDatabaseOperation> daoInterface;

    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Client, MysqlDatabaseOperation> daoInterface2;
    MysqlDatabaseOperation<MappingDatabase> mysqlDatabaseOperation = MysqlDatabaseOperation.getInstance();

    public void createMapping(Employee employee, Model model) {
        final String CLIENT_ID = "clientId";
        final String EMPLOYEE_ID = "employeeId";
        String clientId = employee.getClientId();
        String employeeId = employee.getId();
        MappingDatabase mappingDatabase = new MappingDatabase();
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put(CLIENT_ID, clientId);
        data.put(EMPLOYEE_ID, employeeId);
        try {
            daoInterface.create(mappingDatabase, mysqlDatabaseOperation, data);
        } catch (Exception e) {
           model.addAttribute("excep",e);
        }
    }

    public void viewMapping(Model model ,HttpServletRequest request){
        String employeeIdColumn = "employeeId";
        String clientId = "clientId";

        MappingDatabase mappingDatabase = new MappingDatabase();
        Client client = new Client();

        LinkedHashMap<String, String> data = new LinkedHashMap<>();

        String employeeIdValue = request.getParameter(employeeIdColumn);
        data.put(employeeIdColumn,employeeIdValue);

        try {
            String getId = daoInterface.viewMapping(mappingDatabase, mysqlDatabaseOperation, data, clientId);
            if (getId.length() != 0) {
                data.clear();
                data.put(clientId, getId);
                model.addAttribute("data",data);
                LinkedHashMap<String, String> viewData = daoInterface2.retrieve(client, mysqlDatabaseOperation, data);
                model.addAttribute("viewData",viewData);
            }
        } catch (Exception e){
                model.addAttribute("exception",e);
        }
    }

    public void removeMapping(LinkedHashMap<String, String> checkData,Model model){
        MappingDatabase mappingDatabase = new MappingDatabase();
        try {
            daoInterface.delete(mappingDatabase, mysqlDatabaseOperation, checkData);
        } catch (Exception e) {
            model.addAttribute("exception",e);
        }
    }

}
