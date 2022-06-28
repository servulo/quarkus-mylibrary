package br.com.sprj.library.repository;

import javax.enterprise.context.ApplicationScoped;

import org.springframework.data.repository.CrudRepository;

import br.com.sprj.library.model.Book;

@ApplicationScoped
public interface BookRepositorySpring extends CrudRepository<Book, Long> {
	
}
