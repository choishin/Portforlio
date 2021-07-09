//package kr.ac.kopo.kopo40.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import kr.ac.kopo.kopo40.repository.CommentRepository;
//
//@Controller
//public class CommentController {
//   
//   @Autowired
//   private CommentRepository commentRepository;
//   
//   @RequestMapping(value = "/")
//   //@ResponseBody
//   public String selectAll(Model model) {
//      PageRequest pageable = PageRequest.of(0, 2);
//      Page<Board> page = boardRepository.findAll(pageable);
//      model.addAttribute("BoardList", page.getContent());
//   
//      return "BoardList";
//   }
//     
//}
