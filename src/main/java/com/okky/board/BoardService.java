package com.okky.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okky.utils.Listparam;
import com.okky.utils.Paging;
import com.okky.vo.BoardVO;
import com.okky.vo.ThumbnailVO;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	
	public int boardTableInsert(BoardVO vo) {
		return boardRepository.boardTableInsert(vo);
	}


	public List<BoardVO> boardTableSelect(int index, String title) {
		return boardRepository.boardTableSelect(index, title);
	}

	public int boardTableGetCount(Listparam listparam) {
		return boardRepository.boardTableGetCount(listparam);
	}


	public int boardTableUpdate(BoardVO vo) {
		return boardRepository.boardTableUpdate(vo);
	}


	public int boardTableDelete(int id) {
		
		return boardRepository.boardTableDelete(id);
	}


	public int thumbnailTableInsert(ThumbnailVO vo) {

		return boardRepository.thumbnailTableInsert(vo);
	}


	public ThumbnailVO thumbnailTableSelect(int id) {

		return boardRepository.thumbnailTableSelect(id);
	}


	public int thumbnailTableUpdate(ThumbnailVO thumbnailVO1) {

		return boardRepository.thumbnailTableUpdate(thumbnailVO1);
	}


	public int thumbnailTableDelete(int id) {

		return boardRepository.thumbnailTableDelete(id);
	}


	


	}
