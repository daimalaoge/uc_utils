package com.ucomponent.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 2019/5/17
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
public class FileTools {
	/**
	 *
	 * @param filePath
	 * @return
	 */
	public static List<String> readTxtFileIntoStringArrList(String filePath){
		List<String> list = new ArrayList<String>();
		try{
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()){ // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(	new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;

				while ((lineTxt = bufferedReader.readLine()) != null){
					list.add(lineTxt);
				}
				bufferedReader.close();
				read.close();
			}
			else{
				System.out.println("找不到指定的文件");
				return null;
			}
		}
		catch (Exception e){
			System.out.println("读取文件内容出错");
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
