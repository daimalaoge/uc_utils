package com.ucomponent.base.encrypt;

import com.ucomponent.base.controller.vo.BaseLayuiVO;

import java.lang.reflect.Method;
import java.util.List;

public class EncryptPO {
	/**
	 * 加密
	 * @param bo
	 * @return BaseLayuiVO
	 */
	public static BaseLayuiVO encPO(String key,BaseLayuiVO bo){
		try {
			Method method = bo.getClass().getMethod("getId", null);
			int l =(Integer)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
			pmethod.invoke(bo, new EncryptStringGen(key).encrypt(String.valueOf(l)));

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 解密
	 * @param bo
	 * @return BaseLayuiVO
	 */
	public static BaseLayuiVO decPO(String key,BaseLayuiVO bo){
		try {
			Method method = bo.getClass().getMethod("getEncCode", null);
			String l =(String)method.invoke(bo, null);
			if(!l.equals("")){
				Method pmethod = bo.getClass().getMethod("setId", int.class);
				pmethod.invoke(bo, Integer.parseInt(new EncryptStringGen(key).decrypt(l)));
			}

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 加密
	 * @param bo
	 * @param mod
	 * @return BaseLayuiVO
	 */
	public static BaseLayuiVO encPO(String key,BaseLayuiVO bo,String mod){
		try {
			Method method = bo.getClass().getMethod("get" +mod, null);
			int l =(Integer)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
			pmethod.invoke(bo, new EncryptStringGen(key).encrypt(String.valueOf(l)));

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 解密
	 * @param bo
	 * @param mod
	 * @return BaseLayuiVO
	 */
	public static BaseLayuiVO decPO(String key,BaseLayuiVO bo,String mod){
		try {
			Method method = bo.getClass().getMethod("getEncCode", null);
			String l =(String)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("set" + mod, int.class);
			pmethod.invoke(bo, Integer.parseInt(new EncryptStringGen(key).decrypt(l)));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 加密EncCode
	 * @param bo
	 * @param mod
	 * @param key
	 * @return BaseLayuiVO
	 */
	public static BaseLayuiVO encPOKey(BaseLayuiVO bo,String mod,String key){
		try {
			Method method = bo.getClass().getMethod("get" +mod, null);
			int l =(Integer)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
			pmethod.invoke(bo, new EncryptStringGen(key).encrypt(String.valueOf(l)));

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 解密
	 * @param bo
	 * @param mod
	 * @param key
	 * @return BaseLayuiVO
	 */
	public static BaseLayuiVO decPOKey(BaseLayuiVO bo,String mod,String key){
		try {
			Method method = bo.getClass().getMethod("getEncCode", null);
			String l =(String)method.invoke(bo, null);
			Method pmethod = bo.getClass().getMethod("set" + mod, int.class);
			pmethod.invoke(bo, Integer.parseInt(new EncryptStringGen(key).decrypt(l)));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bo;
	}
	/**
	 * 加密->EncCode
	 * @param list
	 * @return BaseLayuiVO
	 */
	public static List encList(String key,List<BaseLayuiVO> list){
		try {
			for(Object bo:list){
				Method method = bo.getClass().getMethod("getId", null);
				int l =(Integer)method.invoke(bo, null);
				Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
				pmethod.invoke(bo, new EncryptStringGen(key).encrypt(String.valueOf(l)));

			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 加密->EncCode
	 * @param list
	 * @param mod
	 * @return List
	 */
	public static List encList(String key,List<BaseLayuiVO> list,String mod){
		try {
			for(BaseLayuiVO bo:list){
				Method method = bo.getClass().getMethod("get"+mod, null);
				String l =(String)method.invoke(bo, null).toString();
				Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
				pmethod.invoke(bo, new EncryptStringGen(key).encrypt(l));
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 加密->EncCode
	 * @param list
	 * @param key
	 * @return List
	 */
	public static List encListKey(List<BaseLayuiVO> list,String key){
		try {
			for(BaseLayuiVO bo:list){
				Method method = bo.getClass().getMethod("getId", null);
				int l =(Integer)method.invoke(bo, null);
				Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
				pmethod.invoke(bo, new EncryptStringGen(key).encrypt(String.valueOf(l)));
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 加密
	 * @param list
	 * @param mod
	 * @param key
	 * @return List
	 */
	public static List encListKey(List<BaseLayuiVO> list,String mod,String key){
		try {
			for(BaseLayuiVO bo:list){
				Method method = bo.getClass().getMethod("get"+mod, null);
				String l =(String)method.invoke(bo, null).toString();
				Method pmethod = bo.getClass().getMethod("setEncCode", String.class);
				pmethod.invoke(bo, new EncryptStringGen(key).encrypt(l));
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
