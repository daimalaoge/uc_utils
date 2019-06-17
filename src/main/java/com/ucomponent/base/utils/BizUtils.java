package com.ucomponent.base.utils;

import com.ucomponent.base.ICommons;
import com.ucomponent.utils.PinyinTools;

/**
 * 2019/5/10
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
public class BizUtils implements ICommons {
	/**
	 * 获取业务CODE
	 * @param type in ICommons BIZ 定义
	 * @param name
	 * @param orgcode
	 * @return String
	 */
	public static String getBizCode(String type,String name,String orgcode){
		PinyinTools pt = new PinyinTools();
		String code = pt.toHanyuPinyin(name).toUpperCase();;
		if(code.length() > MAX_CODE_LENTH) {
			code = code.substring( 0, MAX_CODE_LENTH );
		}
		if(type.equals(ACTION)){
			code = ACTION+"_" + orgcode +"_" + code;
		}else if(type.equals(FUNCTION)){
			code = FUNCTION+"_" + orgcode +"_" + code;
		}else if(type.equals(PAGE)){
			code = PAGE+"_" + orgcode +"_" + code;
		}else if(type.equals(PLATFORM)){
			code = PLATFORM+"_" + orgcode +"_" + code;
		}else if(type.equals(ORG)){
			code = ORG+"_" + code;
		}else if(type.equals(FIELD)){
			code = code.toLowerCase();
		}else if(type.equals(INVENTORY)){
			code = INVENTORY+"_" + orgcode +"_"+ code;
		}
		return code;
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	public static String getSrcCode(String name){
		PinyinTools pt = new PinyinTools();
		String code = pt.toHanyuPinyin(name).toLowerCase();
		code = code.replaceAll("_","");
		code = code.replaceAll("-","");
		code = code.replaceAll("~","");
		code = code.replaceAll("|","");
		code = code.replaceAll("&","");
		code = code.replaceAll(" ","");
		code = code.replaceAll("%","");
		code = code.replaceAll("#","");
		code = code.replaceAll("\\.","");
		code = code.replaceAll(",","");
		code = code.replaceAll("'","");
		code = code.replaceAll("=","");
		if(code.length() > MAX_CODE_LENTH) {
			code = code.substring( 0, MAX_CODE_LENTH );
		}
		return code;
	}
//
//	public static void main(String[] args) {
//		System.out.println("---"+BizUtils.getSrcCode("仪表盘3"));
//	}

	/**
	 *
	 * @param str
	 * @return
	 */
	public static String getSrcName(String str) {
		if(str!=null && str!=""){
			str  = str.substring(0,1).toUpperCase()+str.substring(1);
		}
		return str;
	}
}
