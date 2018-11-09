package com.neusoft.ehr.service;

import com.neusoft.ehr.dao.Dao;
import com.neusoft.ehr.dao.TalentDao;
import com.neusoft.ehr.entity.HrMenus;
import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.entity.HrTalents;
import com.neusoft.ehr.entity.HrUsers;
import com.neusoft.ehr.utils.FactoryUntil;
import com.neusoft.ehr.utils.Message;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */
public class TalentService implements ImpServiceFac{

    Dao dao=new Dao();
    TalentDao talentDao = (TalentDao) FactoryUntil.getObject("TalentDao");

    //删除人才库 设置del_flag
    public  Boolean deleteTalent(String tableName,String param,int value ){

        return  dao.deleteObject(tableName,param,value);
    }

    //分页查询所有
    public List<HrTalents> queryAll(int pages, int limit) {

        return talentDao.queryAll(pages,limit);
    }
    public List<HrTalents> queryAll(int pages, int limit,String query) {

        return talentDao.queryAll(pages,limit,query);
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

    public List<Object> queryAll(String string,int pages, int limit) {

        return dao.queryAll(string,pages,limit);
    }

    //查询总记录数
    public int queryCount() {

        return talentDao.queryCount();
    }
    public int queryCount(String query) {

        return talentDao.queryCount(query);
    }
    //新增
    public void add(HrTalents hrTalents) {
        talentDao.add(hrTalents);
    }
    public void add2(HrTalents hrTalents) {
        talentDao.add2(hrTalents);
    }
    //更新
    public void update(HrTalents hrTalents) {
        talentDao.update(hrTalents);
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
}
