package com.example.phonebook;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
	
	//@PrimaryKey need to use this primary key next time may be to late now
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    
    public Key getKey() {
        return key;
    }
    
    void clearKey() {
        key = null;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
        
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String password) {
    	this.userPassword = password;
    }
    
    public String getFirstName() {
        return userFirstName;
    }
    
    public void setUserFirst(String userFirstName) {
        this.userFirstName = userFirstName;
    }
    
    public String getLastName() {
        return userLastName;
    }
    
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}	