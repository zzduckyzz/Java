package com.model;

public class Employee {
    public Employee(String fullname, String birthday, String address, String position, String department) {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", full_Name='" + full_Name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public Employee(int ID, String full_Name, String birthday, String address, String position, String department) {
        this.ID = ID;
        this.full_Name = full_Name;
        this.birthday = birthday;
        this.address = address;
        this.position = position;
        this.department = department;
    }

    private int ID;
    private String full_Name;
    private String birthday;
    private String address;
    private String position;
    private String department;
}
