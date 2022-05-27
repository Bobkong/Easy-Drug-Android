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

    @SerializedName("food_interactions")
    ArrayList<FoodInteraction> foodInteractions;
}
