package com.example.demo.DTO;

import java.sql.Date;
import java.time.LocalDate;

public record BookDTO (int id, String author, String title, Date publicationDate){}



