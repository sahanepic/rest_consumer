package com.sahanbcs.models;

//import javax.validation.constraints.NotBlank;

import java.io.Serializable;

public class SampleReq implements Serializable {

//    @NotBlank
    private String name;
//    @NotBlank
    private String age;

    public SampleReq(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public SampleReq( ) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
