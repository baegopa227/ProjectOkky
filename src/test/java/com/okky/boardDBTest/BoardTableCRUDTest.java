package com.okky.boardDBTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.okky.dbconnect.DBConnect;
import com.okky.utils.Paging;
import com.okky.vo.BoardVO;
import com.okky.vo.ThumbnailVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/kiuk-root-context.xml")
@Transactional
public class BoardTableCRUDTest {

	Logger log = LoggerFactory.getLogger(BoardTableCRUDTest.class);

	/*
	 * @Autowired DBConnectionTest dbConnectionTest;
	 */

	@Autowired
	DBConnect dbConnect;

	// @Test
	/*
	 * public void selectTest() {
	 * 
	 * HashMap<String, Object> map = new HashMap<String, Object>(); map.put("index",
	 * 10 ); map.put("title", "title"); try { List<BoardVO> list =
	 * dbConnectionTest.connetMyBatisList("test.mappers.testMapper.selectAll", map);
	 * for(BoardVO item : list) { System.out.println(item.getId()); } } catch
	 * (Exception e) { System.out.println("select error"); e.printStackTrace(); } }
	 */

	String logStr = "--------------------";

	@Test
	public void connectTest() {
		getLogInfo(" DB session connect result : " + dbConnect.connectTest());
	}

	@Test
	public void boardTableInsert() {
		String sql = "board.mappers.boardMapper.boardTableInsert";
		
		BoardVO vo = new BoardVO();
		Date cdate = new Date();
		
		vo.setUid(1);
		vo.setTitle("test");
		vo.setContent("content");
		vo.setCdate(cdate);
		
		int result = dbConnect.connect().insert(sql, vo);
		
		getLogInfo("board table insert result : " + (result == 1?true :false));
	}

	@Test
	public void boardTableSelect() {
		String sql = "board.mappers.boardMapper.boardTableSelect";
		
		Paging paging = new Paging(1,0);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", "test");
		map.put("index", paging.getFirstIndex());
		List<BoardVO> list = dbConnect.connect().selectList(sql, map);
		

		for(BoardVO vo : list) {
			getLogInfo("title : " + vo.getTitle() + ", content : " + vo.getContent() + " , thumbnail_url : " + vo.getThumbnail_url());
		}
	}
	
	@Test
	public void boardTableGetCount() {
		String sql = "board.mappers.boardMapper.boardTableGetCount";
		
		BoardVO vo = new BoardVO();
		vo.setTitle("test");
		
		int count = dbConnect.connect().selectOne(sql, vo);
		
		getLogInfo("board table select count : " + count);
	}
	
	private void getLogInfo(String string) {
		log.info("\n" + String.format(logStr + " %s " + logStr, string));

	}

	@Test
	public void boardTableUpdate() {
		String sql = "board.mappers.boardMapper.boardTableUpdate";
		
		Date cdate = new Date();
		
		BoardVO vo = new BoardVO();
		vo.setId(0);
		vo.setTitle("test");
		vo.setContent("test(수정됨)");
		vo.setCdate(cdate);

		int result = dbConnect.connect().update(sql, vo);
		
		getLogInfo("board table update result : " + (result == 1?true:false));
	}

	
	@Test
	public void boardTableDelete() {
		String sql = "board.mappers.boardMapper.boardTableDelete";
		
		BoardVO vo = new BoardVO();
		vo.setId(0);
		
		int result = dbConnect.connect().delete(sql, vo);
		
		getLogInfo("board table delete result : " + (result == 1 ? true : false));
	}
	
	@Test
	public void thumbnailTableSelect() {
		String sql = "board.mappers.boardMapper.thumbnailTableSelect";
		
		ThumbnailVO thumbnailVO = new ThumbnailVO();
		
		thumbnailVO = dbConnect.connect().selectOne(sql, 0);
		
		getLogInfo("thumbnail table select url result : " + thumbnailVO.getThumbnail_url());
	}
	
	
	@Test
	public void thumbnailTableInsert() {
		String sql = "board.mappers.boardMapper.thumbnailTableInsert";
		
		ThumbnailVO vo = new ThumbnailVO();
		vo.setBcid(0);
		vo.setThumbnail_url("test");
		
		int result = dbConnect.connect().insert(sql, vo);
		
		getLogInfo("thumbnail table insert result : " + (result == 1? true: false));
	}
	
	@Test
	public void thumbnailTableUpdate() {
		String sql = "board.mappers.boardMapper.thumbnailTableUpdate";
		
		ThumbnailVO vo = new ThumbnailVO();
		vo.setBcid(0);
		vo.setThumbnail_url("test_url(수정됨)");
		
		int result = dbConnect.connect().update(sql, vo);
		
		getLogInfo("thumbnail table update result : " + (result == 1?true:false));
	}
	
}
