package com.journel.Journel.App.Controller;
import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journel.Journel.entity.journalEntry;


@RestController
@RequestMapping("/api/journal-entries")
public class JournalEntryController {
   
    private Map<Long, journalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<journalEntry> getAllEntries() {
        return new ArrayList<>(journalEntries.values());   //returns all the enteries
    }

    @PostMapping
    public void createEntry(@RequestBody journalEntry entry) {
        journalEntries.put(entry.getId(), entry);  //create a new entry

    }

    @GetMapping("/id/{id}")
    public journalEntry getEntryById(@PathVariable long id) {
        // System.out.println("Received request for entry with id: " + id);
        return journalEntries.get(id);  //shows any entry with given id
    }

    @DeleteMapping("/id/{id}")
    public void deleteEntryById(@PathVariable long myId){
        journalEntries.remove(myId); //remove any entry by their id
    }

    @PutMapping("/id/{id}")
    public journalEntry updateEntryById(@PathVariable long id , @RequestBody journalEntry entry){
        return journalEntries.put(id, entry);
    }

}