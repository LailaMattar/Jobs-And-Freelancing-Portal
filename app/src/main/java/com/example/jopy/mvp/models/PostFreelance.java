package com.example.jopy.mvp.models;

public class PostFreelance {
    private String title ,time ,accountName ;
    private int applicantsNum ,priceFrom ,priceTo ,accountImg ;

    public PostFreelance(String title, String time, String accountName, int applicantsNum, int priceFrom, int priceTo, int accountImg) {
        this.title = title;
        this.time = time;
        this.accountName = accountName;
        this.applicantsNum = applicantsNum;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
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

    public int getApplicantsNum() {
        return applicantsNum;
    }

    public void setApplicantsNum(int applicantsNum) {
        this.applicantsNum = applicantsNum;
    }

    public int getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(int priceFrom) {
        this.priceFrom = priceFrom;
    }

    public int getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(int priceTo) {
        this.priceTo = priceTo;
    }

    public int getAccountImg() {
        return accountImg;
    }

    public void setAccountImg(int accountImg) {
        this.accountImg = accountImg;
    }

    @Override
    public String toString() {
        return "PostFreelance{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", accountName='" + accountName + '\'' +
                ", applicantsNum=" + applicantsNum +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", accountImg=" + accountImg +
                '}';
    }
}
