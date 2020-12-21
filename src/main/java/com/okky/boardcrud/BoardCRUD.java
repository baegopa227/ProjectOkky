package com.okky.boardcrud;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.okky.dbconnect.DBConnect;
import com.okky.utils.Listparam;
import com.okky.vo.BoardVO;
import com.okky.vo.ThumbnailVO;

@Repository
public class BoardCRUD {

	private Logger log = LoggerFactory.getLogger(BoardCRUD.class);

	@Autowired
	private DBConnect dbConnect;

	public int boardTableGetCount(String sql, Listparam listparam) throws Exception {

		int count = 0;
		count = dbConnect.connect().selectOne(sql, listparam);

		return count;
	}

	public List<BoardVO> boardTableSelect(String sql, HashMap<String, Object> map) {

			List<BoardVO> list = dbConnect.connect().selectList(sql, map);
			return list;
	}

	public int boardTableInsert(String sql, BoardVO vo) throws Exception {

		int result = dbConnect.connect().insert(sql, vo);

		return result;
	}

	public int boardTableUpdate(String sql, BoardVO vo) throws Exception {
		
		int result = dbConnect.connect().update(sql, vo);
		
		return result;
	}
	
	public int boardTableDelete(String sql, int id) throws Exception {
		
		int result = dbConnect.connect().delete(sql, id);
		
		return result;
	}

	public int thumbnailTableInsert(String sql, ThumbnailVO vo) throws Exception {

		int result = dbConnect.connect().insert(sql, vo);
		return result;
	}

	public ThumbnailVO thumbnailTableSelect(String sql, int bcid) throws Exception {
		ThumbnailVO thumbnailVO = new ThumbnailVO();
		thumbnailVO = dbConnect.connect().selectOne(sql, bcid);
		return thumbnailVO;
	}

	public int thumbnailTableUpdate(String sql, ThumbnailVO thumbnailVO) throws Exception {

		int result = dbConnect.connect().update(sql, thumbnailVO);
		return result;
	}

	public int thumbnailTableDelete(String sql, int id) throws Exception{

		int result = dbConnect.connect().delete(sql, id);
		
		return result;
	}
	
}