package com.journel.Journel.App.Services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journel.Journel.App.Respository.JournalAppRepository;
import com.journel.Journel.entity.journalEntry;

@Component
public class JournalAppService {
    @Autowired
    private JournalAppRepository journalEntryRepository;

    public void saveJournalEntry(journalEntry entry){ 
        journalEntryRepository.save(entry);
    }
    public List<journalEntry> getAllEnteries(){
        return journalEntryRepository.findAll();
    }

    public Optional<journalEntry> getEntryById(ObjectId myId){
        return journalEntryRepository.findById(myId);
    }
    public void deleteEntry(ObjectId id){
         journalEntryRepository.deleteById(id);
    }
   
}
