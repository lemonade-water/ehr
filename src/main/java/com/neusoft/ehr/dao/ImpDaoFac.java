package com.neusoft.ehr.dao;

import com.neusoft.ehr.entity.HrMenus;
import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.entity.HrUsersRoles;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface ImpDaoFac {


    List<Object> queryAll();

    boolean deleteMenu(Long id) throws SQLException;

    void add(String url, String menusname) throws SQLException;

    void add(HrRoles condition);

    List<HrUsersRoles> queryRoles();

    void update(HrRoles condition);

    Collection<HrMenus> queryMenuByRoles(int type);

    int querymenus(long menuId);

    List<HrRoles> queryroles(Long menus);

    boolean addMenuRole(Long menu_id, Long role_id);

    boolean delrolemenu(Long menu_id, Long role_id);
}
