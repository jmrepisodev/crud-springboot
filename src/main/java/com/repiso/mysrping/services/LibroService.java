package com.repiso.mysrping.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repiso.mysrping.models.Libro;
import com.repiso.mysrping.repositories.LibroRepository;

import jakarta.transaction.Transactional;

@Service
//Asegura que los datos permanezcan inalterados hasta que la transacción termine
@Transactional
public class LibroService {

    //Inyección de dependecias (crea una instancia cuando lo requiera para manejar los datos)
    @Autowired
    LibroRepository libroRepository;

    public ArrayList<Libro> getLibros(){
       return (ArrayList<Libro>) libroRepository.findAll();
    }

    //Transaccional indica que la transacción o se completa entera o se hace rollback
	@Transactional
    public Libro storeLibro(Libro libro){
        return libroRepository.save(libro);
        
    }

     public Optional<Libro> getLibroById(int id){
        return libroRepository.findById(id);
    }


    public ArrayList<Libro>  getLibroByEditorial(String editorial) {
        return libroRepository.findByEditorial(editorial);
    }

    public boolean deleteLibroById(int id) {
        try{
            libroRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }

    }

}
