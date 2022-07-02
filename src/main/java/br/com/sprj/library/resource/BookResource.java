package br.com.sprj.library.resource;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.sprj.library.dto.BookDTO;
import br.com.sprj.library.mapper.BookMapper;
import br.com.sprj.library.model.Book;
import br.com.sprj.library.repository.BookRepository;

@Path("/books")
public class BookResource {

	@Inject
	BookMapper mapper;

	@Inject
	BookRepository bookRepository;
	
	@GET
	public Response books() {
		List<Book> books = bookRepository.listAll();
		return Response.ok(mapper.booksToBookDTOs(books)).build();
	}

	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") Long id) {
		Book book = bookRepository.findById(id);
		return Response.ok(mapper.bookToBookDTO(book)).build();
	}

	@POST
	@Transactional
	public Response create(@Valid BookDTO bookDTO) {
		if ((bookRepository.findByIsbn(bookDTO.getBookIsbn())) != null ) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		
		Book book = mapper.bookDTOToBook(bookDTO);		
		
		bookRepository.persist(book);

		URI location = UriBuilder.fromResource(BookResource.class).path("/{id}").resolveTemplate("id", book.getId())
				.build();

		return Response.created(location).build();

	}
	
	@PUT
	@Transactional
	@Path("/{id}")
	public Response update(@PathParam("id") Long id, @Valid BookDTO bookDTO) {
		
		if ((bookRepository.findById(id)) != null) {
		
			Book book = bookRepository.findById(id);
			
			book.setIsbn(bookDTO.getBookIsbn());
			book.setTitle(bookDTO.getBookTitle());
			book.setSubTitle(bookDTO.getBookSubTitle());
			
			return Response.ok().build();
		}
		
		Book book = mapper.bookDTOToBook(bookDTO);
		bookRepository.persist(book);
		return Response.ok().build();
		
	}

	@DELETE
	@Transactional
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		
		if (bookRepository.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		Book book = bookRepository.findById(id);

		bookRepository.delete(book);

		return Response.ok().build();
	}

}