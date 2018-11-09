package com.neusoft.ehr.dao;


import com.neusoft.cyt.jdbcUtil;
import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.entity.HrUsers;
import com.neusoft.ehr.entity.HrUsersRoles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */
public class UserRoleDao{
    public Boolean isHasRoles(long uid,long rid){
        Connection connection = jdbcUtil.getConnection();
        String sql = "select * from hrusersroles where role_id=? and user_id=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1,rid);
            stmt.setLong(2,uid);
            rs = stmt.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,connection);
        }
        return false;
    }

    public void delete(Long user_id, Long role_id) {
        Connection conn = jdbcUtil.getConnection();
        String sql = "DELETE from hrusersroles where role_id="+role_id+"  and user_id="+user_id;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }

    }

    public void add(Long user_id, Long role_id) {

        Connection conn = jdbcUtil.getConnection();
        String sql = "INSERT INTO hrusersroles (role_id,user_id) VALUES (?,?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,role_id);
            stmt.setLong(2,user_id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }

    }

    //查询用户角色权限
    public List<HrUsersRoles> queryKey(Long user_id){
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<HrUsersRoles> list = new ArrayList<HrUsersRoles>();
        try {
            stmt = conn.prepareStatement("select hrroles.id,hrroles.role_name,hrusersroles.user_id from hrroles  JOIN hrusersroles ON hrroles.id=hrusersroles.role_id AND hrusersroles.user_id="+user_id);
            rs = stmt.executeQuery();
            while(rs.next()){
                HrUsersRoles resultUser = null;
                resultUser = rs2UsersRoles(rs);
                list.add(resultUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }
        return list;
    }

    public void update(Long user_id, Long role_id) {
        Connection conn = jdbcUtil.getConnection();
        String sql = "UPDATE  hrusersroles SET role_id="+role_id+"  WHERE user_id="+user_id;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs,stmt,conn);
        }

    }


    public HrUsersRoles rs2UsersRoles(ResultSet rs) throws SQLException {
        HrUsersRoles hrUsersRoles = new HrUsersRoles();
        HrUsers hrUsers = new HrUsers();
        HrRoles hrRoles = new HrRoles();

        hrRoles.setId(rs.getLong("hrroles.id"));
        hrRoles.setRoleName(rs.getString("hrroles.role_name"));
        hrUsers.setId(rs.getLong("hrusersroles.user_id"));
        hrUsersRoles.setUserHrUsers(hrUsers);
        hrUsersRoles.setRoleHrRoles(hrRoles);

        return hrUsersRoles;
    }
}
