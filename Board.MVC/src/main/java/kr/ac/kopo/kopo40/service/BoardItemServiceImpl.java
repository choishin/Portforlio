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
	public BoardItem selectOne(int id) {
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
		return boardItemDao.selectOne(id);
	}


	@Override
	public List<BoardItem> selectAll(int board_index) {
		// TODO Auto-generated method stub
		List<BoardItem> boardItemAll = new ArrayList<BoardItem>();
		try {
			boardItemDao = BoardItemDaoImpl.getInstance();
			boardItemAll = boardItemDao.selectAll(board_index);

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
	public void update(BoardItem boardItem) {
		// TODO Auto-generated method stub
		boardItemDao.update(boardItem);
	}
	@Override
	public void delete(BoardItem boardItem) {
		// TODO Auto-generated method stub
		boardItemDao.delete(boardItem);
	}
	public int totalCount() {
		return boardItemDao.totalCount();
	}
	
	public int getMax() {
		return boardItemDao.getMax();
	}
	
	public void addCount(int id) {
		boardItemDao.addCount(id);
	}

}
