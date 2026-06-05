package com.journel.Journel.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Document(collection = "Users")
@Data
public class UserEntity {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    // @lombok.NonNull
    private String userName;
    // @lombok.NonNull
    private String password;
    @DBRef
    private List<journalEntry> journanEnteries=new ArrayList<>();
    private List<String> roles;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<journalEntry> getJournanEnteries() {
        return journanEnteries;
    }

    public void setJournanEnteries(List<journalEntry> journanEnteries) {
        this.journanEnteries = journanEnteries;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
