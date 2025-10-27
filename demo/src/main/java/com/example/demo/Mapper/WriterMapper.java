package com.example.demo.Mapper;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.WriterDTO;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Writer;

import java.util.LinkedList;
import java.util.List;

public class WriterMapper {


    public static WriterDTO toDTO(Writer writer){
        return new WriterDTO(writer.getId(), writer.getFirstName(), writer.getLastName(), writer.getBirthday());
    }

    public static Writer toEntity( WriterDTO writerDTO){
        return new Writer( writerDTO.firstName(),writerDTO.lastName(),writerDTO.birthday());
    }

    public static List<WriterDTO> toDTOs(Iterable<Writer> writers){
        List<WriterDTO> writerdtos = new LinkedList<>();

        for (Writer w : writers) {
            writerdtos.add(toDTO(w));
        }

        return writerdtos ;
    }


}
