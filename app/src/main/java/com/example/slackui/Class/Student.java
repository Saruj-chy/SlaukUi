package com.example.slackui.Class;

import java.lang.reflect.Field;

public class Student {

    public String studentId ;
    public String department ;

    public Student() {
    }

    public Field[] getAllFields(){
        return this.getClass().getDeclaredFields() ;
    }

    public Student(String studentId, String department) {
        this.studentId = studentId;
        this.department = department;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
