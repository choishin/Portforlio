package kr.ac.kopo.kopo40.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.kopo40.dao.BoardItemDaoImpl;
import kr.ac.kopo.kopo40.domain.BoardItem;

public class BoardItemServiceImpl implements BoardItemService {
	static BoardItemDaoImpl boardItemDao;
	private static BoardItemServiceImpl instance = null;

	public BoardItemServiceImpl getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new BoardItemServiceImpl();
		}
		return instance;
	}

	@Override
	public void create(BoardItem boardItem) {
		// TODO Auto-generated method stub
		boardItemDao.create(boardItem);
	}

	@Override
	public List<BoardItem> selectAll(int board_id) {
		// TODO Auto-generated method stub
		List<BoardItem> boardItemAll = new ArrayList<BoardItem>();
		try {
			boardItemDao = BoardItemDaoImpl.getInstance();
			boardItemAll = boardItemDao.selectAll(board_id);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardItemAll;
	}

	@Override
	public BoardItem selectOne(int board_id, int id) {
		// TODO Auto-generated method stub
		try {
			boardItemDao = BoardItemDaoImpl.getInstance();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardItemDao.selectOne(board_id, id);
	}

	@Override
	public void update(BoardItem boardItem) {
		// TODO Auto-generated method stub
		boardItemDao.update(boardItem);
	}

	@Override
	public void delete(int board_id, int id) {
		// TODO Auto-generated method stub
		boardItemDao.delete(board_id, id);
	}

	public int totalCount(int board_id) {
		return boardItemDao.totalCount(board_id);
	}

	public int getMax(int board_id) {
		return boardItemDao.getMax(board_id);
	}

	public void addViewCount(int board_id, int id) {
		boardItemDao.addViewCount(board_id, id);
	}

	@Override
	public List<BoardItem> searchItems(String startNum, String countPage,String keyword) {
		// TODO Auto-generated method stub
		List<BoardItem> boardItems = new ArrayList<BoardItem>();
		try {
			boardItemDao = BoardItemDaoImpl.getInstance();
			boardItems = boardItemDao.searchItems(startNum,countPage,keyword);
		} catch (Exception e) {
			System.out.println(e);
		}
		return boardItems;
	}
	
	public List<Integer> searchPaging (String startNum, String countPage, String keyword) {
		List<Integer> forReturn = new ArrayList<Integer>();
		try {
			boardItemDao = BoardItemDaoImpl.getInstance();
			int searchCnt = boardItemDao.searchCount(keyword);
			
			//startNum : 시작할 게시글의 번호 (파라미터로 받을 수) 
			//fromPT : 출력 시작할 게시글의 번호			 
			String fromPT = "1";
			if (startNum == null || startNum == "") {
				fromPT = "1";
			} else if (Integer.parseInt(startNum) < 0) {
				fromPT = "1";
			} else {
				fromPT = startNum;
			}
			
			//countPage : 출력할 게시글 수 (파라미터로 받을 수)
			//cntPT : 한 페이지에 출력될 게시글 수 (10로 설정)
			String cntPT = "10";
			if (countPage == null || countPage == "") {
				cntPT = "10";
			} else if (countPage.equals("0")) {
				cntPT = "10";
			} else {
				cntPT = countPage;
			}
			
			//fromPT가 음수이거나, 9이상이라면 -> 예외처리
			for (int i = 0; i < fromPT.length(); i++) {
				char ch = fromPT.charAt(i);
				if (ch < '0' || ch > '9') {
					throw new Exception();
				}
			}
			//cntPT가 음수이거나, 9이상이라면 -> 예외처리
			for (int i = 0; i < cntPT.length(); i++) {
				char ch = cntPT.charAt(i);
				if (ch < '0' || ch > '9') {
					throw new Exception();
				}
			}

			//totalCnt : 출력할 총 게시글의 수
			//시작 게시글의 수가 총 게시글의 수보다 크다면, 총 게시글의 수에서 출력할 게시글을 나눈 나머지 만큼으로 세팅.
			if (Integer.parseInt(fromPT) > searchCnt) {
				fromPT = Integer.toString(searchCnt - (searchCnt % Integer.parseInt(cntPT)));
			}
			// pageNum : 현재 페이지 수 (default=1)
			int pageNum = ((searchCnt - 1) / Integer.parseInt(cntPT)) + 1;
			// printPage : 하단에 페이징으로 출력할 숫자의 개수
			int printPage = 10;
			// startPage : 한 화면에 출력될 페이지 중 '시작' 페이지 숫자
			int startPage = ((pageNum - 1) / 10) * 10 + 1;
			// endPage : 한 화면에 출력될 페이지 중 '마지막' 페이지 수
			int endPage = startPage + printPage - 1;
			// 총 페이지 수
			int totalPage = searchCnt / Integer.parseInt(cntPT);
			// 남은 게시글 수가 한 화면에 출력해야 할 게시글 수보다 작더라도 페이지 수는 1만큼 늘어나야 함
			if (searchCnt > Integer.parseInt(cntPT) * totalPage) {
				totalPage++;
			}
			// 현재 페이지 수가 총 페이지 수보다 큰 경우 현재 페이지를 총 페이지 번호로 치환
			if (pageNum > totalPage) {
				pageNum = totalPage;
			}
			//마지막 페이지 수가 총 페이지 수보다 크다면 총 페이지수로 치환
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			//previous : 이전 페이지로 가기
			int previous = Integer.parseInt(startNum) - Integer.parseInt(countPage);
			//next : 다음 페이지로 가기
			int next = Integer.parseInt(startNum) + Integer.parseInt(countPage);
			
			if (previous < 1) {
				previous = 1;
			}

			if (next > searchCnt) {
				next = searchCnt;
			}

			forReturn.add(searchCnt);
			forReturn.add(Integer.parseInt(fromPT));
			forReturn.add(Integer.parseInt(cntPT));
			forReturn.add(pageNum);
			forReturn.add(startPage);
			forReturn.add(endPage);
			forReturn.add(totalPage);
			forReturn.add(previous);
			forReturn.add(next);

		} catch (Exception e) {
			System.out.println(e);
		}
		return forReturn;
	}

	public List<BoardItem> listItems(int startNum, int countPage, int board_id) {
		try {
			boardItemDao = BoardItemDaoImpl.getInstance();
		} catch (Exception e) {
			System.out.println(e);
		}
		return boardItemDao.listItems(startNum, countPage, board_id);
	}

	public List<Integer> listPaging(String startNum, String countPage, int board_id) {
		List<Integer> forReturn = new ArrayList<Integer>();
		try {
			boardItemDao = BoardItemDaoImpl.getInstance();
			int totalCnt = boardItemDao.totalCount(board_id);
			
			//startNum : 시작할 게시글의 번호 (파라미터로 받을 수) 
			//fromPT : 출력 시작할 게시글의 번호			 
			String fromPT = "1";
			if (startNum == null || startNum == "") {
				fromPT = "1";
			} else if (Integer.parseInt(startNum) < 0) {
				fromPT = "1";
			} else {
				fromPT = startNum;
			}
			
			//countPage : 출력할 게시글 수 (파라미터로 받을 수)
			//cntPT : 한 페이지에 출력될 게시글 수 (10로 설정)
			String cntPT = "10";
			if (countPage == null || countPage == "") {
				cntPT = "10";
			} else if (countPage.equals("0")) {
				cntPT = "10";
			} else {
				cntPT = countPage;
			}
			
			//fromPT가 음수이거나, 9이상이라면 -> 예외처리
			for (int i = 0; i < fromPT.length(); i++) {
				char ch = fromPT.charAt(i);
				if (ch < '0' || ch > '9') {
					throw new Exception();
				}
			}
			//cntPT가 음수이거나, 9이상이라면 -> 예외처리
			for (int i = 0; i < cntPT.length(); i++) {
				char ch = cntPT.charAt(i);
				if (ch < '0' || ch > '9') {
					throw new Exception();
				}
			}

			//totalCnt : 출력할 총 게시글의 수
			//시작 게시글의 수가 총 게시글의 수보다 크다면, 총 게시글의 수에서 출력할 게시글을 나눈 나머지 만큼으로 세팅.
			if (Integer.parseInt(fromPT) > totalCnt) {
				fromPT = Integer.toString(totalCnt - (totalCnt % Integer.parseInt(cntPT)));
			}

			// pageNum : 현재 페이지 수 (default=1)
			int pageNum = ((totalCnt - 1) / Integer.parseInt(cntPT)) + 1;
			// printPage : 하단에 페이징으로 출력할 숫자의 개수
			int printPage = 10;
			// startPage : 한 화면에 출력될 페이지 중 '시작' 페이지 숫자
			int startPage = ((pageNum - 1) / 10) * 10 + 1;
			// endPage : 한 화면에 출력될 페이지 중 '마지막' 페이지 수
			int endPage = startPage + printPage - 1;
			// 총 페이지 수
			int totalPage = totalCnt / Integer.parseInt(cntPT);
			// 남은 게시글 수가 한 화면에 출력해야 할 게시글 수보다 작더라도 페이지 수는 1만큼 늘어나야 함
			if (totalCnt > Integer.parseInt(cntPT) * totalPage) {
				totalPage++;
			}
			// 현재 페이지 수가 총 페이지 수보다 큰 경우 현재 페이지를 총 페이지 번호로 치환
			if (pageNum > totalPage) {
				pageNum = totalPage;
			}
			//마지막 페이지 수가 총 페이지 수보다 크다면 총 페이지수로 치환
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			//previous : 이전 페이지로 가기
			int previous = Integer.parseInt(startNum) - Integer.parseInt(countPage);
			//next : 다음 페이지로 가기
			int next = Integer.parseInt(startNum) + Integer.parseInt(countPage);
			
			if (previous < 1) {
				previous = 1;
			}

			if (next > totalCnt) {
				next = totalCnt;
			}

			forReturn.add(totalCnt);
			forReturn.add(Integer.parseInt(fromPT));
			forReturn.add(Integer.parseInt(cntPT));
			forReturn.add(pageNum);
			forReturn.add(startPage);
			forReturn.add(endPage);
			forReturn.add(totalPage);
			forReturn.add(previous);
			forReturn.add(next);

		} catch (Exception e) {
			System.out.println(e);
		}
		return forReturn;
	}

}
