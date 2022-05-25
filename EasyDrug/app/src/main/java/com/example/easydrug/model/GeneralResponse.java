package com.example.easydrug.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GeneralResponse implements Serializable {
    @SerializedName("code")
    int code;

    @SerializedName("msg")
    String msg;
}
