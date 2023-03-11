package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public BookControllerTest() {
        // This helps the object mapper handle LocaleDate
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void shouldAddBook() throws Exception {
        // Arrange ...
        Author author = buildAuthor();
        Publisher publisher = buildPublisher();

        Book bookExpected = buildBook(author, publisher);
        Book bookActual = buildBook(author, publisher);

        // Mock ...
        doReturn(bookActual).when(bookRepository).save(bookExpected);
        String bookAsString = mapper.writeValueAsString(bookExpected);

        // Act ...
        mockMvc.perform(post("/books")
                        .content(bookAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetBookById() throws Exception {
        // Arrange ...
        Author author = buildAuthor();
        Publisher publisher = buildPublisher();

        Book bookExpected = buildBook(author, publisher);
        int expectedPublisherId = bookExpected.getId();

        Book bookActual = buildBook(author, publisher);

        // Mock ...
        doReturn(Optional.of(bookActual)).when(bookRepository).findById(expectedPublisherId);

        // Act ...
        mockMvc.perform(get("/books/".concat(String.valueOf(expectedPublisherId))))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldGetBooks() throws Exception {
        Author author = buildAuthor();
        Publisher publisher = buildPublisher();

        Book book = buildBook(author, publisher);

        Book book2 = buildBook(author, publisher);
        book.setId(1);
        book.setIsbn("4321-44567");
        book2.setTitle("Second Book");
        book2.setPrice(BigDecimal.valueOf(34.56));

        List<Book> books = Arrays.asList(book, book2);

        // Mock
        doReturn(books).when(bookRepository).findAll();

        // Act ...
        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldUpdateBook() throws Exception {
        // Arrange ...
        Author author = buildAuthor();
        Publisher publisher = buildPublisher();

        Book bookExpected = buildBook(author, publisher);
        Book bookActual = buildBook(author, publisher);

        // Mock
        doReturn(bookActual).when(bookRepository).save(bookExpected);
        String bookAsString = mapper.writeValueAsString(bookExpected);

        // Act ...
        mockMvc.perform(put("/books")
                        .content(bookAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        // Act ...
        mockMvc.perform(delete("/books/0"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetBooksByAuthorId() throws Exception {
        // Arrange ...
        Author author = buildAuthor();
        Publisher publisher = buildPublisher();

        Book book = buildBook(author, publisher);
        List<Book> books = Collections.singletonList(book);

        // Mock
        doReturn(books).when(bookRepository).findByAuthorId(author.getId());

        // Act ...
        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk());
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