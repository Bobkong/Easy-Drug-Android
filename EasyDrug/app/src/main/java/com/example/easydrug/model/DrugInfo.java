package com.example.easydrug.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DrugInfo implements Serializable {
    @SerializedName("username")
    private String userName;
    @SerializedName("drug_name")
    private String drugName;
    @SerializedName("drug_image_url")
    private String drugImageUrl;
    @SerializedName("drug_upc_code")
    private String drugUpcCode;

    public DrugInfo(String userName, String drugName, String drugImageUrl, String drugUpcCode) {
        this.userName = userName;
        this.drugName = drugName;
        this.drugImageUrl = drugImageUrl;
        this.drugUpcCode = drugUpcCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugImageUrl() {
        return drugImageUrl;
    }

    public void setDrugImageUrl(String drugImageUrl) {
        this.drugImageUrl = drugImageUrl;
    }

    public String getDrugUpcCode() {
        return drugUpcCode;
    }

    public void setDrugUpcCode(String drugUpcCode) {
        this.drugUpcCode = drugUpcCode;
    }
}
