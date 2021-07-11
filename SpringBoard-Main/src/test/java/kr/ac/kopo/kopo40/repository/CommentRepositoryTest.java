package kr.ac.kopo.kopo40.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.ac.kopo.kopo40.domain.Comment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Test
	void findAllByBoard_idAndPost_id() {
		List<Comment> comments = commentRepository.findOneByBoard_idAndPost_id(1,1);
		for (int i=0; i<comments.size(); i++) {
			Comment comment = new Comment();
			comment = comments.get(i);
			
			int index = comment.getId();
			String contents = comment.getContents();
			String date  = comment.getDateTime();
			
			System.out.println(index);
			System.out.println(contents);
			System.out.println(date);
			}

	}
	
//	@Test 
	void findAll() {
		
		List<Comment> comments = commentRepository.findOneByPost_id(1);
		for (int i=0; i<comments.size(); i++) {
		Comment comment = new Comment();
		comment = comments.get(i);
		
		int index = comment.getId();
		String contents = comment.getContents();
		String date  = comment.getDateTime();
		
		System.out.println(index);
		System.out.println(contents);
		System.out.println(date);
		}
		
	}
}

