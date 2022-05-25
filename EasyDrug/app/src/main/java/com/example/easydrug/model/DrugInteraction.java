package com.example.easydrug.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DrugInteraction implements Serializable {

    @SerializedName("drug_name")
    String drugName;

    @SerializedName("interaction_desc")
    String interactionDesc;
}
