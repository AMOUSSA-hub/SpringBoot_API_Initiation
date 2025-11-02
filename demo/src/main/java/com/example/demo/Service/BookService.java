package com.example.demo.Service;

import com.example.demo.DTO.BookDTO;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Writer;
import com.example.demo.Mapper.BookMapper;
import com.example.demo.Mapper.WriterMapper;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.WriterRepository;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final WriterRepository writerRepository;


    public BookService(BookRepository bookRepository, WriterRepository writerRepository) {
        this.bookRepository = bookRepository;
        this.writerRepository = writerRepository;
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
     * @param bookdto
     * @return
     */
    public BookDTO addBook ( BookDTO bookdto){

        Writer author;
        if(bookdto.author().id()==-1 || !writerRepository.existsById(bookdto.author().id()) ){
           author =  writerRepository.save(WriterMapper.toEntity(bookdto.author()));
        }else{
            author = writerRepository.findById(bookdto.author().id()).orElseThrow( () -> new RuntimeException("Ecrivain non trouvé !"));
        }
        Book book = BookMapper.toEntity(bookdto);
        book.setAuthor(author);

      return  BookMapper.toDTO(bookRepository.save(book)) ;

    }

    /**
     * Modifier les informations d'un livre.
     * @param bookdto
     * @return
     */
    public BookDTO updateBook ( BookDTO bookdto ){
        Book book= bookRepository.findById(bookdto.id()).orElseThrow( () -> new RuntimeException("Modification impossible ! Aucun livre avec cet id trouvé."));
        book.setTitle(bookdto.title());

        if(writerRepository.existsById(bookdto.author().id())){
            book.setAuthor(writerRepository.findById(bookdto.author().id()).orElseThrow(() -> new RuntimeException("Modification impossible ! Aucun livre avec cet id trouvé.")));
        }
        else{
            Writer newAuthor = WriterMapper.toEntity(bookdto.author());
            writerRepository.save(newAuthor);
            book.setAuthor(newAuthor);
        }
        
        book.setPublicationDate(bookdto.publicationDate());
        bookRepository.save(book);

        return BookMapper.toDTO(book);
    }
    /**
     * Supprimer un livre via son id.
     * @param id
     */
    public void deleteBook(int id){
        bookRepository.delete(bookRepository.findById(id).orElseThrow( () -> new RuntimeException("Supression impossible ! Aucun livre avec cet id enregistré.")));
    }

    /**
     * Récupérer tous les livres d'un écrivain.
     * @param id
     * @return
     */
    public Iterable<BookDTO> getBooksbyAuthor(int id){
            Writer writer = writerRepository.findById(id).orElseThrow( () -> new RuntimeException("Ecrivain introuvable ! "));    
            return BookMapper.toDTOs(writer.getBooks());
    }
}
