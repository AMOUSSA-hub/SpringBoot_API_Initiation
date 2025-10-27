package com.example.demo.Mapper;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.WriterDTO;
import com.example.demo.Entity.Book;

import java.util.LinkedList;
import java.util.List;

public class BookMapper {


    public static BookDTO toDTO(Book book){
        return new BookDTO(book.getId(), WriterMapper.toDTO(book.getAuthor()), book.getTitle(),book.getPublicationDate());
    }

    public static Book toEntity( BookDTO bookDTO){
        return new Book( bookDTO.title(),WriterMapper.toEntity(bookDTO.author()),bookDTO.publicationDate());
    }

    public static List<BookDTO> toDTOs(Iterable<Book> books){
        List<BookDTO> booksdtos = new LinkedList<>();

        for (Book b : books) {
           booksdtos.add(toDTO(b));
        }

        return booksdtos ;
    }

}
