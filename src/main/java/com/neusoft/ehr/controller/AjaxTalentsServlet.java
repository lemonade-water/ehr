package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.HrTalents;
import com.neusoft.ehr.service.ImpServiceFac;
import com.neusoft.ehr.service.MenusService;
import com.neusoft.ehr.service.TalentService;
import com.neusoft.ehr.utils.FactoryUntil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@WebServlet(name = "AjaxTalentsServlet",value="/ajaxtalents")
public class AjaxTalentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //ctrl+alt+H  看到哪个方法被调用

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //只返回数据servlet

        ImpServiceFac talentService = FactoryUntil.getClass(TalentService.class);
        //TalentService talentService = new TalentService();
        //获得前台的请求值
        String queryvalue = request.getParameter("queryvalue");

        int pages=0;//显示页面
        int count=0;//记录总数
        int totalpages=0;//页面总数
        int limit=5;//每页显示记录数

        count=talentService.queryCount(queryvalue);//查询记录条数

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

        //去查询数据
        List<HrTalents> listuser1= new ArrayList<HrTalents>();
        listuser1=talentService.queryAll(pages,limit,queryvalue);


        JSONObject returnJson = new JSONObject();
        JSONArray json = new JSONArray();
        //request.setAttribute("talents",listuser1);
        for(HrTalents hrTalents:listuser1){
            JSONObject jo = new JSONObject();
            jo.put("id",hrTalents.getId());
            jo.put("name",hrTalents.getName());
            jo.put("employdate",hrTalents.getEmployDate());
            jo.put("unemploydate",hrTalents.getUnemployDate());
            jo.put("sexual",hrTalents.getSexual());
            json.put(jo);
        }
        returnJson.put("talents",json);

        //JSONArray json = new JSONArray();
        //request.setAttribute("pages",pages);
        returnJson.put("pages",pages);

        //request.setAttribute("totalpages",totalpages);
        returnJson.put("totalpages",totalpages);

        Writer out = response.getWriter();
        //以json格式返回数据
        //return returnJson;
        out.write(returnJson.toString());
        //out.write(json.toString());
        out.flush();
    }
}
