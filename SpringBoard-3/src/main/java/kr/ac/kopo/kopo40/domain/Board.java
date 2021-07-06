package kr.ac.kopo.kopo40.domain;


public class Board {
	private long board_index;
	private String board_title;

	public long getBoard_index() {
		return board_index;
	}

	public long setBoard_index(long index) {
		return board_index = index;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String new_title) {
		this.board_title = new_title;
	}

}
