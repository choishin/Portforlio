package kr.ac.kopo.kopo40.web;


////<-이 어노테이션을 보고 Controller라는 것을 알게됨
//@Controller
//public class BoardController {
//
////<-스프링이 이 주석을 해석해서 new를 하지 않아도 스프링이 알아서 객체를 만들어줌 (new BoardServiceImpl 이라는 것에서 자유로워짐)
//	@Autowired
//	private BoardRepository boardRepository;
//
//	@RequestMapping(value = "/board/list")
//	@ResponseBody
//	public List<Board> list(Model model) {
//		return boardRepository.findAll();
//	}
//
//}