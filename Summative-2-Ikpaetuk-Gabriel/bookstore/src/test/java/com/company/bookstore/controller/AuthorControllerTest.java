package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.AuthorRepository;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository authorRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    public AuthorControllerTest() {
        // This helps the object mapper handle LocaleDate
        this.mapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void shouldAddAuthor() throws Exception {
        // Arrange ...
        Author authorExpected = buildAuthor();
        Publisher publisher = buildPublisher();
        Book book = buildBook(authorExpected, publisher);
        Set<Book> books = Stream.of(book).collect(Collectors.toSet());
        authorExpected.setBooks(books);

        //  TODO: Remove publisher and book if needed?
        Author authorActual = buildAuthor();
        authorExpected.setBooks(books);

        // Mock
        doReturn(authorActual).when(authorRepository).save(authorExpected);

        String authorAsString = mapper.writeValueAsString(authorExpected);
        // Act ...
        mockMvc.perform(post("/authors")
                        .content(authorAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void shouldGetAuthorById() throws Exception {
        // Arrange ...
        Author authorExpected = buildAuthor();
        int expectedAuthorId = authorExpected.getId();

        Author authorActual = buildAuthor();

        // Mock
        doReturn(Optional.of(authorActual)).when(authorRepository).findById(expectedAuthorId);

        // Act ...
        mockMvc.perform(get("/authors/".concat(String.valueOf(expectedAuthorId))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAuthors() throws Exception {
        // Arrange ...
        Author author = buildAuthor();

        Author author2 = buildAuthor();
        author2.setId(1);
        author2.setFirstName("John");
        author2.setLastName("Doe");

        List<Author> authors = Arrays.asList(author, author2);

        // Mock
        doReturn(authors).when(authorRepository).findAll();

        // Act ...
        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldUpdateAuthor() throws Exception {
        // Arrange ...
        Author authorExpected = buildAuthor();
        Author authorActual = buildAuthor();

        // Mock
        doReturn(authorActual).when(authorRepository).save(authorExpected);
        String authorAsString = mapper.writeValueAsString(authorExpected);

        // Act ...
        mockMvc.perform(put("/authors")
                        .content(authorAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteAuthor() throws Exception {
        // Act ...
        mockMvc.perform(delete("/authors/0"))
                .andExpect(status().isNoContent());
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