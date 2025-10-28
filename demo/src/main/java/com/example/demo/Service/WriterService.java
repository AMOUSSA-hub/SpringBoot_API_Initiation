package com.example.demo.Service;


import org.springframework.stereotype.Service;
import com.example.demo.DTO.WriterDTO;
import com.example.demo.Entity.Writer;
import com.example.demo.Mapper.WriterMapper;
import com.example.demo.Repository.WriterRepository;


@Service
public class WriterService {



    private final WriterRepository writerRepository;

    public WriterService(WriterRepository writerRepository){
        this.writerRepository = writerRepository;
    }


    public Iterable<WriterDTO> getAllWriters(){
        return WriterMapper.toDTOs(writerRepository.findAll());
    }


    public WriterDTO getWriterById(int id){
        return WriterMapper.toDTO(writerRepository.findById(id).orElseThrow( () -> new RuntimeException("Modification impossible ! Aucun écrivain avec cet id trouvé.")));
    }


    public WriterDTO addWriter ( WriterDTO writerDTO){
        Writer writer  = writerRepository.save(WriterMapper.toEntity(writerDTO));
        return WriterMapper.toDTO(writer);
    }

    public WriterDTO updateWriter(WriterDTO writerDTO){
        Writer writer = writerRepository.findById(writerDTO.id()).orElseThrow( () -> new RuntimeException("Modification impossible ! Aucun écrivain avec cet id trouvé."));
        writer.setFirstName(writerDTO.firstName());
        writer.setLastName(writerDTO.lastName());
        writer.setBirthday(writerDTO.birthday());
        writerRepository.save(writer);
        return WriterMapper.toDTO(writer);
    }

    /**
     * Supprimer un écrivain via son id.
     * @param id
     */
    public void deleteWriter(int id){
        writerRepository.delete(writerRepository.findById(id).orElseThrow( () -> new RuntimeException("Supression impossible ! Aucun écrivain avec cet id enregistré.")));
    }

    
}
