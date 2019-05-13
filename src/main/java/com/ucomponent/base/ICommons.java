package com.ucomponent.base;

/**
 * 2018年6月20日
 * 代码老哥
 * NAME:
 * Descp:
**/
public abstract interface ICommons {
  //返回码
  public static final String ERROR_URL = "-9";
  public static final String ERROR_NOCONNECTION = "-8";
  public static final String ERROR_NOREASON = "-99";

  //系统是否使用验证码登录 Y是 N否
  public final String SYS_CHECKCODE = "spring.profiles.active";
	
	//ACCOUNT对象在session中命名
	public static final String SESSION_ACCOUNT = "SESSION_ACCOUNT";
	//ACCOUNT对象在session中命名
  public static final String SESSION_MENULIST = "SESSION_MENULIST";
	//ACCOUNT对象可通过的路径
	public static final String SESSION_BIZPATH = "SESSION_BIZPATH";
	//ORG
	public static final String SESSION_ORG = "SESSION_ORG";
	//验证码
	public static final String SESSION_IDECODE_IMG = "SESSION_IDECODE_IMG";
	//用户
	public static final String STATUS_USE = "G_STATUS_USE";
	//用户临时加解密KEY，首次登陆后生成，Session失效后失效
	public static final String USER_ENCRYPT_KEY = "SESSION_USER_ENCRYPT_KEY";

	//BIZ 定义
	//action 定义
	public static final int MAX_CODE_LENTH = 45;  //最大CODE长度
	//action 定义
	public static final String ACTION= "ACT";
	//function 定义
	public static final String FUNCTION= "FUN";
	//page 定义
	public static final String PAGE = "PAG";
	//platform 定义
	public static final String PLATFORM = "PLT";
	//orgnazition 定义
	public static final String ORG = "ORG";
	//field 定义
	public static final String FIELD = "FLD";
	//action 前缀
	public static final String ACTION_PRFIX = "ACT_";
	//function 前缀
	public static final String FUNCTION_PRFIX = "FUN_";
	//page 前缀
	public static final String PAGE_PRFIX = "PAG_";
	//platform 前缀
	public static final String PLATFORM_PRFIX = "PLT_";
	//orgnazition 前缀
	public static final String ORG_PRFIX = "ORG_";
	//共通action 前缀
	public static final String COM_ACTION_PRFIX = "COM_ACT_";
	//共通function 前缀
	public static final String COM_FUNCTION_PRFIX = "COM_FUN_";
	//共通page 前缀
	public static final String COM_PAGE_PRFIX = "COM_PAG_";
	//共通platform 前缀
	public static final String COM_PLATFORM_PRFIX = "COM_PLT_";
	//orgnazition 前缀
	public static final String COM_ORG_PRFIX = "COM_ORG_";

	//UC后台列表页显示条数
	public static final int UCMANAGER_DISP_SIZE = 20;
	//UC后台返回成功：1
	public static final String UCMANAGER_DATA_SUCCUSS = "1";
	//UC后台返回失败：2
	public static final String UCMANAGER_DATA_ERROR = "-99";
	//UC后台列表页正常显示
	public static final String UCMANAGER_LISTPAGE_CODE = "0";
	//UC后台数据增加
	public static final String UCMANAGER_ACTION_ADD = "ADD";
	//UC后台数据修改
	public static final String UCMANAGER_ACTION_EDIT= "EDIT";
	//加密ID页面参数名称
	public static final String UCMANAGER_PAGEENCPAR_NAME =	"encCode"	;
	//UC后台数据添加标题
	public static final String UCMANAGER_ACTION_ADDTITLE= "新增";
	//UC后台数据修改标题
	public static final String UCMANAGER_ACTION_EDITTITLE= "修改";
	/* EncryptFileGen Key vaule*/
	public static final String ENCRY_FILE_KEY =	"123456"	;
	/* EncryptStringGen Key vaule*/
	public static final String ENCRY_STRING_KEY =	""	;
}
