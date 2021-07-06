package kr.ac.kopo.kopo40.board.repo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javassist.tools.reflect.Sample;

@Mapper
@Repository
public interface SampleMapper {

	List<Sample> findAll();
}
