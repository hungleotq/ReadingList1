package readinglist.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.metrics.CounterService;
//import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import readinglist.entity.Book;
import readinglist.repository.ReadingListRepository;

@Slf4j
@Controller
@RequestMapping("/readingList")
public class ReadingListController {

	
	private ReadingListRepository readingListRepository;
	
//	private CounterService counterService;
//	
//	private GaugeService gaugeService;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}
	
	@GetMapping("/{reader}")
	public String readersBooks(@PathVariable("reader") String reader, Model model) {
		log.info("reading list");
//		counterService.increment("reader.access.count");
		List<Book> readingList = readingListRepository.findByReader(reader);
		model.addAttribute("books", readingList);
		return "readingList"; 
	}
	
	@PostMapping("/{reader}")
	public String addToReadingList(@PathVariable String reader, Book book) {
		book.setReader(reader);
		log.info("insert book " + book.toString());	
//		counterService.increment("reader.add.count");
//		gaugeService.submit("reader.add.time", System.currentTimeMillis());
		readingListRepository.save(book);
		return "redirect:/readingList/{reader}";
	}
}
