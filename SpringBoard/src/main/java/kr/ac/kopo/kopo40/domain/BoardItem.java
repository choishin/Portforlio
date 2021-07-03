package kr.ac.kopo.kopo40.domain;
//게시판 하나 하나의 객체
public class BoardItem {
	private int id;
	private String title;
	private String date;
	private String content;
	private int viewcnt;

	// 생성자
//	public BoardItem(int id, String title, String date, String content, int viewcnt) {
//		this.id = id;
//		this.title = title;
//		this.date = date;
//		this.content = content;
//		this.viewcnt = viewcnt;
//	}
	
	// GetterSetter
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	public int getViewcnt() {
		return viewcnt;
	}
	
	public void setViewcnt(int viewcnt) {
		this.viewcnt= viewcnt;
	}

}
