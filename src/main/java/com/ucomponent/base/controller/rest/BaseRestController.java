package com.ucomponent.base.controller.rest;

import com.ucomponent.base.ICommons;
import com.ucomponent.base.controller.BaseLayuiVO;
import com.ucomponent.base.controller.BasePO;
import com.ucomponent.base.controller.ControllerTools;

import com.ucomponent.po.MangSysOrg;
import com.ucomponent.po.MangUserAccount;
import com.ucomponent.utils.StringTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 2018年10月27日
 * BaseController.java
 * 代码老哥
 * NAME:
 * Descp:
**/
public class BaseRestController implements ICommons {
	/**
	 * 得到页面数
	 * @param request
	 * @return
	 */
	public int getPage(HttpServletRequest request){
    String pagenum = StringTools.getString(request.getParameter("page"));
		int npage = 0;
    if(!pagenum.equals("")){
      npage = Integer.parseInt(pagenum) -1;
    }
    return npage;
	}
	/**
	 * 得到每页显示行数
	 * @param request
	 * @return
	 */
	public int getPageLimit(HttpServletRequest request){
  	String pagelimit = StringTools.getString(request.getParameter("limit"));
		int npagelimit = UCMANAGER_DISP_SIZE;
		if(!pagelimit.equals("")){
    	npagelimit = Integer.parseInt(pagelimit);
    }
    return npagelimit;
	}
	/**
	 * 对输入字符和Codeset进行转换
	 * @return
	 */
	public List<BaseLayuiVO> codeKeyConvert(HttpServletRequest request,List<BaseLayuiVO> jsonlist){
		HttpSession session = request.getSession();
		String key = (String)session.getAttribute(USER_ENCRYPT_KEY);
		if(key != null){
			return new ControllerTools().codeKeyConvert(key,jsonlist);
		}else{
			return null;
		}
	}
	public List<BaseLayuiVO> codeKeyConvert(String key,List<BaseLayuiVO> jsonlist){
		return new ControllerTools().codeKeyConvert(key,jsonlist);
	}
	/**
	 * 保存
	 * @param vo
	 * @param request
	 */
	public void doSave(BasePO vo, HttpServletRequest request){
	  String actionModel = StringTools.getString(request.getParameter("ACTIONMODE"));
	  HttpSession session = request.getSession();
		MangUserAccount user = (MangUserAccount)session.getAttribute(SESSION_ACCOUNT);
    if(actionModel.equals("ADD")){
      vo.setCreateDatetime(new Date());
      vo.setUpdateDatetime(new Date());
      vo.setUpdateUserId(user.getId());
      vo.setCreateUserId(user.getId());
      vo.setOrgId(user.getOrgId());
    }else if(actionModel.equals("EDIT")){
      vo.setUpdateDatetime(new Date());
      vo.setUpdateUserId(user.getId());
    }
	}
	/**
	 * 删除
	 * @param vo
	 * @param request
	 */
	public void doDelete(BasePO vo,HttpServletRequest request){
		HttpSession session = request.getSession();
		MangUserAccount user = (MangUserAccount)session.getAttribute(SESSION_ACCOUNT);
	  vo.setCodesetGstatus("G_STATUS_DEL");
	  vo.setUpdateDatetime(new Date());
	  vo.setUpdateUserId(user.getId());
  }
	/**
	 * 更改状态
	 * @param vo
	 */
	public void doStatus(BasePO vo,HttpServletRequest request){
		HttpSession session = request.getSession();
		MangUserAccount user = (MangUserAccount)session.getAttribute(SESSION_ACCOUNT);
	  vo.setUpdateDatetime(new Date());
	  vo.setUpdateUserId(user.getId());
	  if(vo.getCodesetGstatus().equals("G_STATUS_USE")){
	    vo.setCodesetGstatus("G_STATUS_NOUSE");
    }else if(vo.getCodesetGstatus().equals("G_STATUS_NOUSE")){
      vo.setCodesetGstatus("G_STATUS_USE");
    }
  }
  public int getUserId(HttpServletRequest request){
	  HttpSession session = request.getSession();
	  MangUserAccount user = (MangUserAccount)session.getAttribute(SESSION_ACCOUNT);
	  return user.getId();
  }

	public int getOrgId(HttpServletRequest request){
		HttpSession session = request.getSession();
		MangUserAccount user = (MangUserAccount)session.getAttribute(SESSION_ACCOUNT);
		return user.getOrgId();
	}

	public String getOrgCode(HttpServletRequest request){
		HttpSession session = request.getSession();
		MangSysOrg org = (MangSysOrg)session.getAttribute(SESSION_ORG);
		return org.getCode();
	}

	public String getEncrytKey(HttpServletRequest request){
		HttpSession session = request.getSession();
		String key = (String)session.getAttribute(USER_ENCRYPT_KEY);
		if(key != null){
			return key;
		}else{
			return "";
		}
	}
}
