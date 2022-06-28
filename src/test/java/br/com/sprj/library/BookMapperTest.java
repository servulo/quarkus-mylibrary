package br.com.sprj.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.com.sprj.library.dto.BookDTO;
import br.com.sprj.library.mapper.BookMapper;
import br.com.sprj.library.model.Book;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BookMapperTest {

	@Test
	public void shouldMapBookToBookDTO() {
		/*
		Book book = new Book(1L, "Book Four", "The Fourth Book");
		BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDTO(book);
		
		assertNotNull(bookDTO);
		assertEquals(bookDTO.getTitle(), "Book Four");
		assertEquals(bookDTO.getSubTitle(), "The Fourth Book");
		*/
	}

}