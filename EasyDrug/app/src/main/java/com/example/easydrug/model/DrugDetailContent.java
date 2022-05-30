package com.example.easydrug.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DrugDetailContent implements Serializable {

    @SerializedName("drug_list_empty")
    boolean drugListEmpty;

    @SerializedName("is_in_list")
    boolean isInList;

    @SerializedName("drug_interactions")
    ArrayList<DrugInteraction> drugInteractions;

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
