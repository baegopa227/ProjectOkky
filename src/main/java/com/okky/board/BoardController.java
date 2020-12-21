package com.okky.board;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.okky.okkyEnum.EMType;
import com.okky.okkyEnum.EType;
import com.okky.utils.ConfProperties;
import com.okky.utils.Listparam;
import com.okky.utils.MoveTrash;
import com.okky.utils.Paging;
import com.okky.vo.BoardVO;
import com.okky.vo.ErrorVO;
import com.okky.vo.ThumbnailVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	private Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	ConfProperties prop;

	@RequestMapping(value = { "/v1/insert" })
	public @ResponseBody JsonObject insert(@RequestParam(value = "uid", required = true) int uid,
			@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam(value = "thumbnail", required = false) MultipartFile file) {
		BoardVO vo = new BoardVO();

		JsonObject json = new JsonObject();
		ErrorVO errorVO = new ErrorVO();

		Date cdate = new Date();

		vo.setBid(1);
		// 게시판 1
		vo.setTitle(title);
		vo.setContent(content);
		vo.setCdate(cdate);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String cdateStr = format.format(cdate);

		int result = boardService.boardTableInsert(vo);

		if (result != 0) {

			if (file != null) {
				if (file.getSize() > 0) {

					try {

						String fileDirStr = "/data/thumbnail/" + cdateStr + "/";
						String filename = uid + "_" + System.currentTimeMillis() + "";
						File dir = new File(fileDirStr);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						BufferedInputStream bis = new BufferedInputStream(file.getInputStream());

						File file1 = new File(fileDirStr, filename);

						FileOutputStream fos = new FileOutputStream(file1);
						byte[] buffer = new byte[1024];
						int readBytes = 0;
						while ((readBytes = bis.read(buffer)) != -1) {
							fos.write(buffer, 0, readBytes);
						}

						fos.flush();
						fos.close();

						if (file1.exists()) {
							// String cmd = "chmod 644 " + filepath + file1.getName();
							// Runtime rt = Runtime.getRuntime();
							// Process p = rt.exec(cmd);
							file1.setReadable(true, false);

						}

						String thumbnail_url = prop.getInstance().getProperty("aws_webserver_url") + "/thumbnail/"
								+ cdateStr + "/" + filename;

						ThumbnailVO thumbnailVO = new ThumbnailVO();

						thumbnailVO.setBcid(vo.getId());
						thumbnailVO.setThumbnail_url(thumbnail_url);

						int thumbnailResult = boardService.thumbnailTableInsert(thumbnailVO);

						if (thumbnailResult == 1) {
							errorVO.setErrorInfo(EType.errorNone, EMType.errorNone);
							json.put("errorInfo", errorVO);
							return json;

						} else {
							log.error(EType.DBError + " : " + EMType.thumbnailInsertError);
							errorVO.setErrorInfo(EType.DBError, EMType.thumbnailInsertError);
							json.put("errorInfo", errorVO);
							return json;

						}

					} catch (Exception e) {
						log.error(EType.fileError + " : " + EMType.fileIOError + " , " + e);
						errorVO.setErrorInfo(EType.fileError, EMType.fileIOError);
						json.put("errorInfo", errorVO);
						return json;
					}

				}

			} else {

				ThumbnailVO thumbnailVO = new ThumbnailVO();

				thumbnailVO.setBcid(vo.getId());
				thumbnailVO.setThumbnail_url("null");

				int thumbnailResult = boardService.thumbnailTableInsert(thumbnailVO);

				if (thumbnailResult == 1) {
					errorVO.setErrorInfo(EType.errorNone, EMType.errorNone);
					json.put("errorInfo", errorVO);
					return json;

				} else {
					log.error(EType.DBError + " : " + EMType.thumbnailInsertError);
					errorVO.setErrorInfo(EType.DBError, EMType.thumbnailInsertError);
					json.put("errorInfo", errorVO);
					return json;

				}

			}

			errorVO.setErrorInfo(EType.errorNone, EMType.errorNone);
			json.put("errorInfo", errorVO);
			return json;
		} else {
			log.error(EType.DBError + " : " + EMType.boardTableInsertError);
			errorVO.setErrorInfo(EType.DBError, EMType.boardTableInsertError);
			json.put("errorInfo", errorVO);
			return json;
		}

	}

	@RequestMapping(value = { "/v1/select" })
	public @ResponseBody JsonObject select(Paging paging, Listparam listparam) {

		JsonObject json = new JsonObject();
		ErrorVO errorVO = new ErrorVO();

		int totalCount = boardService.boardTableGetCount(listparam);
		if (totalCount != -1) {
			paging.setTotalCount(totalCount);

			List<BoardVO> list = new ArrayList<BoardVO>();
			list = boardService.boardTableSelect(paging.getFirstIndex(), listparam.getTitle());

			errorVO.setErrorInfo(EType.errorNone, EMType.errorNone);
			json.put("errorInfo", errorVO);
			json.put("boardList", list);
			json.put("paging", paging);
			json.put("listparam", listparam);
			return json;

		} else {
			log.error(EType.DBError + " : " + EMType.dberror);
			errorVO.setErrorInfo(EType.DBError, EMType.dberror);
			json.put("errorInfo", errorVO);

			return json;

		}

	}

	@RequestMapping(value = { "/v1/update" })
	public @ResponseBody JsonObject update(@RequestParam(value = "id", required = true, defaultValue = "") int id,
			@RequestParam(value = "uid", required = true, defaultValue = "") int uid,
			@RequestParam(value = "title", required = true, defaultValue = "") String title,
			@RequestParam(value = "content", required = true, defaultValue = "") String content,
			@RequestParam(value = "thumbnail", required = false) MultipartFile file) {

		Date cdate = new Date();
		String filename = "";

		BoardVO vo = new BoardVO();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String cdateStr = format.format(cdate);

		String fileDirStr = "/data/thumbnail/" + cdateStr + "/";
		File dir = new File(fileDirStr);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		vo.setId(id);
		vo.setUid(uid);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setCdate(cdate);

		int result = boardService.boardTableUpdate(vo);

		JsonObject json = new JsonObject();
		ErrorVO errorVO = new ErrorVO();

		if (result == 1) {
			if (file != null) {
				if (file.getSize() > 0) {
					ThumbnailVO thumbnailVO = boardService.thumbnailTableSelect(vo.getId());

					String[] thumbnailInfoArray = thumbnailVO.getThumbnail_url().split("/");

					try {
						filename = vo.getUid() + "_" + System.currentTimeMillis();
						BufferedInputStream bis = new BufferedInputStream(file.getInputStream());

						File file1 = new File(dir, filename);

						FileOutputStream fos = new FileOutputStream(file1);
						byte[] buffer = new byte[1024];
						int readBytes = -1;
						while ((readBytes = bis.read()) != -1) {
							fos.write(buffer, 0, readBytes);
						}

						fos.flush();
						fos.close();

						if (thumbnailVO != null) {
							if (!thumbnailVO.getThumbnail_url().equals("null")) {
								boolean isTrashMoveSuccess = MoveTrash.moveTrash(
										thumbnailInfoArray[thumbnailInfoArray.length - 2],
										thumbnailInfoArray[thumbnailInfoArray.length - 1]);

								if (!isTrashMoveSuccess) {
									log.error(EType.fileError + " : " + EMType.trashMoveError);
									errorVO.setErrorInfo(EType.fileError, EMType.trashMoveError);
									json.put("errorInfo", errorVO);

									return json;
								}
							}

						}

					} catch (IOException e) {
						log.error(EType.fileError + " : " + EMType.fileIOError + " , " + e);
						errorVO.setErrorInfo(EType.fileError, EMType.fileIOError);
						json.put("errorInfo", errorVO);

						return json;
					}

					String thumbnail_url = prop.getInstance().getProperty("aws_webserver_url") + "/thumbnail/"
							+ cdateStr + "/" + filename;

					ThumbnailVO thumbnailVO1 = new ThumbnailVO();
					thumbnailVO1.setBcid(vo.getId());
					thumbnailVO1.setThumbnail_url(thumbnail_url);

					int updateResult = boardService.thumbnailTableUpdate(thumbnailVO1);

					if (updateResult == 1) {
						errorVO.setErrorInfo(EType.errorNone, EMType.errorNone);
						json.put("errorInfo", errorVO);

						return json;
					} else {
						log.error(EType.DBError + " : " + EMType.thumbnailUpdateError);
						errorVO.setErrorInfo(EType.DBError, EMType.thumbnailUpdateError);
						json.put("errorInfo", errorVO);

						return json;

					}

				} else {
					errorVO.setErrorInfo(EType.errorNone, EMType.errorNone);
					json.put("errorInfo", errorVO);
					return json;

				}
			} else {

				ThumbnailVO thumbnailVO = new ThumbnailVO();

				thumbnailVO.setBcid(vo.getId());
				thumbnailVO.setThumbnail_url("null");

				int thumbnailResult = boardService.thumbnailTableUpdate(thumbnailVO);

				if (thumbnailResult == 1) {
					errorVO.setErrorInfo(EType.errorNone, EMType.errorNone);
					json.put("errorInfo", errorVO);
					return json;

				} else {
					log.error(EType.DBError + " : " + EMType.thumbnailUpdateError);
					errorVO.setErrorInfo(EType.DBError, EMType.thumbnailUpdateError);
					json.put("errorInfo", errorVO);
					return json;

				}

			}
		} else {
			log.error(EType.DBError + " : " + EMType.boardTableUpdateError);
			errorVO.setErrorInfo(EType.DBError, EMType.boardTableUpdateError);
			json.put("errorInfo", errorVO);

			return json;

		}

	}

	@RequestMapping(value = { "/v1/delete" })
	public @ResponseBody JsonObject delete(@RequestParam(value = "id", required = true, defaultValue = "-1") int id,
			@RequestParam(value = "uid", required = true, defaultValue = "-1") int uid,
			@RequestParam(value = "bid", required = true, defaultValue = "-1") int bid

	) {

		boolean isTrashMoveSuccess;

		JsonObject json = new JsonObject();
		ErrorVO errorVO = new ErrorVO();

		if (uid == -1) {
			log.error(EType.clientError + " : " + EMType.uidError);
			errorVO.setErrorInfo(EType.clientError, EMType.uidError);
			json.put("errorInfo", errorVO);

			return json;

		}

		if (id >= 0) {

			int result = boardService.boardTableDelete(id);

			if (result == 1) {
				ThumbnailVO thumbnailVO = new ThumbnailVO();
				thumbnailVO = boardService.thumbnailTableSelect(id);
				if (thumbnailVO != null) {
					if (!thumbnailVO.getThumbnail_url().equals("null")) {
						String thumbnail_url = thumbnailVO.getThumbnail_url();
						String[] thumbnailInfoArray = thumbnail_url.split("/");
						System.out.println(thumbnailInfoArray[thumbnailInfoArray.length - 2]);
						isTrashMoveSuccess = MoveTrash.moveTrash(thumbnailInfoArray[thumbnailInfoArray.length - 2],
								thumbnailInfoArray[thumbnailInfoArray.length - 1]);
					} else {
						isTrashMoveSuccess = true;
					}

				} else {
					isTrashMoveSuccess = true;
				}
				if (!isTrashMoveSuccess) {
					log.error(EType.fileError + " : " + EMType.trashMoveError);
					errorVO.setErrorInfo(EType.fileError, EMType.trashMoveError);
					json.put("errorInfo", errorVO);

					return json;
				} else {

					int result1 = boardService.thumbnailTableDelete(id);

					if (result1 == 1) {

						errorVO.setErrorInfo(EType.errorNone, EMType.errorNone);
						json.put("errorInfo", errorVO);
						return json;
					} else {
						errorVO.setErrorInfo(EType.errorNone, EMType.errorNone);
						json.put("errorInfo", errorVO);
						return json;

					}

				}

			} else {

				errorVO.setErrorInfo(EType.errorNone, EMType.alreadyDeleted);
				json.put("errorInfo", errorVO);

				return json;
			}
		} else {
			log.error(EType.clientError + " : " + EMType.uidError);
			errorVO.setErrorInfo(EType.clientError, EMType.uidError);
			json.put("errorInfo", errorVO);

			return json;
		}

	}
}
