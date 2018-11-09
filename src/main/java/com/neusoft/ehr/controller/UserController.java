package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrUsers;
import com.neusoft.ehr.service.UserService;
import com.neusoft.ehr.utils.FactoryUntil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/7/29.
 */
public class UserController extends HttpServlet {

    //反射实例化
    private UserService userService = (UserService) FactoryUntil.getObject("UserService");
    private HrUsers condition = (HrUsers) FactoryUntil.getObject("HrUsers");
    private  String tableName =FactoryUntil.getClassName("HrUsers");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取URL
        String servletPath = request.getServletPath();
        //得到方法名，除去.udo
        String methodName = servletPath.substring(1,servletPath.length()-4);

        //利用反射获取方法
        try {
            Method method =getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);//执行该方法
            response.sendRedirect("user");
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
        Long id = Long.valueOf(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        condition.setId(id);
        condition.setUsername(username);
        condition.setPassword(password);
        userService.add(condition);
    }

    //修改
    public void update(HttpServletRequest request,HttpServletResponse response){

        Long id = Long.valueOf(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        condition.setId(id);
        condition.setUsername(username);
        condition.setPassword(password);
        userService.update(condition);
    }

    //查询所有
    public void queryAll(HttpServletRequest request,HttpServletResponse response){

    }

    //删除
    public void delete(HttpServletRequest request,HttpServletResponse response){
        int id =Integer.parseInt(request.getParameter("userid"));
        System.out.println(id);
        userService.deleteUsers(tableName,"id",id);
    }
}
