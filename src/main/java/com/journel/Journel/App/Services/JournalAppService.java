package com.journel.Journel.App.Services;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.journel.Journel.App.Respository.JournalAppRepository;
import com.journel.Journel.entity.UserEntity;
import com.journel.Journel.entity.journalEntry;

@Component
public class JournalAppService {
    @Autowired
    private JournalAppRepository journalEntryRepository;

    @Autowired
    private UserService userServ;
    @Transactional
    public void saveJournalEntry(journalEntry entry,String userName){ 
        try {
         UserEntity user=userServ.findUserByUsername(userName);
       journalEntry saved=journalEntryRepository.save(entry);
       user.getJournanEnteries().add(saved);
       userServ.saveEnteries(user); 
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("An error occured while saving the entry ");
        }
  
    }
       public void saveJournalEntry(journalEntry entry){ 
        // UserEntity user=userServ.findUserByUsername(userName);
       journalEntryRepository.save(entry);
    //    getJournanEnteries().add(saved);
    //    userServ.saveEnteries(user);
    }
    public List<journalEntry> getAllEnteries(){
        return journalEntryRepository.findAll();
    }

    public Optional<journalEntry> getEntryById(ObjectId myId){
        return journalEntryRepository.findById(myId);
    }
    public void deleteEntry(ObjectId id,String userName){
         UserEntity user=userServ.findUserByUsername(userName);
         user.getJournanEnteries().removeIf(x -> x.getId().equals(id));
         userServ.saveEnteries(user);
         journalEntryRepository.deleteById(id);
    }
    
   
}
