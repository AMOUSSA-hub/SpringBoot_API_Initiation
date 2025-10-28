package com.example.demo.Controller;


import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.WriterDTO;
import com.example.demo.Service.BookService;
import com.example.demo.Service.WriterService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class WriterController {

    private final WriterService writerService;

    public WriterController(WriterService writerService){
        this.writerService = writerService;
    }

    /**
     * Endpoint pour récupérer l'ensemble des écrivains (GET).
     *
     * @return Les livres.
     */
     @GetMapping("/writers")
    public ResponseEntity<Iterable<WriterDTO>> getAllWriters(){
        return ResponseEntity.ok(writerService.getAllWriters());
    }



    /**
     * Endpoint pour rechercher un écrivain avec son id (GET).
     * @param id
     * @return l'écrivain souhaité.
     */
    @GetMapping("/writer/{id}")
    public ResponseEntity<WriterDTO> getWriter(@PathVariable int id){
        return ResponseEntity.ok(writerService.getWriterById(id));
    }



    /**
     * Endpoint pour enregister un écrivain (POST).
     * @param bookdto
     * @return l'écrivain qui vient d'être ajouté.
     */    
    @PostMapping("/writer")
    public ResponseEntity<WriterDTO> addWriter(@RequestBody WriterDTO writerDTO){
        return  ResponseEntity.status(HttpStatus.CREATED).body(writerService.addWriter(writerDTO));
    }

    /**
     * Endpoint pour modifier les informations d'un écrivian (PUT).
     * @param bookdto
     * @return l'écrivain avec les infos modifiés.
     */
    @PutMapping("/writer")
    public ResponseEntity<WriterDTO> updateWriter(@RequestBody WriterDTO writerDTO){
     return  ResponseEntity.ok(writerService.updateWriter(writerDTO));
    }



      /**
     * Endpoint pour supprimer un écrivain (DELETE).
     * @param id
     */
    @DeleteMapping("/writer/{id}")
    public ResponseEntity<Void> deleteWriter(@PathVariable int id){
        writerService.deleteWriter(id);
        return ResponseEntity.noContent().build();
    }




    
}
