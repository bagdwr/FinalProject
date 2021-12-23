package com.example.finalproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    private Integer id;
    private String name;
    private String address;
    private List<Book> books;
}
