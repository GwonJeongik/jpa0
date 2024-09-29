package jpa0.section8.first;

import jakarta.persistence.Entity;

@Entity
public class Book extends Item{

    private Long isbn;
    private String author;
}
