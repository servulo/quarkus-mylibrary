package br.com.sprj.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import br.com.sprj.library.dto.BookDTO;
import br.com.sprj.library.mapper.BookMapper;
import br.com.sprj.library.model.Book;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BookMapperTest {
	
	@Inject
	BookMapper mapper;

	@Test
	public void shouldMapBookToBookDTO() {
		
		Book book = new Book(1L, "1234567890", "Book Four", "The Fourth Book");
		BookDTO bookDTO = mapper.bookToBookDTO(book);
		assertNotNull(bookDTO);
		assertEquals(bookDTO.getBookId(), 1L);
		assertEquals(bookDTO.getBookIsbn(), "1234567890");
		assertEquals(bookDTO.getBookTitle(), "Book Four");
		assertEquals(bookDTO.getBookSubTitle(), "The Fourth Book");
	}
	
	@Test
	public void shouldMapBookDTOToBook() {
		
		BookDTO bookDTO = new BookDTO(1L, "1234567890", "Book Four", "The Fourth Book"); 
		Book book = mapper.bookDTOToBook(bookDTO);
		assertNotNull(book);
		assertEquals(book.getId(), null);
		assertEquals(book.getIsbn(), "1234567890");
		assertEquals(book.getTitle(), "Book Four");
		assertEquals(book.getSubTitle(), "The Fourth Book");
	}
	
	@Test
	public void shouldMapListBookToListBookDTO() {
	}

}