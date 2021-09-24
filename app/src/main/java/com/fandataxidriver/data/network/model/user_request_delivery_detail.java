package com.fandataxidriver.data.network.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class user_request_delivery_detail {

    @SerializedName("delivery_address")
    @Expose
    private String deliveryAddress;

    @SerializedName("user_id")
    @Expose
    private int userId;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("user_request_id")
    @Expose
    private int userRequestId;

    @SerializedName("receiver_name")
    @Expose
    private String receiverName;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("item_to_deliver")
    @Expose
    private String itemToDeliver;

    @SerializedName("receiver_mobile")
    @Expose
    private String receiverMobile;

    @SerializedName("any_instructions")
    @Expose
    private String anyInstructions;

    public void setDeliveryAddress(String deliveryAddress){
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress(){
        return deliveryAddress;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserRequestId(int userRequestId){
        this.userRequestId = userRequestId;
    }

    public int getUserRequestId(){
        return userRequestId;
    }

    public void setReceiverName(String receiverName){
        this.receiverName = receiverName;
    }

    public String getReceiverName(){
        return receiverName;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setItemToDeliver(String itemToDeliver){
        this.itemToDeliver = itemToDeliver;
    }

    public String getItemToDeliver(){
        return itemToDeliver;
    }

    public void setReceiverMobile(String receiverMobile){
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverMobile(){
        return receiverMobile;
    }

    public void setAnyInstructions(String anyInstructions){
        this.anyInstructions = anyInstructions;
    }

    public String getAnyInstructions(){
        return anyInstructions;
    }
}