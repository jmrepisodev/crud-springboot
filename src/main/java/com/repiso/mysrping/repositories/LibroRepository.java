package com.repiso.mysrping.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.repiso.mysrping.models.Libro;

//Autoimplementado por Spring. Crea automáticamente los métodos para gestionar los datos
// CRUD refers Create, Read, Update, Delete
@Repository
public interface LibroRepository extends CrudRepository<Libro, Integer> {

    //método personalizado. Comienza por "findBy". Método abstracto
    public abstract ArrayList<Libro> findByEditorial(String editorial);
}
