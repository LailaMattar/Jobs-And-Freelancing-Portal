package com.example.jopy.mvp.models;

public class PostJop {
    private String title ,time ,accountName ,address ,jopType ;
    private int applicantsNum ,salaryFrom ,salaryTo ,accountImg;

    public PostJop(String title, String time, String accountName, String address, String jopType, int applicantsNum, int salaryFrom, int salaryTo, int accountImg) {
        this.title = title;
        this.time = time;
        this.accountName = accountName;
        this.address = address;
        this.jopType = jopType;
        this.applicantsNum = applicantsNum;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
        this.accountImg = accountImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJopType() {
        return jopType;
    }

    public void setJopType(String jopType) {
        this.jopType = jopType;
    }

    public int getApplicantsNum() {
        return applicantsNum;
    }

    public void setApplicantsNum(int applicantsNum) {
        this.applicantsNum = applicantsNum;
    }

    public int getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(int salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public int getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(int salaryTo) {
        this.salaryTo = salaryTo;
    }

    public int getAccountImg() {
        return accountImg;
    }

    public void setAccountImg(int accountImg) {
        this.accountImg = accountImg;
    }

    @Override
    public String toString() {
        return "PostJop{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", accountName='" + accountName + '\'' +
                ", address='" + address + '\'' +
                ", jopType='" + jopType + '\'' +
                ", applicantsNum=" + applicantsNum +
                ", salaryFrom=" + salaryFrom +
                ", salaryTo=" + salaryTo +
                ", accountImg=" + accountImg +
                '}';
    }
}
