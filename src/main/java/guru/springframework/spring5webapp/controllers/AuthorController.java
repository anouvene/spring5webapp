package guru.springframework.spring5webapp.controllers;


import guru.springframework.spring5webapp.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Antoine Nouvene  on 5/18/17.
 */
@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping(value = "/authors")
    public String getBooks(Model model) {
       model.addAttribute("authors", this.authorRepository.findAll());

       return "authors";
    }
}
