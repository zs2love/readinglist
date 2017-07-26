package com.example.readinglist.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.readinglist.entity.Book;

public interface ReadingListRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByReader(String reader);

}
