package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrTalents;
import com.neusoft.ehr.service.TalentService;
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

@WebServlet(name = "TalentsServlet",value=("/talents"))
public class TalentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String queryvalue = request.getParameter("queryvalue");

        TalentService talentService = (TalentService) FactoryUntil.getObject("TalentService");
        List<Object> listuser= new ArrayList<Object>();
        //管理员
        //查询所有的用户
        int pages=0;//显示页面
        int count=0;//记录总数
        int totalpages=0;//页面总数
        int limit=5;//每页显示记录数
        if(queryvalue!=null){
            count=talentService.queryCount(queryvalue);//查询记录条数
        }else{
            count=talentService.queryCount();
        }
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
        //System.out.println("到了talentsservlet");
        request.setAttribute("pages",pages);
        request.setAttribute("totalpages",totalpages);
        if(queryvalue==null){
            listuser=talentService.queryAll("HrTalents",pages,limit);
            request.setAttribute("talents",listuser);
        }else {
            List<HrTalents> listuser1= new ArrayList<HrTalents>();
            listuser1=talentService.queryAll(pages,limit,queryvalue);
            request.setAttribute("talents",listuser1);
        }

        request.getRequestDispatcher("/WEB-INF/view1/talents.jsp").forward(request, response);
    }
}
