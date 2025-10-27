package com.example.demo.Repository;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Writer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WriterRepository extends CrudRepository<Writer, Integer> {


    @Override
    Iterable<Writer> findAll();


    Optional<Writer> findById(int aLong);



}
