package com.sensemore.event_sam;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent{

    public MessageEvent(Object source, String userName, String email) {
        super(source);

        this.userName = userName;
        this.email = email;
    }

    private String userName;
    private String email;


    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

}
