package kr.ac.kopo.kopo40.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kr.ac.kopo.kopo40.dao.BoardDaoImpl;
import kr.ac.kopo.kopo40.domain.Board;

public class BoardServiceImpl implements BoardService {
	static BoardDaoImpl boardDao;
	private static BoardServiceImpl instance = null;
	private BoardServiceImpl() {}
	public static BoardServiceImpl getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new BoardServiceImpl();
		}
		return instance;
	}

	@Override
	public void create(Board board) {
		boardDao.create(board);
	}

	@Override
	// DAO의 DB를 받아와서 pagination 등의 기능들을 덧붙여서 UI로 전달
	public List<Board> selectAll() {
		// TODO Auto-generated method stub
		List<Board> boardAll = new ArrayList<Board>();
		try {
			boardDao = BoardDaoImpl.getInstance();
			boardAll = boardDao.selectAll();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardAll;
	}
	
	@Override
	public Board selectOne(int id) {
		try {
			boardDao = BoardDaoImpl.getInstance();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardDao.selectOne(id);
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		boardDao.update(board);

	}

	@Override
	public void delete(Board board) {
		// TODO Auto-generated method stub
		boardDao.delete(board);
	}

	public int totalCount() {
		int totalCnt = 0;
		try {
			boardDao = BoardDaoImpl.getInstance();
			totalCnt = boardDao.totalCount();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCnt;
	}

	public int getMax() {
		int maxId = 0;
		try {
			boardDao = BoardDaoImpl.getInstance();
			maxId = boardDao.getMax();
		} catch (Exception e) {
			System.out.println(e);
		}
		return maxId;
	}
}
