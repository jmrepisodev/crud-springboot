package com.repiso.mysrping.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


//Notación para indicar que es una entidad
@Entity
//Tabla que corresponde a esta entidad
@Table(name = "libros")
public class Libro{

    //clave primaria de la tabla
    @Id
    //Se le indica que el campo ID es Autonumerico
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 1, max = 50, message = "El nombre debe medir entre 1 y 50")
    private String titulo;

    @NotEmpty(message = "El autor es obligatorio")
    @Size(min = 1, max = 50, message = "El nombre debe medir entre 1 y 50")
    private String autor;

    @NotEmpty(message = "La editorial es obligatoria")
    @Size(min = 1, max = 50, message = "El nombre debe medir entre 1 y 50")
    private String editorial;

    @NotNull(message = "Debes especificar el precio")
    @Min(value = 1, message = "El precio mínimo es 1")
    private float precio;




    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
   

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public String getEditorial() {
        return this.editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }


}