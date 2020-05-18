package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by antoine nouvene on 16/05/20.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        // Authors and books
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        this.authorRepository.save(eric);
        this.bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        this.authorRepository.save(rod);
        this.bookRepository.save(noEJB);

        // Publishers and books
        Publisher hachetteLivre = new Publisher("Hachette Livre", "58, rue Jean Bleuzen", "Vanves",
                "Hauts-de-Seine", "92170");
        Publisher flammarion = new Publisher("Ernest Flammarion", "87, quai Panhard-et-Levassor", "Paris",
                "Île-de-France", "75647");

        ddd.setPublisher(hachetteLivre);
        noEJB.setPublisher(hachetteLivre);
        hachetteLivre.getBooks().add(ddd);
        hachetteLivre.getBooks().add(noEJB);
        this.publisherRepository.save(hachetteLivre);

        this.publisherRepository.save(flammarion);

        System.out.println("Number of Books: " + this.bookRepository.count());
        System.out.println("Number of editors: " +  this.publisherRepository.count());
        System.out.println("The publisher " + hachetteLivre.getName() + " has: " + hachetteLivre.getBooks().size() + " books");

        if ((flammarion.getBooks().size() > 0)) {
            System.out.println("The publisher " + flammarion.getName() + " has: " + flammarion.getBooks().size() + " books");
        } else {
            System.out.println("The publisher " + flammarion.getName() + " has: " + 0 + " books");
        }


    }
}
