package com.rizky.dropwizard.example.logback;

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.ConsoleAppender;

public class CustomConsoleAppender extends AppenderBase<ILoggingEvent>{

    @JsonProperty
    private String custom_variable;



    public String getCustom_variable() {
        return custom_variable;
    }


    public void setCustom_variable(String custom_variable) {
        this.custom_variable = custom_variable;
    }
    
    @Override
    protected void append(ILoggingEvent event) {
        
       String log1=custom_variable+" "+event.getFormattedMessage();
       
       System.out.println(log1);
        
    }

}
