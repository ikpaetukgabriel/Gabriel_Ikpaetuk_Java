package com.company.bookstore.repository;

import com.company.bookstore.model.Publisher;
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
public class PublisherRepositoryTest {

    @Autowired
    PublisherRepository publisherRepository;


    @Before
    public void setUp() {
        publisherRepository.deleteAll();
    }

    @Test
    @Transactional
    public void shouldAddPublisher() {
        Publisher publisher = buildPublisher();
        publisherRepository.save(publisher);

        Publisher actualPublisher = publisherRepository.findById(publisher.getId()).orElse(null);
        assertEquals(publisher, actualPublisher);
    }

    @Test
    @Transactional
    public void shouldFindPublisherById() {
        Publisher publisher = buildPublisher();
        publisherRepository.save(publisher);

        Optional<Publisher> actualPublisher = publisherRepository.findById(publisher.getId());
        assertEquals(publisher, actualPublisher.orElse(null));
    }

    @Test
    @Transactional
    public void shouldGetAllPublishers() {
        Publisher publisher = buildPublisher();
        publisherRepository.save(publisher);

        Publisher publisher2 = buildPublisher();
        publisher2.setEmail("publisher2@publish.test");
        publisher2.setName("Best Books");
        publisherRepository.save(publisher2);

        List<Publisher> expectedPublishers = Arrays.asList(publisher, publisher2);
        List<Publisher> actualPublishers = publisherRepository.findAll();

        assertEquals(expectedPublishers, actualPublishers);
    }


    @Test
    @Transactional
    public void shouldUpdatePublisher() {
        Publisher publisherInitial = buildPublisher();
        publisherRepository.save(publisherInitial);

        // Update Publisher
        publisherInitial.setEmail("publisher2@publish.test");
        publisherInitial.setName("Best Books");
        publisherRepository.save(publisherInitial);

        Publisher actualPublisher = publisherRepository.findById(publisherInitial.getId()).orElse(new Publisher());

        assertEquals(publisherInitial.getName(), actualPublisher.getName());
        assertEquals(publisherInitial.getEmail(), publisherInitial.getEmail());
    }


    @Test
    @Transactional
    public void shouldDeletePublisher() {
        Publisher publisher = buildPublisher();
        publisherRepository.save(publisher);

        publisherRepository.deleteById(publisher.getId());

        Optional<Publisher> actualPublisher = publisherRepository.findById(publisher.getId());
        assertFalse(actualPublisher.isPresent());
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

}