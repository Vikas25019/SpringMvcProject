package com.telusko.pojo;

import javax.validation.constraints.*;

public class Person {

    @NotBlank(message = "User id can`t be empty.")
    @Pattern(regexp = "[0-9]*",message = "User Id must be integer and positive.")
    private String id;

    //@NotBlank(message = "User name can`t be empty.")
    //@Pattern(regexp = "^[a-zA-Z][a-zA-Z\\s]+$",message = "Username can only use alphabet letter characters.")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
