package com.fiveone.edm.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.fiveone.edm.common.utils.JSONUtils;


/**
 * 基础的Controller
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月30日 下午3:10:32
 * @version: 1.0
 * @since: JDK1.7
 */
public abstract class BaseController {

	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	protected HttpSession session;
	
	protected ModelAndView mav;
	
	/**
	 * 操作结果
	 * 以json的格式传至调用者
	 * @param result
	 * @param msg
	 * @return
	 */
	public String operationResult(boolean result, String msg) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("msg", msg);
		try {
			return JSONUtils.writeJson(map);
		} catch (IOException e) {
			e.printStackTrace();
			return "exception";
		}
	}
	
	/**
	 * 初始化对象
	 * @param request
	 * @param response
	 * @param mav
	 */
	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.mav = mav;
	}
	
	/**
	 * 输出流信息
	 * @param content
	 */
	public void outwrite(String content) {
		PrintWriter out = null;
		response.setContentType("text/html; charset=UTF-8");
		try {
			out = response.getWriter();
			out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			out.flush();
			out.close();
		}
	}
}
