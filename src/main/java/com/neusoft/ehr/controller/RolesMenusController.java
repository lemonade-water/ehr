package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.entity.HrRolesMenus;
import com.neusoft.ehr.service.RoleService;
import com.neusoft.ehr.service.RolesMenusService;
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
public class RolesMenusController extends HttpServlet {

    //反射实例化
    private RolesMenusService rolesMenusService = (RolesMenusService) FactoryUntil.getObject("RolesMenusService");
    private HrRolesMenus conditon = (HrRolesMenus) FactoryUntil.getObject("HrRolesMenus");
    private String tableName = FactoryUntil.getClassName("HrRolesMenus");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取URL
        String servletPath = request.getServletPath();
        //得到方法名，除去.rmdo
        String methodName = servletPath.substring(1,servletPath.length()-5);

        //利用反射获取方法
        try {
            Method method =getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);//执行该方法
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

    }

    //修改
    public void update(HttpServletRequest request,HttpServletResponse response){

    }

    //查询所有
    public void queryAll(HttpServletRequest request,HttpServletResponse response){

    }
}
