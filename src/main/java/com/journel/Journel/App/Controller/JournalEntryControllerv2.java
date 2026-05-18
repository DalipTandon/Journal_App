package com.journel.Journel.App.Controller;
import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.journel.Journel.entity.journalEntry;


@RestController
@RequestMapping("/api/journal-entries")
public class JournalEntryControllerv2 {
   
@Autowired
private JournalAppService journalAppService;
    @GetMapping
    public List<journalEntry> getAllEntries() {
        return journalAppService.getAllEnteries();
    }

    @PostMapping
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry entry) {
        entry.setDate(LocalDateTime.now());
        journalAppService.saveJournalEntry(entry);
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

    @DeleteMapping("/id/{id}")
    public void deleteEntryById(@PathVariable ObjectId id){
        System.out.println(id);
        journalAppService.deleteEntry(id);
    }

    @PutMapping("/id/{id}")
    public journalEntry updateEntryById(@PathVariable ObjectId id , @RequestBody journalEntry entry){
        journalEntry oldEntry=journalAppService.getEntryById(id).orElse(null);
        if(oldEntry!=null){
            oldEntry.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("")?entry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(entry.getContent()!=null && !entry.getContent().equals("")?entry.getContent():oldEntry.getContent());
        }
        journalAppService.saveJournalEntry(oldEntry);
        return oldEntry;
    }

}