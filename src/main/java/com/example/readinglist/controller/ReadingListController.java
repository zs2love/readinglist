package com.example.readinglist.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.readinglist.config.AmazonProperties;
import com.example.readinglist.entity.Book;
import com.example.readinglist.repository.ReadingListRepository;

@Controller
@RequestMapping("/")
public class ReadingListController {

	private static final String reader = "craig";

	private AmazonProperties amazonProps;

	private ReadingListRepository readingListRepository;

	private static final Logger logger = LoggerFactory.getLogger(ReadingListController.class);

	@Autowired
	public ReadingListController(AmazonProperties amazonProps, ReadingListRepository readingListRepository) {
		this.amazonProps = amazonProps;
		this.readingListRepository = readingListRepository;
	}

	@RequestMapping(path = "/readingList", method = RequestMethod.GET)
	public String readersBooks(Model model) {

		List<Book> readingList = readingListRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("books", readingList);
			model.addAttribute("reader", reader);
			model.addAttribute("amazonId", amazonProps.getAssociatedId());
		}
		return "readingList";
	}

	@RequestMapping(path = "/readingList", method = RequestMethod.POST)
	public String addToReadingList(Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/";
	}

	@RequestMapping(path = "/loginuser",method = RequestMethod.GET)
	public String loginuser() {
		logger.info("directing to home page.");
		return "redirect:/home";
	}

}
