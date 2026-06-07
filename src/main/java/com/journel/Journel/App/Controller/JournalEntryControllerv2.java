package com.journel.Journel.App.Controller;
import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/journal-entries")
public class JournalEntryControllerv2 {
   
@Autowired
private JournalAppService journalAppService;
@Autowired
private UserService userServ;

    @GetMapping
    public ResponseEntity<?> getAllEntriesOfUser() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user=userServ.findUserByUsername(userName);
        List<journalEntry> all=user.getJournanEnteries();
        if(all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry entry) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        entry.setDate(LocalDateTime.now());
        journalAppService.saveJournalEntry(entry,userName);
        return new ResponseEntity<>(entry,HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<journalEntry> getEntryById(@PathVariable ObjectId id) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user=userServ.findUserByUsername(userName);
        boolean owned=user.getJournanEnteries().stream().anyMatch(e->e.getId().equals(id));
        if(!owned){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Optional<journalEntry>  jourEntry= journalAppService.getEntryById(id);

        if(jourEntry.isPresent()){
            return new ResponseEntity<>(jourEntry.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id){
        // System.out.println(id);
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user=userServ.findUserByUsername(userName);
        boolean owned=user.getJournanEnteries().stream().anyMatch(e->e.getId().equals(id));
        if(!owned){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        journalAppService.deleteEntry(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId id , @RequestBody journalEntry entry){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity user=userServ.findUserByUsername(userName);
        boolean owned=user.getJournanEnteries().stream().anyMatch(e->e.getId().equals(id));
        if(!owned){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        journalEntry oldEntry=journalAppService.getEntryById(id).orElse(null);
        
        if(oldEntry!=null){
            oldEntry.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("")?entry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(entry.getContent()!=null && !entry.getContent().equals("")?entry.getContent():oldEntry.getContent());
        }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // ✅ handle null
        }
        journalAppService.saveJournalEntry(oldEntry);
        return new ResponseEntity<>(oldEntry,HttpStatus.OK);
    }

}