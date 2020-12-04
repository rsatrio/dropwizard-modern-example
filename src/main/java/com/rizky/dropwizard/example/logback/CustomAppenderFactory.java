package com.rizky.dropwizard.example.logback;

import io.dropwizard.logging.AbstractAppenderFactory;
import io.dropwizard.logging.async.AsyncAppenderFactory;
import io.dropwizard.logging.filter.LevelFilterFactory;
import io.dropwizard.logging.layout.LayoutFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("custom_console")
public class CustomAppenderFactory extends AbstractAppenderFactory<ILoggingEvent>{


    @JsonProperty
    private String custom_variable;



    public String getCustom_variable() {
        return custom_variable;
    }


    public void setCustom_variable(String custom_variable) {
        this.custom_variable = custom_variable;
    }



    @Override
    public Appender<ILoggingEvent> build(LoggerContext context, String appName, LayoutFactory layoutFactory,
            LevelFilterFactory levelFilterFactory, AsyncAppenderFactory asyncFactory) {
        final CustomConsoleAppender appender1=new CustomConsoleAppender();
        appender1.setName("custom-console");
        appender1.setContext(context);
        if(custom_variable!=null)   {
            appender1.setCustom_variable(custom_variable);
        }
        appender1.start();
        return wrapAsync(appender1, asyncFactory);

    }





}
