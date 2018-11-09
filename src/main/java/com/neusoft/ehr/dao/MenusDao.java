package com.neusoft.ehr.dao;

import com.neusoft.cyt.jdbcUtil;
import com.neusoft.ehr.entity.HrMenus;
import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.entity.HrUsersRoles;
import com.neusoft.ehr.utils.FactoryUntil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/7/28.
 */
public class MenusDao implements ImpDaoFac{
    public List<Object> queryAll(){
        List<Object> list = new ArrayList<Object>();
        Connection conn = jdbcUtil.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from hrmenus";
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                HrMenus hrMenus = new HrMenus();
                hrMenus.setId(rs.getLong("id"));
                hrMenus.setName(rs.getString("name"));
                hrMenus.setUrl(rs.getString("url"));
                list.add(hrMenus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }
        return list;
    }

    public void add(String url,String menusname) throws SQLException {
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "insert into hrmenus(url,name) values(?,?)";
        try {
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,url);
            preparedStatement.setString(2,menusname);
            conn.setAutoCommit(false);
            preparedStatement.execute();
            conn.commit();
            //stmt = conn.createStatement();
            //rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,preparedStatement,conn);
        }
    }

    public void add(HrRoles condition) {

    }


    //删除
    public boolean deleteMenu(Long id) throws SQLException {
        Boolean x=false;
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "delete from hrmenus where id=?";
        try {
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            x = preparedStatement.execute();
            //stmt = conn.createStatement();
            //rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,preparedStatement,conn);
        }
        return x;
    }

    public List<HrUsersRoles> queryRoles() {
        return null;
    }

    public void update(HrRoles condition) {

    }

    public Collection<HrMenus> queryMenuByRoles(int type) {
        return null;
    }

    public int querymenus(long menuId) {
        return 0;
    }

    public List<HrRoles> queryroles(Long menus) {
        return null;
    }

    public boolean addMenuRole(Long menu_id, Long role_id) {
        return false;
    }

    public boolean delrolemenu(Long menu_id, Long role_id) {
        return false;
    }
}
