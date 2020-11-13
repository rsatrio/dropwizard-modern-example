package com.rizky.dropwizard.example.api;

import java.util.List;

public class StdResponseV1 {

    boolean statusOk;
    String message;
    List<String> data;
    public boolean isStatusOk() {
        return statusOk;
    }
    public void setStatusOk(boolean statusOk) {
        this.statusOk = statusOk;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<String> getData() {
        return data;
    }
    public void setData(List<String> data) {
        this.data = data;
    }
    
    
}
