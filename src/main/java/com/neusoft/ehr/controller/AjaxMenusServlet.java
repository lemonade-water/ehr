package com.neusoft.ehr.controller;

import com.neusoft.ehr.service.ImpServiceFac;
import com.neusoft.ehr.service.MenusService;
import com.neusoft.ehr.service.RolesMenusService;
import com.neusoft.ehr.utils.FactoryUntil;
import com.neusoft.ehr.utils.Message;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(name = "AjaxMenusServlet",value ="/ajaxAddMenu")
public class AjaxMenusServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String url = request.getParameter("url");
            String menusname = request.getParameter("menusname");
            Message message = new Message();
            if("/".equals(url.substring(0, 1))||url!=""||menusname!=""){
                //add
                ImpServiceFac menusService = FactoryUntil.getClass(MenusService.class);
                //MenusService menusService = new MenusService();
                message = menusService.add(url,menusname);

            }else{
                message.setMeaasgeId(400);
                message.setMessageName("输入错误！");
            }
            Writer writer = response.getWriter();
            JSONObject  jsonObject = new JSONObject();
            jsonObject.put("mesId",message.getMeaasgeId());
            jsonObject.put("mesName",message.getMessageName());
            writer.write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long del_id = Long.valueOf(request.getParameter("delete_id"));
        Message message=new Message();

        ImpServiceFac menusService = FactoryUntil.getClass(MenusService.class);
        //MenusService menusService = new MenusService();
        //查询rolesmenus里面有没有这个id

        ImpServiceFac rolesMenusService = FactoryUntil.getClass(RolesMenusService.class);
        //RolesMenusService rolesMenusService = new RolesMenusService();

        if(rolesMenusService.querymenus(del_id)){

            message=menusService.delete(del_id);
        }else {
            message.setMeaasgeId(400);
            message.setMessageName("请先删除已有用户的权限");
        }

        Writer writer = response.getWriter();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("delmesId",message.getMeaasgeId());
        jsonObject.put("delmesName",message.getMessageName());
        //System.out.println(jsonObject.toString());
        writer.write(jsonObject.toString());
    }

}
