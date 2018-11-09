package com.neusoft.ehr.controller;

import com.neusoft.ehr.service.ImpServiceFac;
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
import java.io.Writer;

@WebServlet(name = "AjaxDelRoleMenuServlet",value = "/delrolemenu")
public class AjaxDelRoleMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long menu_id = Long.valueOf(request.getParameter("menu_id"));
        Long role_id = Long.valueOf(request.getParameter("role_id"));


        ImpServiceFac rolesMenusService = FactoryUntil.getClass(RolesMenusService.class);
        //RolesMenusService rolesMenusService = new RolesMenusService();
        Message message = rolesMenusService.delrolemenu(menu_id,role_id);


        Writer out = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mes_id",message.getMeaasgeId());
        jsonObject.put("mes_name",message.getMessageName());
        out.write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
