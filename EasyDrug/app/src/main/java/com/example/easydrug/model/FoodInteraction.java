package com.example.easydrug.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FoodInteraction implements Serializable {

    @SerializedName("food_name")
    String foodName;
}
