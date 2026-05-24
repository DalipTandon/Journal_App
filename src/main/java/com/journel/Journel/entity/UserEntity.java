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

}
