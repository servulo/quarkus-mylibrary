package br.com.sprj.library.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

	private Long bookId;

	@NotBlank(message = "ISBN may not be blank")
	@Size(min = 13, max = 13, message = "ISBN should have 13 characters")
	private String bookIsbn;

	@NotBlank(message = "Title may not be blank")
	@Size(min = 10, message = "Title should have at least 10 characters")
	private String bookTitle;

	@NotBlank(message = "Subtitle may not be blank")
	@Size(min = 10, message = "Subtitle should have at least 10 characters")
	private String bookSubTitle;

}