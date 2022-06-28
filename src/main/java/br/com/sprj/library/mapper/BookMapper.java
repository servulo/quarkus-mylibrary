package br.com.sprj.library.mapper;

import java.util.List;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import br.com.sprj.library.dto.BookDTO;
import br.com.sprj.library.model.Book;

@ApplicationScoped
@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface BookMapper {

	BookDTO bookToBookDTO(Book book);

	Book bookDTOToBook(BookDTO bookDTO);
	
	List<BookDTO> booksToBookDTOs(Stream<Book> books);

}