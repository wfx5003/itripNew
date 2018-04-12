/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itrip;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class AddressModel {
@Id
 @GeneratedValue(strategy=GenerationType.AUTO)
private Integer id;
    private String name;
    private String email;
    private String message;
    
public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    public AddressModel(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
        

    }

    public AddressModel() {

    }

    public boolean isValid() {

        if (getName().length() > 1) {
            if (getEmail().length() > 1 && getMessage().length() >1 ) {
                return true;
            }
        }
        return false;
    }

    public String getName() {

        return name;

    }
     public String getEmail() {

        return email;

    }
      public String getMessage() {

        return message;

    }

    public void setName(String name) {
        this.name = name;
    }
      public void setEmail(String email) {
        this.email = email;
    }
        public void setMessage(String message) {
        this.message = message;
    }

}
