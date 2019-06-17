package com.ucomponent.authent.handler;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2019/6/13
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mv;
		//进行异常判断。如果捕获异常请求跳转。
		if(ex instanceof UnauthorizedException){
			mv = new ModelAndView("/unauthor");
			return mv;
		}else {
			mv = new ModelAndView();
			ex.printStackTrace();
			return mv;
		}
	}
}

