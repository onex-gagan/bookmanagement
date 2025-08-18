package org.example.bookmanagement.mapper;

import org.example.bookmanagement.entity.BookEntity;
import org.example.bookmanagement.model.Book;
import org.example.bookmanagement.model.BookCreateRequest;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    private BookMapper() {}
    public static Book toApiBook(BookEntity entity) {
        if (entity == null) {
            return null;
        }

        Book apiBook = new Book();
        apiBook.setBookId(entity.getId() != null ? entity.getId().intValue() : null);
        apiBook.setBookName(entity.getTitle());
        apiBook.setAuthor(entity.getAuthor());

        if (entity.getUser() != null) {
            apiBook.setUserId(entity.getUser().getId() != null ? entity.getUser().getId().intValue() : null);
        }

        if (entity.getPrice() != null) {
            apiBook.setPriceId(entity.getPrice().getId() != null ? entity.getPrice().getId().intValue() : null);
        }

        return apiBook;
    }

    public static List<Book> toApiBookList(List<BookEntity> entities) {
        return entities.stream()
                .map(BookMapper::toApiBook)
                .collect(Collectors.toList());
    }

    public static BookEntity toEntity(BookCreateRequest request) {
        if (request == null) {
            return null;
        }
        return BookEntity.builder()
                .title(request.getBookName())
                .author(request.getAuthor())
                .build();
    }
}
