package com.neusoft.ehr.dao;

import com.neusoft.cyt.jdbcUtil;
import com.neusoft.ehr.entity.HrTalents;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */
public class TalentDao{

    //查询总记录数
    public int queryCount(){
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        jdbcUtil.getConnection();
        String sql ="select COUNT(*) from hrtalents where del_flag=0";

        int count = 0;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()){
                count =rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtil.release(rs,stmt,conn);
        return  count;
    }
    //
    public int queryCount(String query){
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        jdbcUtil.getConnection();                           //del_flag=0 and
        String sql ="select COUNT(*)from hrtalents WHERE  del_flag=0 and concat(id,ifnull(name,''),ifnull(t_code,''),sexual,ifnull(sexual,''),ifnull(employ_date,''),ifnull(unemploy_date,''))like '%"+query+"%'";

        int count = 0;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()){
                count =rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcUtil.release(rs,stmt,conn);
        return  count;
    }

    //按条件查询以及分页
    public  List<HrTalents> queryAll(int pages ,int limit,String query){
        List<HrTalents> list = new ArrayList<HrTalents>();
        HrTalents hrTalents = new HrTalents();
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        jdbcUtil.getConnection();
        String sql="select * from hrtalents WHERE  del_flag=0 and concat(id,ifnull(name,''),ifnull(t_code,''),sexual,ifnull(sexual,''),ifnull(employ_date,''),ifnull(unemploy_date,''))like '%"+query+"%' order by id limit "+(pages-1)*limit+" , "+limit;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                hrTalents=rs2Talents(rs);
                list.add(hrTalents);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    //分页查询所有
    public List<HrTalents> queryAll(int pages , int limit) {

        List<HrTalents> list = new ArrayList<HrTalents>();
        HrTalents hrTalents = new HrTalents();

        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        jdbcUtil.getConnection();
        String sql="select * from user WHERE  del_flag=0  order by id limit "+(pages-1)*limit+" , "+limit;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                hrTalents=rs2Talents(rs);
                list.add(hrTalents);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    //新增
    public void add(HrTalents hrTalents) {

        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql="INSERT INTO HrTalents (id,name,t_code,sexual,employ_date,unemploy_date) VALUES (?,?,?,?,?,?) ";
        try {
            stmt=conn.prepareStatement(sql);
            stmt.setLong(1,hrTalents.getId());
            stmt.setString(2,hrTalents.getName());
            stmt.setString(3,hrTalents.gettCode());
            stmt.setInt(4,hrTalents.getSexual());
            //将java.util.Date 转换为 java.sql.Date
            stmt.setDate(5, new java.sql.Date(hrTalents.getEmployDate().getTime()));
            stmt.setDate(6,  new java.sql.Date(hrTalents.getUnemployDate().getTime()));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }
    }

    public void add2(HrTalents hrTalents) {

        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql="INSERT INTO HrTalents (id,name,t_code,sexual) VALUES (?,?,?,?) ";
        try {
            stmt=conn.prepareStatement(sql);
            stmt.setLong(1,hrTalents.getId());
            stmt.setString(2,hrTalents.getName());
            stmt.setString(3,hrTalents.gettCode());
            stmt.setInt(4,hrTalents.getSexual());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }
    }


    //rs对象存取数据
    public HrTalents rs2Talents(ResultSet rs) throws SQLException {
        HrTalents hrTalents = new HrTalents();
        hrTalents.setId(rs.getLong("id"));
        hrTalents.setName(rs.getString("name"));
        hrTalents.settCode(rs.getString("t_code"));
        hrTalents.setSexual(rs.getInt("Sexual"));
        hrTalents.setEmployDate(rs.getDate("employ_date"));
        hrTalents.setUnemployDate(rs.getDate("unemploy_date"));
        hrTalents.setDelFlag(rs.getString("del_flag"));
        return hrTalents;
    }
    //更新数据
    public void update(HrTalents hrTalents) {
        Connection conn= jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql=null;
        if(hrTalents.getUnemployDate()==null&&hrTalents.getEmployDate()!=null){
            sql="UPDATE hrtalents SET name=? , sexual=?  ,employ_date=? WHERE  id="+hrTalents.getId();
        }
        if(hrTalents.getUnemployDate()!=null&&hrTalents.getEmployDate()==null){
            sql="UPDATE hrtalents SET name=? , sexual=?  ,unemploy_date=? WHERE  id="+hrTalents.getId();
        }
        if(hrTalents.getUnemployDate()!=null&&hrTalents.getEmployDate()!=null){
            sql="UPDATE hrtalents SET name=? , sexual=?  ,unemploy_date=? , employ_date=? WHERE  id="+hrTalents.getId();
        }
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,hrTalents.getName());
            stmt.setInt(2,hrTalents.getSexual());
            //将java.util.Date 转换为 java.sql.Date
            if(hrTalents.getUnemployDate()==null&&hrTalents.getEmployDate()!=null){
                stmt.setDate(3, new java.sql.Date(hrTalents.getEmployDate().getTime()));
            }
            if(hrTalents.getUnemployDate()!=null&&hrTalents.getEmployDate()==null){
                stmt.setDate(3,  new java.sql.Date(hrTalents.getUnemployDate().getTime()));
            }
            if(hrTalents.getUnemployDate()!=null&&hrTalents.getEmployDate()!=null){
                stmt.setDate(3,  new java.sql.Date(hrTalents.getUnemployDate().getTime()));
                stmt.setDate(4, new java.sql.Date(hrTalents.getEmployDate().getTime()));
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }
    }

}