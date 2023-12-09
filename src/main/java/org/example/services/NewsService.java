package org.example.services;

import org.example.annotations.Bean;

@Bean(scope = "prototype")
public class NewsService {

    public void allNews(){
        System.out.println("Pokrenuta metoda allNews u Servisu!");
    }
}
