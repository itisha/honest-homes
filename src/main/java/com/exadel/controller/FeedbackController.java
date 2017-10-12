package com.exadel.controller;

import com.exadel.mongodb.model.Feedback;
import com.exadel.mongodb.service.api.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {


    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAll() {
        return new ResponseEntity<List<Feedback>>(feedbackService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findById(@PathVariable("id") String id) {
        return new ResponseEntity<Feedback>(feedbackService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/authorId/{authorId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findByAuthorId(@PathVariable("authorId") String landlordId) {
        return new ResponseEntity<List<Feedback>>(feedbackService.findByAuthorId(landlordId), HttpStatus.OK);
    }

    @RequestMapping(value = "/entityId/{entityId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findByEntityId(@PathVariable("authorId") String landlordId) {
        return new ResponseEntity<List<Feedback>>(feedbackService.findByEntityId(landlordId), HttpStatus.OK);
    }

    @RequestMapping(value = "/sha256/{sha256Hex}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findBySha256Hex(@PathVariable("sha256Hex") String sha256Hex) {
        return new ResponseEntity<Feedback>(feedbackService.findBySha256Hex(sha256Hex), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{feedbackId}/databall", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getDataBall(@PathVariable("feedbackId") String feedbackId) {
        return new ResponseEntity<String>(feedbackService.getDataBall(feedbackId), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity create(@RequestBody Feedback property) {
        return new ResponseEntity<Feedback>(feedbackService.save(property), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity update(@RequestBody Feedback property) {
        return new ResponseEntity<Feedback>(feedbackService.save(property), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable("id") String id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (feedbackService.exists(id)) {
            feedbackService.delete(id);
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity(status);
    }
}
