package com.neusoft.ehr.service;

import com.neusoft.ehr.dao.ImpDaoFac;
import com.neusoft.ehr.dao.MenusDao;
import com.neusoft.ehr.dao.RolesMenusDao;
import com.neusoft.ehr.entity.*;
import com.neusoft.ehr.utils.FactoryUntil;
import com.neusoft.ehr.utils.Message;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */
public class RolesMenusService implements ImpServiceFac{
    ImpDaoFac rolesMenusDao = FactoryUntil.getClass(RolesMenusDao.class);
    //获得不同角色的菜单
    public Collection<HrMenus> queryMenuByRoles(int type){
        //type 是角色的id
        Collection<HrMenus> list;
        //调用HrRolesMenusDao

        //RolesMenusDao rolesMenusDao = (RolesMenusDao) FactoryUntil.getObject("RolesMenusDao");
        list = rolesMenusDao.queryMenuByRoles(type);
        return  list;
    }
    public List<Object> queryAll(){
        //RolesMenusDao rolesMenusDao = new RolesMenusDao();
        List<Object> list = rolesMenusDao.queryAll();
        return list;
    }
    //查询角色菜单里面的是否有角色用菜单
    public Boolean querymenus(long menuId){
        //RolesMenusDao rolesMenusDao = new RolesMenusDao();
        Message message = new Message();
        if(rolesMenusDao.querymenus(menuId)==1){
            //有角色拥有这个信息
            return false;
        }else {
            return true;
        }
    }
    public List<HrRoles> queryroles(Long menus){
        //查询出没有菜单的所有角色
        //RolesMenusDao rolesMenusDao =new RolesMenusDao();
        //rolesMenusDao.queryroles(menus);
        return  rolesMenusDao.queryroles(menus);
    }
    public Message addMenuRole(Long menu_id,Long role_id){
        //RolesMenusDao rolesMenusDao = new RolesMenusDao();
        Message message = new Message();
        if(rolesMenusDao.addMenuRole(menu_id,role_id)){
            message.setMeaasgeId(200);
            message.setMessageName("ok");
        }else {
            message.setMeaasgeId(500);
            message.setMessageName("增加错误!");
        }
        return message;
    }

    public Message delrolemenu(Long menu_id,Long role_id){
        Message message = new Message();
        //RolesMenusDao rolesMenusDao = new RolesMenusDao();
        if(rolesMenusDao.delrolemenu(menu_id,role_id)){
            message.setMeaasgeId(200);
            message.setMessageName("ok");
        }else {
            message.setMeaasgeId(500);
            message.setMessageName("删除错误");
        }
        return  message;
    }

    public Message add(String url, String menusname) {
        return null;
    }

    public Message delete(Long del_id) {
        return null;
    }

    public int queryCount(String queryvalue) {
        return 0;
    }

    public List<HrTalents> queryAll(int pages, int limit, String queryvalue) {
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
}
