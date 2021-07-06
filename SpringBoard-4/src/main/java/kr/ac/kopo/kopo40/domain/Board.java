package kr.ac.kopo.kopo40.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String title;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(cascade=CascadeType.ALL, mappedBy="board")
	private Collection<BoardItem> BoardItems;
	
	public Collection<BoardItem> getBoardItems() {
		if( BoardItems == null) {
			BoardItems = new ArrayList<BoardItem>();
		}
		return BoardItems;
	}
	public void setBoardItems(Collection<BoardItem> boardItems) {
		this.BoardItems = boardItems;
	}
	
	public void addBoardItem(BoardItem boardItem) {
		Collection<BoardItem> boardItems = getBoardItems();
		boardItems.add(boardItem);
	}

}
