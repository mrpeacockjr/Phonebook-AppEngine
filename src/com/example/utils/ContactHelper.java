package com.example.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.phonebook.ContactCopy;

public class ContactHelper {

	
	public static List<ContactCopy> sorter(List<ContactCopy> c){
		System.out.println("Begin ContactHelper sorter");
		ArrayList<String> contactNames = new ArrayList<>();
		ArrayList<ContactCopy> sortedContacts = new ArrayList<ContactCopy>();
		
		for(int i = 0 ; i < c.size(); i++)
			contactNames.add(c.get(i).getContactFirstName()+""+c.get(i).getContactLastName());
		Collections.sort(contactNames, String.CASE_INSENSITIVE_ORDER);
		String cNameString = "";
		ContactCopy tempContactCopy;
		for(int i = 0; i < contactNames.size(); i ++){
			cNameString=contactNames.get(i);
			
			labelLoopIn:
			for(int j = 0 ; j < c.size(); j++){
				tempContactCopy = c.get(j);
				if(cNameString.startsWith(tempContactCopy.getContactFirstName()) && 
							cNameString.endsWith(tempContactCopy.getContactLastName() ) ){
					sortedContacts.add(tempContactCopy);
					break labelLoopIn;
				}
					
			}
			
		}	
		
		System.out.println("End ContactHelper sorter");
		return sortedContacts;
		//return c.getItems().
			
	}
}
