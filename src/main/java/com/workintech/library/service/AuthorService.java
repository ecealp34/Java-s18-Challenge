package com.workintech.library.service;

import com.workintech.library.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(int id);
    Author save(Author author);
    void delete(Author author);
}
