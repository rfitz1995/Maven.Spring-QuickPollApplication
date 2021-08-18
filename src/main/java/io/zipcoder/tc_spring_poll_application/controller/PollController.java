package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PollController {
    private PollRepository repo;

    @Autowired
    public PollController(PollRepository repo) {
        this.repo = repo;
    }

    @GetMapping(value = "/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls(){
        Iterable<Poll> allPolls = repo.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }
    @PostMapping(value="/polls")
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
        poll = repo.save(poll);
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        return new ResponseEntity<>(newPollUri, HttpStatus.CREATED);
    }

    @GetMapping(value="/polls/{pollId}")
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        Poll p = repo.findOne(pollId);
        verifyPoll(pollId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }
    @PutMapping(value="/polls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        // Save the entity
        Poll p = repo.save(poll);
        verifyPoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value="/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        repo.delete(pollId);
        verifyPoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public void verifyPoll(@PathVariable Long pollId){
        if(repo.findOne(pollId) == null){
            throw new ResourceNotFoundException();
        }
    }
}
