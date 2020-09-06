package com.example.slack.Model;

import java.lang.reflect.Field;

public class Main_Model {
   /* String recentActivity;
    String clients;
    String projects;

    public Main_Model() {
    }

    public Main_Model(String recentActivity, String clients, String projects) {
        this.recentActivity = recentActivity;
        this.clients = clients;
        this.projects = projects;
    }

    public String getRecentActivity() {
        return recentActivity;
    }

    public String getClients() {
        return clients;
    }

    public String getProjects() {
        return projects;
    }

    public Field[] getAllFields()
    {
        return this.getClass().getDeclaredFields();
    }*/

   String group;
   String title;

    public Main_Model() {
    }

    public Main_Model(String group, String title) {
        this.group = group;
        this.title =  title;
    }

    public String getTitle() {
        return title;
    }

    public String getGroup() {
        return group;
    }

    public Field[] getAllFields()
    {
        return this.getClass().getDeclaredFields();
    }
}
