package br.com.sprj.library.resource;

import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import br.com.sprj.library.dto.BookDTO;
import br.com.sprj.library.mapper.BookMapper;
import br.com.sprj.library.model.Book;
import br.com.sprj.library.repository.BookRepository;
import br.com.sprj.library.repository.BookRepositorySpring;

@Path("/books")
public class BookResource {
	
	@Inject
	BookMapper mapper;

	@Inject
	BookRepository bookRepository;

	@Inject
	BookRepositorySpring bookRepositorySpring;

	@GET
	public List<BookDTO> list() {
		List<Book> books =  bookRepository.listAll();
		return mapper.booksToBookDTOs(books.stream());		
	}

	@GET
	@Path("/spring")
	public List<BookDTO> listSpring() {
		List<Book> books = (List<Book>) bookRepositorySpring.findAll();
		return mapper.booksToBookDTOs(books.stream());
	}

	@POST
	@Transactional
	public List<Book> add(Book book) {
		bookRepository.persist(book);
		return bookRepository.listAll();
	}

}