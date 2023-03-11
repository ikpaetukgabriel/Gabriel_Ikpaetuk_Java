package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PublisherController.class)
public class PublisherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublisherRepository publisherRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldAddPublisher() throws Exception {
        // Arrange ...
        Publisher publisherExpected = buildPublisher();
        Publisher publisherActual = buildPublisher();

        // Mock
        doReturn(publisherActual).when(publisherRepository).save(publisherExpected);
        String publisherAsString = mapper.writeValueAsString(publisherExpected);

        // Act ...
        mockMvc.perform(post("/publishers")
                        .content(publisherAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetPublisherById() throws Exception {
        // Arrange ...
        Publisher publisherExpected = buildPublisher();
        int expectedPublisherId = publisherExpected.getId();

        Publisher publisherActual = buildPublisher();

        // Mock
        doReturn(Optional.of(publisherActual)).when(publisherRepository).findById(expectedPublisherId);

        // Act ...
        mockMvc.perform(get("/publishers/".concat(String.valueOf(expectedPublisherId))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetPublishers() throws Exception {
        // Arrange ...
        Publisher publisher = buildPublisher();

        Publisher publisher2 = buildPublisher();
        publisher2.setId(1);
        publisher2.setName("Horizon Publishers");

        List<Publisher> publishers = Arrays.asList(publisher, publisher2);

        // Mock
        doReturn(publishers).when(publisherRepository).findAll();

        // Act ...
        mockMvc.perform(get("/publishers"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldUpdatePublisher() throws Exception {
        // Arrange ...
        Publisher publisherExpected = buildPublisher();
        Publisher publisherActual = buildPublisher();

        // Mock
        doReturn(publisherActual).when(publisherRepository).save(publisherExpected);
        String publisherAsString = mapper.writeValueAsString(publisherExpected);

        // Act ...
        mockMvc.perform(put("/publishers")
                        .content(publisherAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldDeletePublisher() throws Exception {
        // Act ...
        mockMvc.perform(delete("/publishers/0"))
                .andExpect(status().isNoContent());
    }


    private static Publisher buildPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Perd Publishers");
        publisher.setStreet("Broad St");
        publisher.setCity("Savannah");
        publisher.setState("GA");
        publisher.setPostalCode("31401");
        publisher.setPhone("912-555-5555");
        publisher.setEmail("perd@perdroasters.com");
        return publisher;
    }
}