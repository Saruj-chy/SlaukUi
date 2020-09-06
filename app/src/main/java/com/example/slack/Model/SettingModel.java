package com.example.slack.Model;


import java.lang.reflect.Field;

public class SettingModel {
    String id;
    String name;
    String domain ;
//    String type = "default" ;

    public Field[] getAllFields(){
        return this.getClass().getDeclaredFields() ;
    }

    public SettingModel() {
    }


    public SettingModel(String id, String name, String domain) {
        this.id = id;
        this.name = name;
        this.domain = domain;
    }

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String damain) {
        this.domain = damain;
    }

//    public String getType() {
//        return type;
//    }


}
