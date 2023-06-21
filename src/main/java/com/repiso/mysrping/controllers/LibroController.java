package com.repiso.mysrping.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.CrudMethods;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.repiso.mysrping.models.Libro;
import com.repiso.mysrping.repositories.LibroRepository;
import com.repiso.mysrping.services.LibroService;

import jakarta.validation.Valid;


//Notación para indicar que es un controlador. 
//Controller retorna una vista. RestController retorna XML, JSON o String
@Controller
//Notación para indicar el contexto de nuestros endpoint, es decir, /libro/nombreServicio
@RequestMapping("/libros")
//URL que permitimos que consuman nuestras APIS
//En caso de querer permitir todos los origentes ponemos en lugar de la URL un *
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {
    //Inyección de dependecias (crea una instancia cuando lo requiera)
    @Autowired 
    private LibroService libroService;

    @GetMapping(path = "/all")
    public String getLibros(Model model){
        ArrayList<Libro> libros = libroService.getLibros();
        model.addAttribute("libros", libros);
       return "index"; //sin la anotación ResponseBody retorna una vista
    }

     // @ResponseBody retorna una respuesta String, no una vista
    // @RequestParam parámetro GET o POST   
    @GetMapping(path="/create")
    public String create(Model model){
        model.addAttribute("libro", new Libro());
        return "form_add"; //retorna una vista
         
    }

    //Si el objeto contiene ID actualiza, en caso contrario agrega nuevo
    @PostMapping(path="/store")
    public String store (@ModelAttribute @Valid Libro libro, BindingResult bindingResult, RedirectAttributes redirectAttrs){
        //si contiene errores validación devuelve al formulario inicial. Maneja MethodArgumentNotValidException .
        if (bindingResult.hasErrors()) {
            return "form_add";
        }

        libroService.storeLibro(libro);

        //envía mensaje y atributos a la página de redirección
        redirectAttrs
                .addFlashAttribute("mensaje", "Libro agregado correctamente")
                .addFlashAttribute("clase", "success");
        
        return "redirect:/libros/all"; //redirige a una ruta
    }

    @GetMapping(path="/update/{id}")
    public String update (@PathVariable int id, Model model){
        Libro libro = libroService.getLibroById(id).get();
        model.addAttribute("libro", libro);
        return "form_edit";
    }

    @PostMapping(path= "/modify")
    public String actualizarProducto(@ModelAttribute @Valid Libro libro, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "form_edit";
        }

        libroService.storeLibro(libro);

         redirectAttrs
                .addFlashAttribute("mensaje", "Libro editado correctamente")
                .addFlashAttribute("clase", "success");
       
        return "redirect:/libros/all";
    }

    @GetMapping(path="/delete/{id}")
    public String delete (@PathVariable int id, Model model, RedirectAttributes redirectAttrs){
        libroService.deleteLibroById(id);

        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        
        return "redirect:/libros/all";
    }



   


/*
   // @ResponseBody vs ResponseEntity
    @GetMapping(path = "/all")
    public ResponseEntity<ArrayList<Libro>> getAll() { //ResponseEntity agrega códido HTTP de estado
        try {
            ArrayList<Libro> libros=libroService.getLibros();
            return new ResponseEntity<ArrayList<Libro>>(libros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getOne(@PathVariable int id) {
        try {
            Libro libro= libroService.getLibroById(id).get();
            return new ResponseEntity<>(libro, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping(path="/add")
    public ResponseEntity<?> create(@RequestParam Libro libro) {
        try {
            libroService.storeLibro(libro);
            return new ResponseEntity<>("Libro creado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestParam Libro dto) {
        try {
            Libro libro = libroService.getLibroById(id).get();
            libro.setTitulo(dto.getTitulo());
            libro.setEditorial(dto.getEditorial());
            libro.setPrecio(dto.getPrecio());

            libroService.storeLibro(libro);
            return new ResponseEntity<>("Libro actualizado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable int id) {
        try {
            libroService.deleteLibroById(id);
            return new ResponseEntity<>("Libro eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
   */

}
