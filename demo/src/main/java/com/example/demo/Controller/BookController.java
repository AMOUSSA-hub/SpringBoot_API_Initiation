package com.example.demo.Controller;

import com.example.demo.DTO.BookDTO;
import com.example.demo.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Endpoint pour récupérer l'ensemble des livres (GET).
     *
     * @return Les livres.
     */
    @GetMapping("/books")
    public ResponseEntity<Iterable<BookDTO>> getBook(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    /**
     * Endpoint pour rechercher un livre avec son id (GET).
     * @param id
     * @return le livre souhaité.
     */
    @GetMapping("/book/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable int id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    /**
     * Endpoint pour enregister un livre (POST).
     * @param bookdto
     * @return le livre qui vient d'être ajouté.
     */
    @PostMapping("/book")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookdto){
    return  ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookdto));
    }

    /**
     * Endpoint pour modifier les informations d'un livre (PUT).
     * @param bookdto
     * @return le livre avec les infos modifiés.
     */
    @PutMapping("/book")
    public ResponseEntity<BookDTO> updateBook( @RequestBody BookDTO bookdto){
        return ResponseEntity.ok(bookService.updateBook(bookdto));
    }

    /**
     * Endpoint pour supprimer un livre (DELETE).
     * @param id
     */
    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id){
        
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    
    }


    /**
     * Endpoint pour obtenir tous les livres écrits par un auteur en particulier (via son id) (GET).
     * @param id id de l'auteur.
     * @return
     */
    @GetMapping("/books/writer/{id}")
    public ResponseEntity<Iterable<BookDTO>> getBookOfAuthor(@PathVariable int id){
        return ResponseEntity.ok(bookService.getBooksbyAuthor(id));
    }


}
