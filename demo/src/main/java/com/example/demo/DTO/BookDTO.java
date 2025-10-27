package com.example.demo.DTO;



import com.example.demo.Entity.Writer;

import java.sql.Date;
import java.time.LocalDate;

public record BookDTO (int id, WriterDTO author, String title, Date publicationDate){}



