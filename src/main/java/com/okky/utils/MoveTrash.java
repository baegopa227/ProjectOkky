package com.okky.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveTrash {

	private static Logger log = LoggerFactory.getLogger(MoveTrash.class);
	
	public static boolean moveTrash(String cdateStr, String filename) {
		// TODO Auto-generated method stub

		String trashSrcDirStr = "/data/thumbnail/" + cdateStr;
		String trashDesDirStr = "/data/trash/" + cdateStr;
		String trashSrcFileStr = filename;
		String trashDesFileStr = filename;

		// 쓰레기 대상
		File trashSrcDir = new File(trashSrcDirStr);
		File trashSrcFile = new File(trashSrcDirStr, trashSrcFileStr);

		// 쓰레기 목적지
		File trashDesDir = new File(trashDesDirStr);
		File trashDesFile = new File(trashDesDirStr, trashDesFileStr);

		try {
			
			if(!trashSrcDir.exists()) {
				trashSrcDir.mkdirs();
			}
			if(!trashDesDir.exists()) {
				
				trashDesDir.mkdirs();
			}
			

			if (trashSrcFile.exists()) {
				System.out.println("원본은 있음");
				trashDesFile.createNewFile();
				if(trashDesFile.exists()) {
					System.out.println("dest 파일도 생성되어있음");
				}
				return trashSrcFile.renameTo(trashDesFile);
			}else {
				System.out.println(trashSrcFileStr);
				return true;
			}


		} catch (Exception e) {
			log.error("trash move error : " + e );
			return false;
		}

	}

}
