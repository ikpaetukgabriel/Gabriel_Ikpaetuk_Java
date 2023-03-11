package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @Before
    public void setUp() {
        bookRepository.deleteAll();
    }


    @Test
    @Transactional
    public void shouldAddBook() {
        Publisher publisher = buildPublisher();
        publisherRepository.save(publisher);

        Author author = buildAuthor();
        authorRepository.save(author);

        Book book = buildBook(author, publisher);
        book = bookRepository.save(book);

        Book actualBook = bookRepository.findById(book.getId()).orElse(null);
        assertEquals(actualBook, book);
    }


    @Test
    @Transactional
    public void shouldFindByBookId() {
        Author author = buildAuthor();
        author = authorRepository.save(author);

        Publisher publisher = buildPublisher();
        publisher = publisherRepository.save(publisher);

        Book book = buildBook(author, publisher);
        book = bookRepository.save(book);

        Optional<Book> actualBook = bookRepository.findById(book.getId());
        assertEquals(book, actualBook.orElse(null));
    }


    @Test
    @Transactional
    public void shouldGetBooks() {
        Author author = buildAuthor();
        author = authorRepository.save(author);

        Publisher publisher = buildPublisher();
        publisher = publisherRepository.save(publisher);

        Book book = buildBook(author, publisher);
        book = bookRepository.save(book);

        Book book2 = buildBook(author, publisher);
        book.setIsbn("4321-44567");
        book2.setTitle("Second Book");
        book2.setPrice(BigDecimal.valueOf(34.56));
        book2 = bookRepository.save(book2);

        List<Book> books = Arrays.asList(book, book2);
        List<Book> actualBooks = bookRepository.findAll();

        assertEquals(actualBooks, books);
    }


    @Test
    @Transactional
    public void shouldUpdateBook() {
        Author author = buildAuthor();
        authorRepository.save(author);

        Publisher publisher = buildPublisher();
        publisherRepository.save(publisher);

        Book bookInitial = buildBook(author, publisher);
        bookRepository.save(bookInitial);

        // Update Book
        bookInitial.setTitle("Updated");
        bookInitial.setIsbn("5555-22332");
        bookRepository.save(bookInitial);

        Book actualBook = bookRepository.findById(bookInitial.getId()).orElse(new Book());

        assertEquals(bookInitial.getTitle(), actualBook.getTitle());
        assertEquals(bookInitial.getIsbn(), actualBook.getIsbn());
    }


    @Test
    @Transactional
    public void shouldDeleteBook() {
        Author author = buildAuthor();
        authorRepository.save(author);

        Publisher publisher = buildPublisher();
        publisherRepository.save(publisher);

        Book book = buildBook(author, publisher);
        book = bookRepository.save(book);

        bookRepository.deleteById(book.getId());

        Optional<Book> actualBookOptional = bookRepository.findById(book.getId());
        assertFalse(actualBookOptional.isPresent());
    }


    @Test
    @Transactional
    public void shouldFindBooksByAuthorId() {
        Author author = buildAuthor();
        author = authorRepository.save(author);

        Publisher publisher = buildPublisher();
        publisher = publisherRepository.save(publisher);

        Book book = buildBook(author, publisher);
        book = bookRepository.save(book);

        List<Book> actualBook = bookRepository.findByAuthorId(book.getAuthorId());
        List<Book> bookList = Collections.singletonList(book);

        assertEquals(actualBook, bookList);
    }


    private static Author buildAuthor() {
        Author author = new Author();
        author.setFirstName("Perd");
        author.setLastName("Last");
        author.setStreet("Broad St");
        author.setCity("Savannah");
        author.setState("GA");
        author.setPostalCode("31401");
        author.setPhone("912-555-5555");
        author.setEmail("perd@perdroasters.com");
        return author;
    }

    private static Publisher buildPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Perd Last");
        publisher.setStreet("Broad St");
        publisher.setCity("Savannah");
        publisher.setState("GA");
        publisher.setPostalCode("31401");
        publisher.setPhone("912-555-5555");
        publisher.setEmail("perd@perdroasters.com");
        return publisher;
    }

    private static Book buildBook(Author author, Publisher publisher) {
        Book book = new Book();
        book.setIsbn("1234-22332");
        book.setPublishDate(LocalDate.of(2023, 3, 8));
        book.setTitle("First book");
        book.setPrice(BigDecimal.valueOf(23.3));
        book.setPublisherId(publisher.getId());
        book.setAuthorId(author.getId());
        return book;
    }


}