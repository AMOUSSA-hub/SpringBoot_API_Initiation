package com.example.demo.Entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Writer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    
    private int id;
    @Column(nullable = false)
    private  String firstName;

    @Column(nullable = false)
    private String lastName;

    private Date birthday;

    @OneToMany (mappedBy ="author", cascade = CascadeType.ALL)
    private List<Book> books;

    protected Writer(){}



    public Writer(String firstName, String lastName, Date birthday){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) { this.lastName = lastName;}


    public String getFirstName() { return this.firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public Date getBirthday() { return this.birthday; }
    public void setBirthday( Date birthday) { this.birthday = birthday; }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
