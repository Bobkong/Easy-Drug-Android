package com.example.easydrug.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DrugList implements Serializable {

    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("druglist")
    private ArrayList<Drug> drugList;

    public ArrayList<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(ArrayList<Drug> drugList) {
        this.drugList = drugList;
    }
}
