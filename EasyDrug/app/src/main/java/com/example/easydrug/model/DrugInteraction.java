package com.example.easydrug.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DrugInteraction implements Serializable {

    @SerializedName("drug_name")
    String drugName;

    @SerializedName("interaction_desc")
    String interactionDesc;

    @SerializedName("possibilities")
    ArrayList<SideEffectPossibility> possibilities;

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getInteractionDesc() {
        return interactionDesc;
    }

    public void setInteractionDesc(String interactionDesc) {
        this.interactionDesc = interactionDesc;
    }

    public ArrayList<SideEffectPossibility> getPossibilities() {
        return possibilities;
    }

    public void setPossibilities(ArrayList<SideEffectPossibility> possibilities) {
        this.possibilities = possibilities;
    }
}
