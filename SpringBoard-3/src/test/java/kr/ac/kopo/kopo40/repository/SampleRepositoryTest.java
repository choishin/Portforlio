package kr.ac.kopo.kopo40.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.ac.kopo.kopo40.repo.SampleRepository;

@RunWith(SpringRunner.class)
	@SpringBootTest
	public class SampleRepositoryTest {
		
		@Autowired
		private SampleRepository sampleRepository;
		
		@Before
		public void before() {
			System.out.println("before");
		}	
		@After
		public void after() {
			System.out.println("after");
		}
		
		@Test
		public void findAll() {
			assertEquals(5, sampleRepository.count());
		}
		
		@Test
		public void equalTest() {
			assertEquals("a","a");
		}
		
		@Test
		@Ignore
		public void notEqualTest() {
			assertEquals("a","b");
		}
	}

