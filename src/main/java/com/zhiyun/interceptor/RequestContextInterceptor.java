package com.zhiyun.interceptor;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyun.entity.*;
import com.zhiyun.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhiyun.base.config.Config;
import com.zhiyun.base.util.CommonUtils;
import com.zhiyun.client.UserHolder;
import com.zhiyun.context.OnlineUser;
import com.zhiyun.context.RequestHolder;

/**
 * 请求拦截。<br>
 * @author 庄超
 */
public class RequestContextInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

	@Resource
	private Config config;
	@Resource
	private CasUserService casUserService;
	@Resource
	private CasCompanyService casCompanyService;
	@Resource
	private UserService userService;
	@Resource
    IcloudOnicloudService icloudOnicloudService;

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		RequestHolder.init();
		OnlineUser user = (OnlineUser) request.getSession().getAttribute("user");
		// 判断是否调试模式
		if (config.getIsDev()) {
			// 调试模式使用默认账户
			user = new OnlineUser();
			user.setCompanyId(1L);
			user.setId(20155L);
			user.setAccountName("ptest");
			user.setUserName("ptest");
		} else {
			if (user == null) {
				response.setHeader("Content-type", "application/json;charset=UTF-8");
				response.getWriter().write("{\"errorMsg\":\"没有访问权限,请先登录账号\"}");
				return false;
			}
			// 通过登录账号查询用户信息
			CasUser casUser = new CasUser();
			casUser.setAccount(user.getAccountName());

			User liferayUser = userService.findUserByScreenNameAndEmail(user.getAccountName());

			IcloudOnicloud icloudOnicloud = icloudOnicloudService.getOnIcloudByUserId(liferayUser.getUserid());
			if (liferayUser != null) {
				// 设置用户信息
				user.setId(liferayUser.getUserid());
				user.setAccountName(liferayUser.getScreenname());
				user.setUserName(liferayUser.getFirstname() + liferayUser.getLastname());
				user.setId(liferayUser.getUserid());
				if (icloudOnicloud != null) {
					user.setCompanyId(icloudOnicloud.getOrganizationId());
					user.setCompanyName(icloudOnicloud.getName());
				}

				request.getSession().setAttribute("user", user);
			} else {
				response.setHeader("Content-type", "application/json;charset=UTF-8");
				response.getWriter().write("{\"errorMsg\":\"账号信息不匹配,请重新登录\"}");
				return false;
			}

		}
		UserHolder.setUser(user);
		return true;

	}
 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

}
