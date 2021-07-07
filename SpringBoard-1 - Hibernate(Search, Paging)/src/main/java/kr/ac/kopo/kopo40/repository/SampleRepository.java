package kr.ac.kopo.kopo40.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo40.domain.Sample;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long>, JpaSpecificationExecutor<Sample> {
	Optional<Sample> findOneByTitle(String title);

	Page<Sample> findAllByTitle(String title, Pageable pageable);

	List<Sample> findAllByTitle(String title);
}
