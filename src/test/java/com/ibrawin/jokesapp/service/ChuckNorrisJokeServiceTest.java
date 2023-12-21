package com.ibrawin.jokesapp.service;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ChuckNorrisJokeServiceTest {

    @Mock
    private ChuckNorrisQuotes chuckNorrisQuotes;

    private ChuckNorrisJokeService chuckNorrisJokeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        chuckNorrisJokeService = new ChuckNorrisJokeService(chuckNorrisQuotes);
    }

    @ParameterizedTest
    @CsvSource({"0, First Quote", "1, Second Quote", "2, Third Quote"})
    void getJoke(int number, String expected) {
        List<String> quotes = List.of("First Quote", "Second Quote", "Third Quote");
        when(chuckNorrisQuotes.getRandomQuote())
                .thenReturn(quotes.get(number));

        String quote = chuckNorrisJokeService.getJoke();

        assertEquals(expected, quote);
        verify(chuckNorrisQuotes, times(1)).getRandomQuote();
    }
}