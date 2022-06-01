package com.example.easydrug.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FoodInteraction implements Serializable {

    @SerializedName("other_drug_name")
    String foodName;

    @SerializedName("interaction_desc")
    String interactionDesc;

    @SerializedName("probability")
    int probability;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getInteractionDesc() {
        return interactionDesc;
    }

    public void setInteractionDesc(String interactionDesc) {
        this.interactionDesc = interactionDesc;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
}
