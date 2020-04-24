package com.telusko.service;

import com.telusko.dao.IDaoInterface;
import com.telusko.dao.MysqlDatabaseOperation;
import com.telusko.pojo.Client;
import com.telusko.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.LinkedHashMap;
import java.util.List;

@Component("employeeImpl")
public class EmployeeService {
    final String LOCATION = "createclient.html";
    final String MESSAGE = "<script>alert('%s'); location ='%s';</script>";

    @Autowired
    @Qualifier("daoImpl")
    IDaoInterface<Employee, MysqlDatabaseOperation> daoInterface;
    MysqlDatabaseOperation<Employee> mysqlDatabaseOperation = MysqlDatabaseOperation.getInstance();

    public void create(Employee employee, Model model) {
        model.addAttribute("employee","welome");
        LinkedHashMap<String, String> data = employee.employeeData();
        try {
            daoInterface.create(employee, mysqlDatabaseOperation, data,model);
        }catch (Exception e) {
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
        }
    }
    public void retrieve(Employee employee,Model model) {
        String column = "employeeId";
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        String id = employee.getId();
        checkData.put(column, id);
        try{
            LinkedHashMap<String, String> viewData = daoInterface.retrieve(employee, mysqlDatabaseOperation, checkData);
            //view data
            model.addAttribute("view",viewData);

        }catch(Exception e){
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
            model.addAttribute("e",e);
        }
    }

    public void retrieveAll(Employee employee , Model model) {
        try {
            List<LinkedHashMap<String,String>> data = daoInterface.retrieveAll(employee,mysqlDatabaseOperation);
            model.addAttribute("data",data);
        } catch (Exception e) {
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
            model.addAttribute("e",e);
        }
    }

    public void update(Employee employee,Model model) {

        String idName = "employeeId";
        LinkedHashMap<String, String> data = employee.employeeData();
        try{
            daoInterface.update(employee, mysqlDatabaseOperation, data,idName);
        }catch(Exception e){
            String result = String.format(MESSAGE, e, LOCATION);
            //out.println(result);
            model.addAttribute("e",e);
        }

    }
}
