package kr.ac.kopo.kopo40.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo40.domain.Sample;

@Repository
//<이 레파지토리가 다루려는 도메인, 식별자 변수타입>
//앞으로는 implements 하는 클래스는 필요가 없음.
public interface SampleRepository extends JpaRepository<Sample, Long>{

}
