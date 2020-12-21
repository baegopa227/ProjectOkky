package com.okky.board;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.okky.boardcrud.BoardCRUD;
import com.okky.utils.Listparam;
import com.okky.vo.BoardVO;
import com.okky.vo.ThumbnailVO;

@Repository
public class BoardRepository {

	private Logger log = LoggerFactory.getLogger(BoardRepository.class);
	
	@Autowired
	private BoardCRUD boardCRUD;
	
	public int boardTableInsert(BoardVO vo) {
		int result = 0;
		
		try {
			result = boardCRUD.boardTableInsert("board.mappers.boardMapper.boardTableInsert", vo);
			return result;
		} catch (Exception e) {
			log.info("boardTableInsert error");
			return result;
		}
	}
	
	public int boardTableGetCount(Listparam listparam) {
		try {

			int count = boardCRUD.boardTableGetCount("board.mappers.boardMapper.boardTableGetCount", listparam);
			return count;
		} catch (Exception e) {
			log.info("boardTableGetCount error");
			return -1;
		}

	}


	public List<BoardVO> boardTableSelect(int index, String title) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("index", index);
		map.put("title", title);
		try {
			List<BoardVO> list = boardCRUD.boardTableSelect("board.mappers.boardMapper.boardTableSelect", map);
			return list;
		} catch (Exception e) {
			log.info("boardTableSelect error");
			return null;
		}
	}

	
	public int boardTableUpdate(BoardVO vo) {
		int result = 0;
		try {
			result = boardCRUD.boardTableUpdate("board.mappers.boardMapper.boardTableUpdate", vo);
			return result;
		} catch (Exception e) {
			System.out.println("boardTableUpdate error");
			return result;
		}

	}

	
	public int boardTableDelete(int id) {
		
		int result = 0;
		
		try {
			result = boardCRUD.boardTableDelete("board.mappers.boardMapper.boardTableDelete", id);
			return result;
		} catch (Exception e) {
			log.info("boardTableDelete DB error");
			return result;
		}
		
	}

	public int thumbnailTableInsert(ThumbnailVO vo) {
		
		int result = 0;
		
		try {
			result = boardCRUD.thumbnailTableInsert("board.mappers.boardMapper.thumbnailTableInsert",vo);
			return result;
		} catch (Exception e) {
			log.info("thumbnailTableInsert DB error");
			return result;
		}
		
		
	}

	public ThumbnailVO thumbnailTableSelect(int bcid) {
		
		ThumbnailVO thumbnailVO = new ThumbnailVO();
		
		try {
			thumbnailVO = boardCRUD.thumbnailTableSelect("board.mappers.boardMapper.thumbnailTableSelect", bcid);
			return thumbnailVO;
		} catch (Exception e) {
			log.info("thumbnailTableSelect DB error : " + e);
			return null;
		}
		
		
	}

	public int thumbnailTableUpdate(ThumbnailVO thumbnailVO) {

		int result = 0;
		
		try {
			result = boardCRUD.thumbnailTableUpdate("board.mappers.boardMapper.thumbnailTableUpdate",thumbnailVO);
			return result;

		} catch (Exception e) {
			log.info("thumbnailTableUpdate DB error");
			return result;
		}
		
	}

	public int thumbnailTableDelete(int id) {
		
		int result = 0;
		
		try {
			result = boardCRUD.thumbnailTableDelete("board.mappers.boardMapper.thumbnailTableDelete", id);
		} catch (Exception e) {
			log.info("thumbnailTableDelete DB error");
		}
		
		return result;
	}



}
