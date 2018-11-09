package com.neusoft.ehr.controller;

import com.neusoft.ehr.service.UserService;
import com.neusoft.ehr.utils.FactoryUntil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", value="/user")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = (UserService) FactoryUntil.getObject("UserService");
        List<Object> listuser= new ArrayList<Object>();
        //管理员
        //查询所有的用户
        int pages=0;//显示页面
        int count=0;//记录总数
        int totalpages=0;//页面总数
        int limit=5;//每页显示记录数
        count=userService.queryCount();//查询记录条数
        totalpages=(int) Math.ceil(count/(limit*1.0));//页面总数
        String strPage = request.getParameter("pages");
        //判断需要跳转的页面
        if (strPage == null) {
            pages = 1;
        } else {
            try{
                pages = Integer.parseInt(strPage);//异常字符
            }catch(Exception e){
                pages = 1;
            }if (pages < 1){
                pages = 1;
            }if (pages > totalpages){//大于总页数
                pages = totalpages;
            }
        }
        System.out.println("到了userservlet");
        listuser=userService.queryAllLimit(pages,limit);
        request.setAttribute("users",listuser);
        request.setAttribute("pages",pages);
        request.setAttribute("totalpages",totalpages);
        request.getRequestDispatcher("/WEB-INF/view1/main.jsp").forward(request, response);
    }
}
