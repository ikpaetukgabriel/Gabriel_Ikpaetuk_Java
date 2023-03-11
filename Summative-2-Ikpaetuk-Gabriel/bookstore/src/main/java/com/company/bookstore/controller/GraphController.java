package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class GraphController {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public GraphController(PublisherRepository publisherRepository, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public Publisher findPublisherById(@Argument Integer id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        return publisherOptional.orElse(null);
    }

    @QueryMapping
    public Author findAuthorById(@Argument Integer id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        return authorOptional.orElse(null);
    }

    @QueryMapping
    public Book findBookById(@Argument Integer id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElse(null);
    }

}
