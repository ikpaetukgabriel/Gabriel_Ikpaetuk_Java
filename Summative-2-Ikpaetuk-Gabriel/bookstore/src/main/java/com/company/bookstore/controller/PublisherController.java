package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/publishers")
    public List<Publisher> getPublishers() {
        return publisherRepository.findAll();
    }

    @GetMapping("/publishers/{publisherId}")
    public Publisher getPublisherById(@PathVariable int publisherId) {

        Optional<Publisher> returnVal = publisherRepository.findById(publisherId);
        return returnVal.orElse(null);
    }

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher Publisher) {
        return publisherRepository.save(Publisher);
    }

    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher Publisher) {
        publisherRepository.save(Publisher);
    }

    @DeleteMapping("/publishers/{publisherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int publisherId) {
        try {
            publisherRepository.deleteById(publisherId);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found", ex);
        }
    }
}