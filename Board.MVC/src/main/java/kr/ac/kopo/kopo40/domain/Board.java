package kr.ac.kopo.kopo40.domain;

public class Board {
	private int board_index;
	private String board_title;
	
	public int getBoard_index() {
		return board_index;
	}
	
	public int setBoard_index(int index) {
		return board_index = index;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String new_title) {
		this.board_title = new_title;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + board_index;
		result = prime * result + ((board_title == null) ? 0 : board_title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (board_index != other.board_index)
			return false;
		if (board_title == null) {
			if (other.board_title != null)
				return false;
		} else if (!board_title.equals(other.board_title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Board [board_index=" + board_index + ", board_name=" + board_title + ", board_contentsCnt="
				+  "]";
	}
	
}	