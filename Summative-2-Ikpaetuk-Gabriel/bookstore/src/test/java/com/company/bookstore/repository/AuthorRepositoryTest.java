package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;


    @Before
    public void setUp() {
        authorRepository.deleteAll();
    }

    @Test
    @Transactional
    public void shouldAddAuthor() {
        Author author = buildAuthor();
        authorRepository.save(author);

        Author actualAuthor = authorRepository.findById(author.getId()).orElse(null);
        assertEquals(author, actualAuthor);
    }

    @Test
    @Transactional
    public void shouldFindAuthorById() {
        Author author = buildAuthor();
        authorRepository.save(author);

        Optional<Author> actualAuthor = authorRepository.findById(author.getId());
        assertEquals(author, actualAuthor.orElse(null));
    }

    @Test
    @Transactional
    public void shouldGetAllAuthors() {
        Author author = buildAuthor();
        authorRepository.save(author);

        Author author2 = buildAuthor();
        author2.setEmail("author2@company.test");
        author2.setFirstName("John");
        author2.setLastName("Doe");
        authorRepository.save(author2);

        List<Author> expectedAuthors = Arrays.asList(author, author2);
        List<Author> actualAuthors = authorRepository.findAll();

        assertEquals(expectedAuthors, actualAuthors);
    }


    @Test
    @Transactional
    public void shouldUpdateAuthor() {
        Author authorInitial = buildAuthor();
        authorRepository.save(authorInitial);

        // Update Author
        authorInitial.setEmail("author2@company.test");
        authorInitial.setFirstName("John");
        authorInitial.setLastName("Doe");
        authorRepository.save(authorInitial);

        Author actualAuthor = authorRepository.findById(authorInitial.getId()).orElse(new Author());

        assertEquals(authorInitial.getFirstName(), actualAuthor.getFirstName());
        assertEquals(authorInitial.getLastName(), actualAuthor.getLastName());
        assertEquals(authorInitial.getEmail(), authorInitial.getEmail());
    }


    @Test
    @Transactional
    public void shouldDeleteAuthor() {
        Author author = buildAuthor();
        authorRepository.save(author);

        authorRepository.deleteById(author.getId());

        Optional<Author> actualAuthor = authorRepository.findById(author.getId());
        assertFalse(actualAuthor.isPresent());
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

}