package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.service.ImpServiceFac;
import com.neusoft.ehr.service.MenusService;
import com.neusoft.ehr.service.RoleService;
import com.neusoft.ehr.service.RolesMenusService;
import com.neusoft.ehr.utils.FactoryUntil;
import com.neusoft.ehr.utils.Message;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MenuqueServlet",value = "/querole")
public class MenuqueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long menu_id = Long.valueOf(request.getParameter("role_id"));
        Long role_id = Long.valueOf(request.getParameter("menu_id"));


        ImpServiceFac rolesMenusService = FactoryUntil.getClass(RolesMenusService.class);
        //RolesMenusService rolesMenusService = new RolesMenusService();



        Message message = rolesMenusService.addMenuRole(menu_id,role_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mes_id",message.getMeaasgeId());
        jsonObject.put("mes_name",message.getMessageName());
        Writer out = response.getWriter();
        out.write(jsonObject.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long menus_id = Long.valueOf(request.getParameter("query_id"));
            JSONArray array = new JSONArray();
            List<HrRoles> list = new ArrayList<HrRoles>();

            ImpServiceFac rolesMenusService = FactoryUntil.getClass(RolesMenusService.class);
            //RolesMenusService rolesMenusService =new RolesMenusService();
            list = rolesMenusService.queryroles(menus_id);
            for(HrRoles hrRoles : list){
                JSONObject jsonObject =new JSONObject();
                jsonObject.put("roles_id",hrRoles.getId());
                jsonObject.put("roles_name",hrRoles.getRoleName());
                array.put(jsonObject);
            }
            Writer out = response.getWriter();
            out.write(array.toString());
    }
}
