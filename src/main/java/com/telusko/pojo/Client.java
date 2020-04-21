package com.telusko.pojo;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.LinkedHashMap;

@Component
public class Client extends Person {

    @NotBlank(message="Address is can`t be blank")
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
