package com.example.demo.Repository;


import com.example.demo.Entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends  CrudRepository<Book, Integer> {


    @Override
    Iterable<Book> findAll();


    Optional<Book> findById(int aLong);

    List<Book> findByAuthorContaining(String keyword);




}

