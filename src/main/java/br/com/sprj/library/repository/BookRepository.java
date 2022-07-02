package br.com.sprj.library.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.sprj.library.model.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

	public Book findByIsbn(String isbn) {
		return find("isbn", isbn).firstResult();
	}

}