package com.ucomponent.authent.web;

import com.ucomponent.base.ICommons;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.ucomponent.base.ICommons.USER_PATH_SET;

/**
 * 2019/6/14
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@Configuration
public class FtlRoleCheck implements TemplateMethodModelEx, ICommons {
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public Object exec(List args)
					throws TemplateModelException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String getRole = args.get(0).toString();
		Subject subject = SecurityUtils.getSubject();
		Session ssession = subject.getSession();
		Iterator<String> it = ((Set)ssession.getAttribute(USER_PATH_SET)).iterator();
		while (it.hasNext()) {
			String strpath = it.next();
			if(strpath.equals(getRole)){
				return "";
			}
		}
		return "style=\"display: none;\"";
	}
}
