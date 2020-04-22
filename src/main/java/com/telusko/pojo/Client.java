package com.telusko.pojo;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.LinkedHashMap;

@Component
public class Client extends Person {

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z\\s]+$",message = "Address can only use alphabet letter characters.")
    private String address;
    private LinkedHashMap<String,String> map = new LinkedHashMap<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LinkedHashMap<String,String> clientData() {
        map.put("clientId",getId());
        map.put("name",getName());
        map.put("address",getAddress());
        return map;
    }
}
