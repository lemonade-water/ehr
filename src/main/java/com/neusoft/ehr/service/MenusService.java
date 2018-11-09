package com.neusoft.ehr.service;

import com.neusoft.ehr.dao.Dao;
import com.neusoft.ehr.dao.ImpDaoFac;
import com.neusoft.ehr.dao.MenusDao;
import com.neusoft.ehr.entity.*;
import com.neusoft.ehr.utils.FactoryUntil;
import com.neusoft.ehr.utils.Message;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */
public class MenusService implements ImpServiceFac{

    ImpDaoFac menusDao = FactoryUntil.getClass(MenusDao.class);
    public List<Object> queryAll(){

        //MenusDao menusDao = new MenusDao();
        List<Object> list = menusDao.queryAll();
        return  list;
    }



    public Message addMenuRole(Long menu_id, Long role_id) {
        return null;
    }

    public List<HrRoles> queryroles(Long menus_id) {
        return null;
    }


    //删除菜单
    public  Boolean deleteMenus(String tableName,String param,int value ){
        Dao dao =new Dao();
        return  dao.deleteObject(tableName,param,value);
    }
    //增加
    public Message add(String url,String menusname){
        Message m = new Message();
        try {
            menusDao = new MenusDao();
            menusDao.add(url, menusname);
            m.setMeaasgeId(200);
            m.setMessageName("ok!");
        }catch (Exception e){
            m.setMeaasgeId(500);
            m.setMessageName("false!");
        }
        return  m;
    }
    //删除菜单
    public Message delete(Long id){
        Message message = new Message();
        menusDao = new MenusDao();
        try{
            if(!menusDao.deleteMenu(id)){
                message.setMeaasgeId(200);
                message.setMessageName("ok");
            }else{
                message.setMeaasgeId(500);
                message.setMessageName("请先删除用户权限！");
            }

        }catch (Exception e){
            message.setMeaasgeId(500);
            message.setMessageName("服务器错误！");
        }
        return  message;
    }

    public int queryCount(String queryvalue) {
        return 0;
    }

    public List<HrTalents> queryAll(int pages, int limit, String queryvalue) {
        return null;
    }

    public Message delrolemenu(Long menu_id, Long role_id) {
        return null;
    }

    public HrUsers getUser(HrUsers condition) {
        return null;
    }

    public boolean querymenus(Long del_id) {
        return false;
    }

    public boolean isHasRoles(Long id, long l) {
        return false;
    }

    public Collection<HrMenus> queryMenuByRoles(int i) {
        return null;
    }
}
