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
    public void retrieve(Model model) {
        
    }

    public void retrieveAll(Model model) {

    }
    public void update(Model model) {

    }
}
