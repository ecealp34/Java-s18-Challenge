package com.workintech.library.service;


import com.workintech.library.entity.Book;

import java.util.List;

public interface BookService{
    List<Book> findAll();
    Book findById(int id);
    Book save(Book book);
    void delete(Book book);
}
