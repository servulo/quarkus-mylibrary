package br.com.sprj.library.mapper;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import br.com.sprj.library.dto.BookDTO;
import br.com.sprj.library.model.Book;

@ApplicationScoped
@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface BookMapper {

	@Mapping(target = "bookId", source = "id")
	@Mapping(target = "bookIsbn", source = "isbn")
	@Mapping(target = "bookTitle", source = "title")
	@Mapping(target = "bookSubTitle", source = "subTitle")
	BookDTO bookToBookDTO(Book book);

	@InheritInverseConfiguration
	@Mapping(target = "id", ignore = true)
	Book bookDTOToBook(BookDTO bookDTO);

	List<BookDTO> booksToBookDTOs(List<Book> books);

}