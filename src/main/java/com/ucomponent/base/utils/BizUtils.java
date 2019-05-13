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
	 * @param type: in ICommons BIZ 定义
	 * @param name
	 * @param orgcode
	 * @return
	 */
	public static String getBizCode(String type,String name,String orgcode){
		PinyinTools pt = new PinyinTools();
		String code = pt.toHanyuPinyin(name).toUpperCase();;
		if(code.length() > MAX_CODE_LENTH) {
			code = code.substring( 0, MAX_CODE_LENTH );
		}
		if(type.equals(ACTION)){
			code = ACTION_PRFIX + orgcode +"_" + code;
		}else if(type.equals(FUNCTION)){
			code = FUNCTION_PRFIX + orgcode +"_" + code;
		}else if(type.equals(PAGE)){
			code = PAGE_PRFIX + orgcode +"_" + code;
		}else if(type.equals(PLATFORM)){
			code = PLATFORM_PRFIX + orgcode +"_" + code;
		}else if(type.equals(ORG)){
			code = ORG_PRFIX + code;
		}else if(type.equals(FIELD)){
			code = code.toLowerCase();
		}
		return code;
	}
}
