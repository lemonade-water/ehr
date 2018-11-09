package com.neusoft.ehr.service;


import com.neusoft.ehr.dao.Dao;
import com.neusoft.ehr.dao.UserDao;
import com.neusoft.ehr.entity.HrMenus;
import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.entity.HrTalents;
import com.neusoft.ehr.entity.HrUsers;
import com.neusoft.ehr.utils.FactoryUntil;
import com.neusoft.ehr.utils.Message;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * 处理user类的具体服务
 */
public class UserService implements ImpServiceFac{

    private Dao dao = new Dao();
    public HrUsers getUser(HrUsers condition){
        //实例化
        UserDao userDao = (UserDao) FactoryUntil.getObject("UserDao");
        HrUsers hrUsers = userDao.getUser(condition);

        try{
            if(hrUsers!=null){

                return hrUsers;
            }else {

                return null;
            }
        }catch (Exception e){

            return null;
        }
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

    public List<HrUsers> queryAllUser(){

        return new UserDao().queryAllUser();
    }
    //分页
    public List<Object> queryAllLimit(int pages, int limit){
        Dao dao =new Dao();
        return  new Dao().queryAll("HrUsers",pages,limit);
    }
    public int queryCount(){

        return new Dao().queryCount("HrUsers");
    }

    //修改登录时间
    public  void  updateTime(Long id,String login,String out){
        UserDao userDao = (UserDao) FactoryUntil.getObject("UserDao");

        userDao.updateTime(id,login,out);
    }

    //lbf
    //删除用户
    public  Boolean deleteUsers(String tableName,String param,int value ){

        return  dao.deleteObject(tableName,param,value);
    }

    //新增用户
    public void add(HrUsers condition) {
        UserDao userDao = (UserDao) FactoryUntil.getObject("UserDao");
        userDao.add(condition);
    }
    //修改
    public void update(HrUsers condition) {
        UserDao userDao = (UserDao) FactoryUntil.getObject("UserDao");
        userDao.update(condition);
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
}
