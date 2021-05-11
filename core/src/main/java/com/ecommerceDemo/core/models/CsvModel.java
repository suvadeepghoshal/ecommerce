package com.ecommerceDemo.core.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "serialNumber", "companyName", "employee", "description", "leave" })
public class CsvModel {
    private long serialNumber;
    private String companyName;
    private String employee;
    private String description;
    private byte leave;

    public CsvModel() {
        super();
    }

    public CsvModel(long serialNumber, String companyName, String employee, String description, byte leave) {
        this.serialNumber = serialNumber;
        this.companyName = companyName;
        this.employee = employee;
        this.description = description;
        this.leave = leave;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getLeave() {
        return leave;
    }

    public void setLeave(byte leave) {
        this.leave = leave;
    }

}
