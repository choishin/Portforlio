package kr.ac.kopo.kopo40.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sample {
	@Id
	@GeneratedValue
	@Column
	private long id;
	//해당 데이터 베이스를 컬럼으로 처리하겠다는 뜻. 여러개를 추가해도 됨 
	@Column
	private String title;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	

}
