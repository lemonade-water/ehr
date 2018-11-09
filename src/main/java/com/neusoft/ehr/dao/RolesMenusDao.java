package com.neusoft.ehr.dao;

import com.neusoft.cyt.jdbcUtil;
import com.neusoft.ehr.entity.*;
import com.neusoft.ehr.utils.FactoryUntil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */
public class RolesMenusDao implements ImpDaoFac{

    //根据角色不同获得不同菜单
    public Collection<HrMenus> queryMenuByRoles(int type) {
        //type 是角色的id
        Collection<HrMenus> collection = new ArrayList<HrMenus>();
        //SELECT hrmenus.`name`,hrmenus.url from hrrolesmenus,hrmenus WHERE hrmenus.id=hrrolesmenus.menu_id and hrrolesmenus.role_id=1;
        String sql = "SELECT hrmenus.id,hrmenus.`name`,hrmenus.url from hrrolesmenus,hrmenus WHERE hrmenus.id=hrrolesmenus.menu_id and hrrolesmenus.role_id=?";
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HrUsers resultUser = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, type);
            rs = stmt.executeQuery();
            while (rs.next()) {
                HrMenus hrMenus = (HrMenus) FactoryUntil.getObject("HrMenus");
                hrMenus.setId(rs.getLong("id"));
                hrMenus.setName(rs.getString("name"));
                hrMenus.setUrl(rs.getString("url"));
                collection.add(hrMenus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.release(rs, stmt, conn);
        }
        return collection;
    }

    public List<Object> queryAll() {
        List<Object> list = new ArrayList<Object>();
        //SELECT hrmenus.`name`,hrmenus.url from hrrolesmenus,hrmenus WHERE hrmenus.id=hrrolesmenus.menu_id and hrrolesmenus.role_id=1;
        //String sql ="select distinct(hrroles.id),hrroles.role_name,hrmenus.`name` from hrroles,hrrolesmenus,hrmenus where hrrolesmenus.menu_id=hrmenus.id and hrroles.id=hrrolesmenus.role_id ORDER BY hrroles.id";
        String sql ="select hrroles.id,hrroles.role_name,hrmenus.`name`,hrmenus.id as menuid from hrroles,hrrolesmenus,hrmenus where hrrolesmenus.menu_id=hrmenus.id and hrroles.id=hrrolesmenus.role_id ";
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                HrRolesMenus hrRolesMenus = new HrRolesMenus();
                HrMenus hrMenus = new HrMenus();
                hrMenus.setId(rs.getLong("menuid"));
                hrMenus.setName(rs.getString("name"));
                hrRolesMenus.setMenuHrMenus(hrMenus);

                HrRoles hrRoles = new HrRoles();
                hrRoles.setId(rs.getLong("id"));
                hrRoles.setRoleName(rs.getString("role_name"));
                hrRolesMenus.setRoleHrRoles(hrRoles);
                list.add(hrRolesMenus);
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            jdbcUtil.release(rs, stmt, conn);
        }
        return list;
    }

    public boolean deleteMenu(Long id) throws SQLException {
        return false;
    }

    public void add(String url, String menusname) throws SQLException {

    }

    public void add(HrRoles condition) {

    }

    public List<HrUsersRoles> queryRoles() {
        return null;
    }

    public void update(HrRoles condition) {

    }

    public int querymenus(long menus){
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("select * from hrrolesmenus where  menu_id= ? ");
            stmt.setLong(1,menus);
            rs = stmt.executeQuery();
            while(rs.next()){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }
        return 0;
    }
    //查询出没有此菜单的角色
    public  List<HrRoles> queryroles(Long menus){
        List<HrRoles> list = new ArrayList<HrRoles>();
        //SELECT hrmenus.`name`,hrmenus.url from hrrolesmenus,hrmenus WHERE hrmenus.id=hrrolesmenus.menu_id and hrrolesmenus.role_id=1;
        String sql = "SELECT hrroles.id,hrroles.role_name from hrroles WHERE hrroles.id not in (SELECT hrrolesmenus.role_id from hrrolesmenus where hrrolesmenus.menu_id=?) ";
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,menus);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //应该有id和角色名字
                HrRoles hrRoles = new HrRoles();
                hrRoles.setId(rs.getLong("id"));
                hrRoles.setRoleName(rs.getString("role_name"));
                list.add(hrRoles);
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            jdbcUtil.release(rs, stmt, conn);
        }
        return list;
    }

    public boolean addMenuRole(Long menu_id,Long role_id){
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement ps =null;
        try {
            ps = conn.prepareStatement("insert into hrrolesmenus(menu_id,role_id) values(?,?)");
            ps.setLong(1,menu_id);
            ps.setLong(2,role_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }finally {
            jdbcUtil.release(null, ps, conn);
        }
    }

    public boolean delrolemenu(Long menu_id,Long role_id){
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement ps =null;
        try {
            ps = conn.prepareStatement("delete from hrrolesmenus where menu_id = ? and role_id = ?");
            ps.setLong(1,menu_id);
            ps.setLong(2,role_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }finally {
            jdbcUtil.release(null, ps, conn);
        }

    }
}
