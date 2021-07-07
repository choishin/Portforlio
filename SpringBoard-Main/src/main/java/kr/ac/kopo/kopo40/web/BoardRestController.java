//package kr.ac.kopo.kopo40.web;
//
//import java.util.HashMap;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import kr.ac.kopo.kopo40.domain.Board;
//import kr.ac.kopo.kopo40.repo.BoardRepository;
//
//@RestController
//public class BoardRestController {
//
//	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
//	
//	@Autowired
//	private BoardRepository BoardRepository;
//	
////	@RequestMapping("/api/board/findOne")
////	public Board findOne(@RequestParam HashMap<String, String> map) {
////		int id = Integer.parseInt(map.get("id"));
////		String title = map.get("title");
////		logger.info("id: "+id);
////		logger.info("title: "+ title);
////		return BoardRepository.findById(id).get();
////	}
//	
//	@RequestMapping(value = "/hello")
//	public String helloSpringBoot(Model model) {
//		model.addAttribute("name","홍길동");
//		return "hello";
//	}
//}
