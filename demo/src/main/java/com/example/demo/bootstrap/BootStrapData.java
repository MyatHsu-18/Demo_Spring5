package com.example.demo.bootstrap;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher=new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Pertersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driver Design","123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("rod","Jhonson");
        Book noEJB = new Book("J2EE Developement without EJB","3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books"+bookRepository.count());
        
        System.out.println("Number of Author="+authorRepository.count()+"/Author rod number of book="+rod.getBooks().size()+"/Author eric number of book="+eric.getBooks().size());
        
        System.out.println("Publisher count="+publisherRepository.count());
        System.out.println("Publisher number of books="+publisher.getBooks().size());
    }
}
