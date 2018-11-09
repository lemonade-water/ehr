package com.neusoft.ehr.service;


import com.neusoft.ehr.entity.*;
import com.neusoft.ehr.utils.Message;

import java.util.Collection;
import java.util.List;

public interface ImpServiceFac {

    List<Object> queryAll();

    Message delrolemenu(Long menu_id, Long role_id);

    Message add(String url, String menusname);

    Message delete(Long del_id);

    int queryCount(String queryvalue);

    List<HrTalents> queryAll(int pages, int limit, String queryvalue);

    HrUsers getUser(HrUsers condition);

    boolean querymenus(Long del_id);

    boolean isHasRoles(Long id, long l);

    Collection<HrMenus> queryMenuByRoles(int i);

    Message addMenuRole(Long menu_id, Long role_id);

    List<HrRoles> queryroles(Long menus_id);
}
