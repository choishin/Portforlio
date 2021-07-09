package kr.ac.kopo.kopo40.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "board", fetch = FetchType.LAZY)
	private Collection<BoardItem> boardItems;

	public Collection<BoardItem> getBoardItems() {
		if (boardItems == null) {
			boardItems = new ArrayList<BoardItem>();
		}
		return boardItems;
	}

	public void setBoardItems(Collection<BoardItem> boardItems) {
		this.boardItems = boardItems;
	}

	public void addBoardItem(BoardItem boardItem) {
		Collection<BoardItem> boardItems = getBoardItems();
		boardItems.add(boardItem);
	}

}
