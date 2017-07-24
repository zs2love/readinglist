package com.example.readinglist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ReadingListController {

	private static final String reader = "craig";

	private AmazonProperties amazonProps;

	private ReadingListRepository readingListRepository;

	@Autowired
	public ReadingListController(AmazonProperties amazonProps, ReadingListRepository readingListRepository) {
		this.amazonProps = amazonProps;
		this.readingListRepository = readingListRepository;
	}

	@RequestMapping( path = "/readingList", method = RequestMethod.GET)
	public String readersBooks(Model model) {

		List<Book> readingList = readingListRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("books", readingList);
			model.addAttribute("reader", reader);
			model.addAttribute("amazonId", amazonProps.getAssociatedId());
		}
		return "readingList";
	}

	@RequestMapping(path = "/readingList",method = RequestMethod.POST)
	public String addToReadingList(Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/";
	}

}
