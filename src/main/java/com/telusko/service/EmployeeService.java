package com.telusko.service;

import com.telusko.dao.IDaoInterface;
import com.telusko.dao.MysqlDatabaseOperation;
import com.telusko.inputvalidation.InputValidation;
import com.telusko.pojo.Client;
import com.telusko.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @Autowired
    @Qualifier("mapping")
    MappingDatabase mappingDatabase;

    public String create(Employee employee, Model model) {
        String message = "";
        final String CLIENT_ID = "clientId";
        final String EMPLOYEE_ID = "employeeId";
        EmployeeService employeeService = new EmployeeService();
        boolean valid = employeeService.inputValidation(employee, model);
        if (valid) {
            LinkedHashMap<String, String> data = employee.employeeData();
            LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
            checkData.put(EMPLOYEE_ID, data.get(EMPLOYEE_ID));
            try {
                boolean checkId = daoInterface.isIdPresent(employee, mysqlDatabaseOperation, checkData);
                if (!checkId) {
                    daoInterface.create(employee, mysqlDatabaseOperation, data);
                    mappingDatabase.createMapping(employee,model);
                    message = "Record saved successfully!";
                }
                else {
                     message = "Id already present";
                }
            } catch (Exception e) {
                model.addAttribute("exceptionMessage", e);
            }
        }
        return message;
    }
    public String retrieve(Employee employee,Model model) {
        boolean success = false;

        String column = "employeeId";
        String message = "";
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        EmployeeService employeeService = new EmployeeService();
        String id = employee.getId();
        checkData.put(column, id);
        try {
            boolean valid = employeeService.userIdValidation(employee, model);
            if (valid) {
                boolean checkId = daoInterface.isIdPresent(employee, mysqlDatabaseOperation, checkData);
                if (checkId) {
                    LinkedHashMap<String, String> viewData = daoInterface.retrieve(employee, mysqlDatabaseOperation, checkData);
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

    public void retrieveAll(Employee employee , Model model) {
        try {
            List<LinkedHashMap<String,String>> data = daoInterface.retrieveAll(employee,mysqlDatabaseOperation);
            model.addAttribute("data",data);
        } catch (Exception e) {
            model.addAttribute("e",e);
        }
    }

    public String update(Employee employee,Model model) {

        String idName = "employeeId";
        boolean success = false;
        String message = "";
        EmployeeService employeeService = new EmployeeService();
        try {
            boolean valid = employeeService.inputValidation(employee, model);
            if (valid) {
                LinkedHashMap<String, String> data = employee.employeeData();
                String id = data.get(idName);
                LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
                checkData.put(idName, id);

                boolean checkId = daoInterface.isIdPresent(employee, mysqlDatabaseOperation, checkData);
                if (checkId) {
                    daoInterface.update(employee, mysqlDatabaseOperation, data, idName);
                    message = "Record update successfully!";
                    success = true;

                }
                else{
                    message = "Id is not present";
                }
            }
        } catch (Exception e) {
            model.addAttribute("e", e);
        }
        model.addAttribute("success",success);
        return message;

    }

    public void delete(Employee employee, Model model, HttpServletRequest request) {
        String idName = "employeeId";
        String id = request.getParameter(idName);
        LinkedHashMap<String, String> checkData = new LinkedHashMap<>();
        checkData.put(idName, id);
        try {
            daoInterface.delete(employee, mysqlDatabaseOperation, checkData);
            mappingDatabase.removeMapping(checkData,model);
        } catch (Exception e) {
            model.addAttribute("exception", e);
        }
    }

    private boolean inputValidation(Employee employee,Model model) {
        InputValidation inputValidation = new InputValidation();

        try {
            inputValidation.userIdValidator(employee.getId());
            inputValidation.userIdValidator(employee.getClientId());
            inputValidation.userNameValidator(employee.getName());
            inputValidation.userDepartmentValidator(employee.getDepartment());
            inputValidation.userEmailValidator(employee.getEmail());
            inputValidation.userDateOfBirthValidator(employee.getDateOfBirth());
            return true;
        } catch (Exception e) {
            model.addAttribute("exception", e);
            return false;
        }
    }

    private boolean userIdValidation(Employee employee, Model model) {
        InputValidation inputValidation = new InputValidation();
        try {
            inputValidation.userIdValidator(employee.getId());
            return true;
        } catch (Exception e) {
            model.addAttribute("exception", e);
            return false;
        }
    }
}
