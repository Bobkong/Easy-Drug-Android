package com.example.easydrug.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DrugDetail implements Serializable {
    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("drug_detail")
    DrugDetailContent drugDetailContent;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DrugDetailContent getDrugDetailContent() {
        return drugDetailContent;
    }

    public void setDrugDetailContent(DrugDetailContent drugDetailContent) {
        this.drugDetailContent = drugDetailContent;
    }
}
