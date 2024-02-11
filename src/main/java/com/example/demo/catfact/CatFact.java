package com.example.demo.catfact;

import lombok.Data;

/*
This and subsequent classes are an implementation of external API integration and doesn’t really add functionality to the API
 */

@Data
public class CatFact {

    private String fact;
    private int length;
}
