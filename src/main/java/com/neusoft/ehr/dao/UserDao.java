package com.neusoft.ehr.dao;

import com.neusoft.cyt.jdbcUtil;
import com.neusoft.ehr.entity.HrUsers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public HrUsers getUser(HrUsers condition){
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HrUsers resultUser = null;
        try {
            stmt = conn.prepareStatement("select * from hrusers where username = ? and password = ? ");
            stmt.setString(1,condition.getUsername());
            stmt.setString(2,condition.getPassword());
            rs = stmt.executeQuery();
            while(rs.next()){
                resultUser = rs2User(rs);
                return resultUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }
        return null;
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

    /*public static void main(String[] args) {
        UserDao userDao = new UserDao();
        HrUsers hrUsers = new HrUsers("15202121","123");
        HrUsers hrUsers2 = userDao.getUser(hrUsers);
        hrUsers2.toString();

    }*/

    //查询所有的用户
    public List<HrUsers> queryAllUser(){
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<HrUsers> list = new ArrayList<HrUsers>();
        try {
            stmt = conn.prepareStatement("select * from hrusers");
            rs = stmt.executeQuery();
            while(rs.next()){
                HrUsers resultUser = null;
                resultUser = rs2User(rs);
                list.add(resultUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }
        return list;
    }

    //更新登录时间
    public void updateTime(Long id,String login,String out){
            Connection connection = jdbcUtil.getConnection();
            PreparedStatement ps = null;
            String sql = "update hrusers set last_login_time='?',last_leave_time='?' where id=?";
        try {
            ps= connection.prepareStatement(sql);
            ps.setTimestamp(1,Timestamp.valueOf(login));
            ps.setTimestamp(2,Timestamp.valueOf(out));
            ps.setLong(3,id);
            ps.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(null,ps,connection);
        }
    }

    //lbf

    //新增
    public void add(HrUsers condition) {

        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql="INSERT INTO hrusers (id,username,password) VALUES (?,?,?) ";
        try {
            stmt=conn.prepareStatement(sql);
            stmt.setLong(1,condition.getId());
            stmt.setString(2,condition.getUsername());
            stmt.setString(3,condition.getPassword());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtil.release(rs,stmt,conn);
    }
    //修改
    public void update(HrUsers condition) {
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "UPDATE hrusers SET username=?,password=? WHERE id="+condition.getId();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,condition.getUsername());
            stmt.setString(2,condition.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
