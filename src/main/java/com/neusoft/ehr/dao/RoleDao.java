package com.neusoft.ehr.dao;

import com.neusoft.cyt.jdbcUtil;
import com.neusoft.ehr.entity.HrMenus;
import com.neusoft.ehr.entity.HrRoles;
import com.neusoft.ehr.entity.HrUsers;
import com.neusoft.ehr.entity.HrUsersRoles;
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
public class RoleDao implements ImpDaoFac{

    
    
    //查询角色
    public List<HrUsersRoles> queryRoles() {
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<HrUsersRoles> list = new ArrayList<HrUsersRoles>();
        try {
            stmt = conn.prepareStatement("select hrroles.id,hrroles.role_name,hrusersroles.user_id from hrroles left JOIN hrusersroles ON hrroles.id=hrusersroles.role_id");
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



// hrroles.id,hrroles.role_name,hrusersroles.user_id

    public HrUsersRoles rs2UsersRoles(ResultSet rs) throws SQLException {
        HrUsersRoles hrUsersRoles = new HrUsersRoles();
        HrUsers  hrUsers = new HrUsers();
        HrRoles hrRoles = new HrRoles();

        hrRoles.setId(rs.getLong("hrroles.id"));
        hrRoles.setRoleName(rs.getString("hrroles.role_name"));
        hrUsers.setId(rs.getLong("hrusersroles.user_id"));
        hrUsersRoles.setUserHrUsers(hrUsers);
        hrUsersRoles.setRoleHrRoles(hrRoles);

        return hrUsersRoles;
    }

    //新增
    public void add(HrRoles condition) {

        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "INSERT INTO hrroles (id,role_name) VALUES (?,?) ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, condition.getId());
            stmt.setString(2, condition.getRoleName());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcUtil.release(rs, stmt, conn);
        }

    }
    //修改
    public void update(HrRoles condition) {
        Connection conn = jdbcUtil.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "UPDATE hrroles SET role_name=? WHERE id=" + condition.getId();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, condition.getRoleName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.release(rs, stmt, conn);
        }

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

    public List<Object> queryAll() {
        return null;
    }

    public boolean deleteMenu(Long id) throws SQLException {
        return false;
    }

    public void add(String url, String menusname) throws SQLException {

    }
}
