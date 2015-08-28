package com.imooc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.service.MaintainService;

/*
 * 批量删除控制类
 */
@SuppressWarnings("serial")
public class DeleteBatchServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			//设置页面编码
			req.setCharacterEncoding("UTF-8");//可以写成过滤器
			//接收页面的值
			String[] ids = req.getParameterValues("id");
			MaintainService maintainService = new MaintainService();
			maintainService.deleteBatch(ids);
			//页面跳转
			req.getRequestDispatcher("/list.action").forward(req, resp);
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			this.doGet(req, resp);
		}
}
