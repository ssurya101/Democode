package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Document
@Data
@NoArgsConstructor
public class Person {
    @Id
    private UUID id;
    private String name;
    public Person(@JsonProperty("name")String name,
                  @JsonProperty("id") UUID id) {
        this.name = name;
        this.id = id;
    }
}
