package com.neusoft.ehr.service;

import com.neusoft.ehr.dao.Dao;
import com.neusoft.ehr.dao.ImpDaoFac;
import com.neusoft.ehr.dao.MenusDao;
import com.neusoft.ehr.dao.RoleDao;
import com.neusoft.ehr.entity.*;
import com.neusoft.ehr.utils.FactoryUntil;
import com.neusoft.ehr.utils.Message;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */
public class RoleService implements ImpServiceFac{
    Dao dao=new Dao();
    //RoleDao roleDao  = new RoleDao();
    ImpDaoFac roleDao = FactoryUntil.getClass(RoleDao.class);
    //获得不同角色的菜单


    //删除权限角色
    public  Boolean deleteRole(String tableName,String param,int value ){

        return  dao.deleteObject(tableName,param,value);
    }
    //查询所有角色信息
    public List<HrUsersRoles> queryRoles() {
        return  roleDao.queryRoles();
    }
    //新增用户
    public void add(HrRoles condition) {
        roleDao.add(condition);
    }
    //修改
    public void update(HrRoles condition) {

        roleDao.update(condition);
    }

    public List<Object> queryAll() {
        return null;
    }

    public Message delrolemenu(Long menu_id, Long role_id) {
        return null;
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

    public Collection<HrMenus> queryMenuByRoles(int i) {
        return null;
    }

    public Message addMenuRole(Long menu_id, Long role_id) {
        return null;
    }

    public List<HrRoles> queryroles(Long menus_id) {
        return null;
    }
}
