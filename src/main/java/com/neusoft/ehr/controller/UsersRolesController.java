package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.entity.HrUsersRoles;
import com.neusoft.ehr.service.UserRolesService;
import com.neusoft.ehr.utils.FactoryUntil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/29.
 */
public class UsersRolesController extends HttpServlet {

    //反射实例化
    private UserRolesService userRolesService= (UserRolesService) FactoryUntil.getObject("UserRolesService");
    private HrUsersRoles conditon = (HrUsersRoles) FactoryUntil.getObject("HrUsersRoles");
    private String tableName = FactoryUntil.getClassName("HrUsersRoles");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取URL
        String servletPath = request.getServletPath();
        //得到方法名，除去.urdo
        String methodName = servletPath.substring(1,servletPath.length()-5);

        //利用反射获取方法
        try {
            Method method =getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);//执行该方法
            if (methodName.equals("queryKey")){
                request.getRequestDispatcher("roles").forward(request,response);
            }else {
                response.sendRedirect("roles");
            }
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

    //删除权限
    public  void delete(HttpServletRequest request,HttpServletResponse response){
        Long user_id = Long.valueOf(request.getParameter("user_id"));
        Long role_id = Long.valueOf(request.getParameter("role_id"));

        userRolesService.delete(user_id,role_id);
    }

    //新增
    public void add(HttpServletRequest request,HttpServletResponse response){
        Long user_id = Long.valueOf(request.getParameter("user_id"));
        Long role_id = Long.valueOf(request.getParameter("role_id"));
        userRolesService.add(user_id,role_id);
    }

    //修改
    public void update(HttpServletRequest request,HttpServletResponse response){
        Long user_id = Long.valueOf(request.getParameter("user_id"));
        Long role_id = Long.valueOf(request.getParameter("role_id"));
        userRolesService.update(user_id,role_id);
    }

    //查询用户权限
    public void queryKey(HttpServletRequest request,HttpServletResponse response){
        Long user_id = Long.valueOf(request.getParameter("queryKey"));
        List<HrUsersRoles> list = new ArrayList<HrUsersRoles>();
        int flag=1;
        list = userRolesService.queryKey(user_id);
        request.setAttribute("flag",flag);
        request.setAttribute("list",list);

    }
}
