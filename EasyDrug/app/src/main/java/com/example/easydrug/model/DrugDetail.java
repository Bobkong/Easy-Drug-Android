package com.example.easydrug.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DrugDetail implements Serializable {
    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;

    @SerializedName("drug_list_empty")
    boolean drugListEmpty;

    @SerializedName("is_in_list")
    boolean isInList;

    @SerializedName("cur_drug_side_effect")
    String curDrugSideEffect;

    @SerializedName("drug_interactions")
    ArrayList<DrugInteraction> drugInteractions;

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

    public boolean isDrugListEmpty() {
        return drugListEmpty;
    }

    public void setDrugListEmpty(boolean drugListEmpty) {
        this.drugListEmpty = drugListEmpty;
    }

    public boolean isInList() {
        return isInList;
    }

    public void setInList(boolean inList) {
        isInList = inList;
    }

    public ArrayList<DrugInteraction> getDrugInteractions() {
        return drugInteractions;
    }

    public void setDrugInteractions(ArrayList<DrugInteraction> drugInteractions) {
        this.drugInteractions = drugInteractions;
    }
}
