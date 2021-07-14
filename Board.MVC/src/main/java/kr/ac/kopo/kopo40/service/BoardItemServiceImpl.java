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
			
			//startNum : ������ �Խñ��� ��ȣ (�Ķ���ͷ� ���� ��) 
			//fromPT : ��� ������ �Խñ��� ��ȣ			 
			String fromPT = "1";
			if (startNum == null || startNum == "") {
				fromPT = "1";
			} else if (Integer.parseInt(startNum) < 0) {
				fromPT = "1";
			} else {
				fromPT = startNum;
			}
			
			//countPage : ����� �Խñ� �� (�Ķ���ͷ� ���� ��)
			//cntPT : �� �������� ��µ� �Խñ� �� (10�� ����)
			String cntPT = "10";
			if (countPage == null || countPage == "") {
				cntPT = "10";
			} else if (countPage.equals("0")) {
				cntPT = "10";
			} else {
				cntPT = countPage;
			}
			
			//fromPT�� �����̰ų�, 9�̻��̶�� -> ����ó��
			for (int i = 0; i < fromPT.length(); i++) {
				char ch = fromPT.charAt(i);
				if (ch < '0' || ch > '9') {
					throw new Exception();
				}
			}
			//cntPT�� �����̰ų�, 9�̻��̶�� -> ����ó��
			for (int i = 0; i < cntPT.length(); i++) {
				char ch = cntPT.charAt(i);
				if (ch < '0' || ch > '9') {
					throw new Exception();
				}
			}

			//totalCnt : ����� �� �Խñ��� ��
			//���� �Խñ��� ���� �� �Խñ��� ������ ũ�ٸ�, �� �Խñ��� ������ ����� �Խñ��� ���� ������ ��ŭ���� ����.
			if (Integer.parseInt(fromPT) > searchCnt) {
				fromPT = Integer.toString(searchCnt - (searchCnt % Integer.parseInt(cntPT)));
			}
			// pageNum : ���� ������ �� (default=1)
			int pageNum = ((searchCnt - 1) / Integer.parseInt(cntPT)) + 1;
			// printPage : �ϴܿ� ����¡���� ����� ������ ����
			int printPage = 10;
			// startPage : �� ȭ�鿡 ��µ� ������ �� '����' ������ ����
			int startPage = ((pageNum - 1) / 10) * 10 + 1;
			// endPage : �� ȭ�鿡 ��µ� ������ �� '������' ������ ��
			int endPage = startPage + printPage - 1;
			// �� ������ ��
			int totalPage = searchCnt / Integer.parseInt(cntPT);
			// ���� �Խñ� ���� �� ȭ�鿡 ����ؾ� �� �Խñ� ������ �۴��� ������ ���� 1��ŭ �þ�� ��
			if (searchCnt > Integer.parseInt(cntPT) * totalPage) {
				totalPage++;
			}
			// ���� ������ ���� �� ������ ������ ū ��� ���� �������� �� ������ ��ȣ�� ġȯ
			if (pageNum > totalPage) {
				pageNum = totalPage;
			}
			//������ ������ ���� �� ������ ������ ũ�ٸ� �� ���������� ġȯ
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			//previous : ���� �������� ����
			int previous = Integer.parseInt(startNum) - Integer.parseInt(countPage);
			//next : ���� �������� ����
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
			
			//startNum : ������ �Խñ��� ��ȣ (�Ķ���ͷ� ���� ��) 
			//fromPT : ��� ������ �Խñ��� ��ȣ			 
			String fromPT = "1";
			if (startNum == null || startNum == "") {
				fromPT = "1";
			} else if (Integer.parseInt(startNum) < 0) {
				fromPT = "1";
			} else {
				fromPT = startNum;
			}
			
			//countPage : ����� �Խñ� �� (�Ķ���ͷ� ���� ��)
			//cntPT : �� �������� ��µ� �Խñ� �� (10�� ����)
			String cntPT = "10";
			if (countPage == null || countPage == "") {
				cntPT = "10";
			} else if (countPage.equals("0")) {
				cntPT = "10";
			} else {
				cntPT = countPage;
			}
			
			//fromPT�� �����̰ų�, 9�̻��̶�� -> ����ó��
			for (int i = 0; i < fromPT.length(); i++) {
				char ch = fromPT.charAt(i);
				if (ch < '0' || ch > '9') {
					throw new Exception();
				}
			}
			//cntPT�� �����̰ų�, 9�̻��̶�� -> ����ó��
			for (int i = 0; i < cntPT.length(); i++) {
				char ch = cntPT.charAt(i);
				if (ch < '0' || ch > '9') {
					throw new Exception();
				}
			}

			//totalCnt : ����� �� �Խñ��� ��
			//���� �Խñ��� ���� �� �Խñ��� ������ ũ�ٸ�, �� �Խñ��� ������ ����� �Խñ��� ���� ������ ��ŭ���� ����.
			if (Integer.parseInt(fromPT) > totalCnt) {
				fromPT = Integer.toString(totalCnt - (totalCnt % Integer.parseInt(cntPT)));
			}

			// pageNum : ���� ������ �� (default=1)
			int pageNum = ((totalCnt - 1) / Integer.parseInt(cntPT)) + 1;
			// printPage : �ϴܿ� ����¡���� ����� ������ ����
			int printPage = 10;
			// startPage : �� ȭ�鿡 ��µ� ������ �� '����' ������ ����
			int startPage = ((pageNum - 1) / 10) * 10 + 1;
			// endPage : �� ȭ�鿡 ��µ� ������ �� '������' ������ ��
			int endPage = startPage + printPage - 1;
			// �� ������ ��
			int totalPage = totalCnt / Integer.parseInt(cntPT);
			// ���� �Խñ� ���� �� ȭ�鿡 ����ؾ� �� �Խñ� ������ �۴��� ������ ���� 1��ŭ �þ�� ��
			if (totalCnt > Integer.parseInt(cntPT) * totalPage) {
				totalPage++;
			}
			// ���� ������ ���� �� ������ ������ ū ��� ���� �������� �� ������ ��ȣ�� ġȯ
			if (pageNum > totalPage) {
				pageNum = totalPage;
			}
			//������ ������ ���� �� ������ ������ ũ�ٸ� �� ���������� ġȯ
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			//previous : ���� �������� ����
			int previous = Integer.parseInt(startNum) - Integer.parseInt(countPage);
			//next : ���� �������� ����
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
