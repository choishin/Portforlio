package kr.ac.kopo.kopo40.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo40.domain.Sample;


//<이 레파지토리가 다루려는 도메인, 식별자 변수타입> 앞으로는 implements 하는 클래스는 필요가 없음.
//제네릭 : 일반화를 하기위해서 제공되는 라이브러리. 특정 클래스의 기능을 추상화.
//안에 component annotation이 들어있음. (상속 받음) 
@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {

//, JpaSpecificationExecutor<Sample> {
//	Optional<Sample> findOneByTitle(String title);
//
////	//3번페이지에 100개씩, 
//	Page<Sample> findAllByTitle(String title, Pageable pageable);
//	
////	//전체를 리턴하는 것
//	List<Sample> findAllByTitle(String title);
}
