package com.neusoft.ehr.dao;

import com.neusoft.cyt.jdbcUtil;
import com.neusoft.ehr.entity.HrMenus;
import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.entity.HrTalents;
import com.neusoft.ehr.entity.HrUsers;
import com.neusoft.ehr.utils.FactoryUntil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    public List<Object> queryAll(String string,int pages,int limit){

        List<Object> list = new ArrayList<Object>();
        String sql =null;
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String tablename = FactoryUntil.getClassName(string);
        if(string.equals("HrTalents")){
            sql="select * from "+tablename+" WHERE  del_flag=0 limit "+(pages-1)*5+" , "+limit;
        }else {
            sql="select * from "+tablename+" limit "+(pages-1)*5+" , "+limit;
        }

        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            conn.commit();
            while (rs.next()){
                //HrUsers
                if(tablename.equals("HrUsers")){
                    HrUsers hrUsers = new HrUsers();
                    hrUsers=rs2User(rs);
                    list.add(hrUsers);
                }
                //HrRoles
                if(tablename.equals("HrRoles")){
                    HrRoles hrRoles = new HrRoles();
                    hrRoles=rs2Roles(rs);
                    list.add(hrRoles);
                }
                //HrTalents
                if(tablename.equals("HrTalents")){
                    HrTalents hrTalents =  new HrTalents();
                    hrTalents=rs2Talents(rs);
                    list.add(hrTalents);
                }
                //HrMenus
                if(tablename.equals("HrMenus")){
                    HrMenus hrMenus = new HrMenus();
                    hrMenus=rs2Menus(rs);
                    list.add(hrMenus);
                }
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }
        return list;
    }

    //查询表的数
    public int queryCount(String string){
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        jdbcUtil.getConnection();
        String sql;
        if(string.equals("HrTalents")){
            sql = "select COUNT(*) from " + FactoryUntil.getClassName(string)+"where del_flag=0";
        }else {
            sql = "select COUNT(*) from " + FactoryUntil.getClassName(string);
        }
        int count = 0;
        try {
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            conn.commit();
            if (rs.next()){
                count =rs.getInt(1);
            }

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }
        return  count;
    }

    /**
     * 根据属性删除将flag设置为
     * @param tableName 返回结果对象类型
     * @param param 查找条件key值
     * @param value 查找条件value值
     * @return
     */
    public Boolean  deleteObject(String tableName,String param,Object value){

        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        //判断参数类型
        String s = value.getClass().getName();
        if (s.equals("class java.lang.Integer")) {
            value = Integer.parseInt(value.toString());

        } else if (s.equals("java.lang.String")) {
            value = "'" + value + "'";
        }
        Boolean result= false;
        if("HrTalents".equals(tableName)){
            String sql=" update  "+tableName+" set del_flag = '1' " + "  where "+param+" = "+value;
            try {
                stmt=conn.prepareStatement(sql);
                int i=0;
                i=stmt.executeUpdate();
                if (i>0){
                    result=true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String sql="delete from "+tableName+" where "+param+" = "+value;
            try {
                stmt=conn.prepareStatement(sql);
                result = stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        jdbcUtil.release(rs,stmt,conn);

        return result;

    }

    public HrUsers rs2User(ResultSet rs) throws SQLException {
        HrUsers user = new HrUsers();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setLastLeaveTime(rs.getTimestamp("last_leave_time"));
        user.setLastLoginTime(rs.getTimestamp("last_login_time"));
        return user;
    }
    public HrRoles rs2Roles(ResultSet rs) throws SQLException{
        HrRoles hrRoles = new HrRoles();
        hrRoles.setId(rs.getLong("id"));
        hrRoles.setRoleName(rs.getString("role_name"));
        hrRoles.setRoleCode(rs.getInt("role_code"));
        return hrRoles;
    }
    public HrTalents rs2Talents(ResultSet rs) throws SQLException{
        HrTalents hrTalents =new HrTalents();
        hrTalents.setId(rs.getLong("id"));
        hrTalents.setDelFlag(rs.getString("del_flag"));
        hrTalents.setEmployDate(rs.getDate("employ_date"));
        hrTalents.setUnemployDate(rs.getDate("unemploy_date"));
        hrTalents.setName(rs.getString("name"));
        hrTalents.setSexual(rs.getInt("sexual"));
        return hrTalents;
    }
    public  HrMenus rs2Menus(ResultSet rs) throws SQLException{
        HrMenus hrMenus =new HrMenus();
        hrMenus.setId(rs.getLong("id"));
        hrMenus.setUrl(rs.getString("url"));
        hrMenus.setName(rs.getString("name"));
        return hrMenus;

    }
}
