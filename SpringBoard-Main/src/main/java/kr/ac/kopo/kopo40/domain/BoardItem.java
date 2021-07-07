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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class BoardItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;

	@Column
	private String title;

	@Column
	private String date;

	@Column
	private String content;

	@Column
	private Integer viewCnt;
	
	//게시판과 1:多 관계로 연결
	@ManyToOne(optional = false)
	@JoinColumn(name = "board_id")
	private Board board;

	//게시글과 댓글을 1:多 관계로 연결
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "boardItem", fetch = FetchType.LAZY)
	private Collection<Comment> comments;

	public Collection<Comment> getComments() {
		if (comments == null) {
			comments = new ArrayList<Comment>();
		}
		return comments;
	}

	public void setBoardItems(Collection<Comment> comments) {
		this.comments = comments;
	}

	public void addBoardItem(Comment comment) {
		Collection<Comment> comments = getComments();
		comments.add(comment);
	}

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(Integer viewCnt) {
		this.viewCnt = viewCnt;
	}

	// @ManytoOne을 하고 getter, setter을 하면 자동으로 생기는 것이고, 부모를 지정
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
