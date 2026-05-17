package com.journel.Journel.App.Respository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journel.Journel.entity.journalEntry;

public interface JournalAppRepository extends MongoRepository<journalEntry,ObjectId> {

    
} 
