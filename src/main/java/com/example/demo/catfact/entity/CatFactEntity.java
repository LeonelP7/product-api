package com.example.demo.catfact.entity;

import com.example.demo.catfact.CatFact;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/*
This and subsequent classes are an implementation of external API integration and doesn’t really add functionality to the API
 */

@Entity
@Table(name = "cat_facts")
@AllArgsConstructor
@NoArgsConstructor
public class CatFactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "catfactJSON")
    private String catfactJSON;

    public CatFactEntity(CatFact catFact) {
        this.catfactJSON = convertToJSON(catFact);
    }

    //sertialization
    private String convertToJSON(CatFact catFact) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(catFact);
        }catch (Exception e) {
            throw new RuntimeException("JSON parsing error");
        }
    }

    public CatFact convertToCatFact(){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(catfactJSON, CatFact.class);
        }catch (Exception e) {
            throw new RuntimeException("JSON parsing error");
        }
    }
}
