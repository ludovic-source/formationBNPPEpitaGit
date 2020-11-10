package com.example.SpringBatchTP1;

// pour utliser un transformateur de données entre la lecture des données et l'écriture des données
// exemple transformer tout en majuscules

import org.springframework.batch.item.ItemProcessor;

public class BookItemProcessor implements ItemProcessor<BookDTO, BookDTO> {

    @Override
    public BookDTO process(BookDTO bookDTO) throws Exception {
        BookDTO bookResult = bookDTO;
        bookResult.setPublisher(bookDTO.getPublisher().toUpperCase());
        System.out.println("resultat transformation : " + bookResult.getPublisher());
        return bookResult;
    }
}
