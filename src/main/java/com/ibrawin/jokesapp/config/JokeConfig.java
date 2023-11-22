package com.ibrawin.jokesapp.config;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JokeConfig {

    @Bean
    ChuckNorrisQuotes getChuckNorrisQuotes() {
        return new ChuckNorrisQuotes();
    }
}
