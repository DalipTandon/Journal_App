package com.journel.Journel.App.Controller;
import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journel.Journel.App.Services.JournalAppService;
import com.journel.Journel.App.Services.UserService;
import com.journel.Journel.entity.UserEntity;
import com.journel.Journel.entity.journalEntry;


@RestController
@RequestMapping("/api/journal-entries")
public class JournalEntryControllerv2 {
   
@Autowired
private JournalAppService journalAppService;
@Autowired
private UserService userServ;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllEntriesOfUser(@PathVariable String userName) {
        UserEntity user=userServ.findUserByUsername(userName);
        List<journalEntry> all=user.getJournanEnteries();
        if(all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry entry,@PathVariable String userName) {
        entry.setDate(LocalDateTime.now());
        journalAppService.saveJournalEntry(entry,userName);
        return new ResponseEntity<>(entry,HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<journalEntry> getEntryById(@PathVariable ObjectId id) {
        Optional<journalEntry>  jourEntry= journalAppService.getEntryById(id);
        if(jourEntry.isPresent()){
            return new ResponseEntity<>(jourEntry.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{userName}/{id}")
    public void deleteEntryById(@PathVariable ObjectId id,@PathVariable String userName){
        System.out.println(id);
        journalAppService.deleteEntry(id,userName);
    }

    @PutMapping("/id/{userName}/{id}")
    public journalEntry updateEntryById(@PathVariable ObjectId id , @RequestBody journalEntry entry,@PathVariable String userName){
        journalEntry oldEntry=journalAppService.getEntryById(id).orElse(null);
        if(oldEntry!=null){
            oldEntry.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("")?entry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(entry.getContent()!=null && !entry.getContent().equals("")?entry.getContent():oldEntry.getContent());
        }
        journalAppService.saveJournalEntry(oldEntry);
        return oldEntry;
    }

}