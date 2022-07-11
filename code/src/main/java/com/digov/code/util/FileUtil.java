package com.digov.code.util;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 * 2016年12月16日 15:57:02
 * @author yuyongxing
 *
 */
public class FileUtil {

	/**
	 * 读取某个文件目录下的所有文件内容到
	 * 某个文件中去
	 */
	public static void readDirToFile(List<String> dirList, String targetFilePath){
		for (String dir : dirList) {
			List<String> filePathList = getFilePathList(dir);
			for (String filePath : filePathList) {
				String textByFile = TextUtil.getOriginTextByFile(filePath, null);
				TextUtil.writeStrToFile(targetFilePath, textByFile, true);
			}
		}
	}


	/**
	 * 获取某个目录下的所有子文件的绝对路径
	 * @param dirName
	 * @return
	 */
	public static List<String> getFilePathList(String dirName){
		List<String> resultList = new ArrayList<>();
		File data = new File(dirName);
		File[] files = data.listFiles();
		for (File file : files) {
			boolean directory = file.isDirectory();
			String path = file.getAbsolutePath();
			if(directory){
				List<String> filePath = getFilePathList(path);
				if (!CommonUtil.isEmpty(filePath)) {
					resultList.addAll(filePath);
				}
			} else {
				resultList.add(path);
			}
		}
		return resultList;
	}

	public static void main(String[] args) {
		List<String> dirList = new ArrayList<>();
		dirList.add("industry_entity/src/main/java/com/digov/industry_entity/entity/ads/area");
		dirList.add("industry_repository/src/main/java/com/digov/industry_repository/repository/ads/area");
		dirList.add("industry_service/src/main/java/com/digov/industry_service/service/ads/area");
//		dirList.add("industry_web/src/main/java/com/digov/industry_web/controller/ads/area");
		readDirToFile(dirList, "content.txt");

	}
}
