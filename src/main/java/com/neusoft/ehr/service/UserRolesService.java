package com.neusoft.ehr.service;

import com.neusoft.ehr.dao.UserRoleDao;
import com.neusoft.ehr.entity.*;
import com.neusoft.ehr.utils.FactoryUntil;
import com.neusoft.ehr.utils.Message;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */
public class UserRolesService implements ImpServiceFac{

    UserRoleDao userRoleDao = (UserRoleDao) FactoryUntil.getObject("UserRoleDao");
    //判定是否有用户的角色
    public boolean isHasRoles(Long id, long l) {
        return  userRoleDao.isHasRoles(id,l);
    }
    //查询用户权限信息
    public List<HrUsersRoles> queryKey(Long user_id){
        return  userRoleDao.queryKey(user_id);
    }

    public void delete(Long user_id, Long role_id) {
        userRoleDao.delete(user_id,role_id);
    }

    public void add(Long user_id, Long role_id) {
        userRoleDao.add(user_id,role_id);
    }

    public void update(Long user_id, Long role_id) {
        userRoleDao.update(user_id,role_id);
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
