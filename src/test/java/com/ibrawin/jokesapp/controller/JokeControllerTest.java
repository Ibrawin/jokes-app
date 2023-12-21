package com.ibrawin.jokesapp.controller;

import com.ibrawin.jokesapp.service.JokeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class JokeControllerTest {

    @Mock
    private Model model;

    @Mock
    private JokeService jokeService;

    private JokeController jokeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jokeController = new JokeController(jokeService);
        mockMvc = MockMvcBuilders.standaloneSetup(jokeController).build();
    }

    @ParameterizedTest
    @CsvSource({"0, Joke 1", "1, Joke 2", "2, Joke 3", "3, Joke 4", "4, Joke 5"})
    void getJoke(int number, String expected) {
        List<String> jokes = List.of("Joke 1", "Joke 2", "Joke 3", "Joke 4", "Joke 5");
        when(jokeService.getJoke())
                .thenReturn(jokes.get(number));
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        String viewName = jokeController.getJoke(model);

        assertEquals("index", viewName);
        verify(jokeService, times(1)).getJoke();
        verify(model, times(1)).addAttribute(eq("joke"), captor.capture());
        String joke = captor.getValue();
        assertEquals(expected, joke);
    }

    @Test
    void mockMvcJokes() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}