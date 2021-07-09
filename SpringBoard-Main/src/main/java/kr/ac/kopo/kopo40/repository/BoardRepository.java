package kr.ac.kopo.kopo40.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.kopo40.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>, JpaSpecificationExecutor<Board> {
	Optional<Board> findOneById(String board_id);
	
}