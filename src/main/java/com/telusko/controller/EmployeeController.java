package com.telusko.controller;

import com.telusko.pojo.Employee;
import com.telusko.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    @Qualifier("employeeImpl")
    private EmployeeService employeeService;

    @RequestMapping(value = "/createEmployee", method = RequestMethod.GET)
    public String create( @ModelAttribute("employee") Employee employee) {
        return "employee/createemployee";
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.GET)
    public String save(@ModelAttribute("employee") @Valid Employee employee, BindingResult result, Model model) {

        model.addAttribute("model",result);
        if (result.hasErrors()) {
            List<FieldError> listError=result.getFieldErrors();
            List<String> list = new ArrayList<>();
            for(FieldError error: listError){
                list.add(error.getDefaultMessage());
            }
            model.addAttribute("listError",list);
            return "employee/createemployee";
        }else{
            employeeService.create(employee,model);
        }
        return "employee/demo";
    }

}
