package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrTalents;
import com.neusoft.ehr.service.TalentService;
import com.neusoft.ehr.utils.DateUtils;
import com.neusoft.ehr.utils.FactoryUntil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2018/7/29.
 */
public class TalentController extends HttpServlet {

    private TalentService talentService = (TalentService) FactoryUntil.getObject("TalentService");
    private HrTalents hrTalents = (HrTalents) FactoryUntil.getObject("HrTalents");
    private String tableName = FactoryUntil.getClassName("HrTalents");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();
        String methodname = servletPath.substring(1,servletPath.length()-4);
        try {
            Method method = getClass().getDeclaredMethod(methodname,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);
            response.sendRedirect("talents");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

    //新增
    public void add(HttpServletRequest request,HttpServletResponse response){

        response.setCharacterEncoding("utf-8");

        Long  id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("username");
        try {
            String name2 = new String(name.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String t_code= request.getParameter("t_code");
        int sexual = Integer.parseInt(request.getParameter("sexual"));
//        String s1 =request.getParameter("employ_date");
//        Date employ_date = DateUtils.toDate(s1);//转换为Date
//        String s2 = request.getParameter("unemploy_date");
//        Date unemploy_date = DateUtils.toDate(s2);
        hrTalents =new  HrTalents(id,name,t_code,sexual);
        //System.out.println("--------add------------------");
        //执行操作
        talentService.add2(hrTalents);
    }

    //修改
    public void update(HttpServletRequest request,HttpServletResponse response){
        HrTalents hrTalents =new  HrTalents();
        Long  id = Long.valueOf(request.getParameter("id"));
        //System.out.println(id);
        String name = request.getParameter("username");
        //String t_code= request.getParameter("t_code");
        int sexual = Integer.parseInt(request.getParameter("sexual"));
        String s1 =request.getParameter("employ_date");
        Date employ_date = DateUtils.toDate(s1);
        hrTalents.setEmployDate(employ_date);

        String s2 = request.getParameter("unemploy_date");
        Date unemploy_date = DateUtils.toDate(s2);
        hrTalents.setUnemployDate(unemploy_date);
        //String s1 =request.getParameter("employ_date");
        //Date employ_date = DateUtils.toDate(s1);//转换为Date
        //String s2 = request.getParameter("unemploy_date");
        //Date unemploy_date = DateUtils.toDate(s2);
        hrTalents.setSexual(sexual);
        hrTalents.setId(id);
        hrTalents.setName(name);
        talentService.update(hrTalents);
    }

    //查询所有   分页查询
    public void queryAll(HttpServletRequest request, HttpServletResponse response){

        List<HrTalents> list= new ArrayList<HrTalents>();
        int pages=0;//显示页面
        int count=0;//记录总数
        int totalpages=0;//页面总数
        int limit=5;//每页显示记录数

        count=talentService.queryCount();//查询记录条数

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
            }

            if (pages < 1){
                pages = 1;
            }

            if (pages > totalpages){//大于总页数
                pages = totalpages;
            }
        }

        list=talentService.queryAll(pages,limit);


        request.getSession().setAttribute("list",list);


    }

    //删除操作
    public Boolean delete(HttpServletRequest request, HttpServletResponse response) {
        int id =Integer.parseInt(request.getParameter("talentid"));

        return talentService.deleteTalent(tableName, "id", id);
    }
}
