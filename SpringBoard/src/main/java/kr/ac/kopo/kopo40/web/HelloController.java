package kr.ac.kopo.kopo40.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.kopo40.service.BoardService;

@Controller
public class HelloController {
	@RequestMapping(value = "/hello")
	public String helloSpringBoot(Model model) {
		model.addAttribute("name", "홍길동");
		return "hello";
	}

	// <-이 어노테이션을 보고 Controller라는 것을 알게됨
	@Controller
	public class BoardController {
		// <-스프링이 이 주석을 해석해서 new를 하지 않아도 스프링이 알아서 객체를 만들어줌 (new BoardServiceImpl 이라는 것에서
		// 자유로워짐)
		@Autowired
		private BoardService boardService;

		@RequestMapping(value = "/boardList")
		public String boardList(Model model) {

			model.addAttribute("name", "홍길동");
			return "boardList";
		}
	}

}