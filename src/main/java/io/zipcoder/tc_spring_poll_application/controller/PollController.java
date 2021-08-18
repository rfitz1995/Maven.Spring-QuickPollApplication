package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
