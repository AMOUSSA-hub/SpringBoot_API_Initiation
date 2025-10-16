package com.example.demo.Service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.Entity.Book;
import com.example.demo.Mapper.BookMapper;
import com.example.demo.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /**
     * Récupérer l'ensemble des livres.
     * @return
     */
    public Iterable<BookDTO> getAllBooks(){
        Iterable<Book> books = bookRepository.findAll();
        return BookMapper.toDTOs(books);
    }

    /**
     * Trouver un livre avec son id.
     * @param id
     * @return
     */
    public BookDTO getBookById(int id){
        Book book = bookRepository.findById(id).orElseThrow( () -> new RuntimeException("Livre non trouvé"));
        return BookMapper.toDTO(book);
    }

    /**
     * Ajouter un livre.
     * @param book
     * @return
     */
    public BookDTO addBook ( BookDTO book){
      return  BookMapper.toDTO(bookRepository.save(BookMapper.toEntity(book))) ;

    }

    /**
     * Modifier les informations d'un livre.
     * @param bookdto
     * @return
     */
    public BookDTO updateBook ( BookDTO bookdto ){
        Book book= bookRepository.findById(bookdto.id()).orElseThrow( () -> new RuntimeException("Modification impossible ! Aucun livre avec cet id trouvé."));
        book.setTitle(bookdto.title());
        book.setAuthor(bookdto.author());
        book.setPublicationDate(bookdto.publicationDate());
        bookRepository.save(book);

        return BookMapper.toDTO(book);
    }

    public void deleteBook(int id){
        bookRepository.delete(bookRepository.findById(id).orElseThrow( () -> new RuntimeException("Supression impossible ! Aucun livre avec cet id enregistré.")));
    }
}
