package com.example.phonebook;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;

@Entity
public class Contact {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long id;
	
	//@ManyToOne
	//@JoinColumn(name = "userId")
	//private User user;
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)	
	private long contactId;
	private long userId;
	private String contactFirstName;
	private String contactLastName;
	private String contactPhoneNumber;
	
    public Key getKey() {
        return key;
    }
    
    void clearKey() {
        key = null;
    }

    //public Long getId() {
    //    return id;
    //}
    
    //void clearId() {
    //    id = null;
    //}
    
    public long getContactId() {
        return contactId;
    }
    
    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
	
    
    //public User getUser() {
    //   return user;
    //}
    
    //public void setUserId(User user) {
    //    this.user = user;
    //}

    public long getUserId() {
        return userId;
    }
    
    public void setUserId(long userId) {
       this.userId = userId;
    }
    
    public String getContactFirstName() {
        return contactFirstName;
    }
    
    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }    
    
    public String getContactLastName() {
        return contactLastName;
    }
    
    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }  
    
    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }  
    
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }
    

    
}
