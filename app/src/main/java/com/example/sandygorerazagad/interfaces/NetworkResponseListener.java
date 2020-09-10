package com.example.sandygorerazagad.interfaces;

public interface NetworkResponseListener {

    //Handle When We Recevie Success Data
    void SuccessData(String data);

    //When We Received Fail Response
    void FailedData();
}
