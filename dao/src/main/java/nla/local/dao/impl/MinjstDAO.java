package nla.local.dao.impl;

import nla.local.dao.IMinjstDAO;
import nla.local.pojos.subjects.JurMINJST;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by beresnev on 07.08.2015.
 */
@Repository
public class MinjstDAO extends  BaseDao implements IMinjstDAO{

    @Autowired
    private BasicDataSource dataSourceMVDUST;

    public List<JurMINJST> getDatabyName(String name)
    {

        List<JurMINJST> jurMINUST = new ArrayList<JurMINJST>();

        Connection conn = null;


        try {


            String sql =  "SELECT DISTINCT TAB.* FROM TABLE (CAST (JPR.Q2('%" +name+ "%') AS T_Q2_ARRAY)) TAB";  //"SELECT *  FROM TABLE (CAST (jpr.q2( '%" + name + "%' ) AS t_q2_array))";

            conn = dataSourceMVDUST.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs =  ps.executeQuery();

            jurMINUST = convert_result_set(rs);

            ps.close();

        } catch (SQLException e) { throw new RuntimeException(e); } finally {
            if (conn != null) { try {conn.close(); } catch (SQLException e) {} }
        }

        return jurMINUST;
    }

    public JurMINJST getDatabyNumber(Integer unp)
    {

        List<JurMINJST> jurMINUST =new ArrayList<JurMINJST>() ;


        Connection conn = null;


        try {


            String sql = "SELECT *  FROM TABLE (CAST (JPR.Q1( '" + unp + "' ) AS T_Q1_ARRAY))";

            conn = dataSourceMVDUST.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs =  ps.executeQuery();

            jurMINUST = convert_result_set(rs);

            ps.close();

        } catch (SQLException e) { throw new RuntimeException(e); } finally {
            if (conn != null) { try {conn.close(); } catch (SQLException e) {} }
        }

        return jurMINUST.get(0);
    }

    private List<JurMINJST> convert_result_set(ResultSet rs) throws SQLException {

        List<JurMINJST> ret_val = new ArrayList<JurMINJST>();


        while(rs.next()){

            JurMINJST newur = new JurMINJST();

            //Retrieve by column name
            int    ngnr  = rs.getInt("NGRN");

            newur.setUNP(ngnr);

            String vnaim = rs.getString("VNAIM");

            newur.setVNAIM(vnaim);

            String vn = rs.getString("VN");

            newur.setVN(vn);

            String voblast = rs.getString("VOBLAST");

            newur.setVoblast(voblast);

            String vraion = rs.getString("VRAION");

            newur.setVraion(vraion);

            String vntnpk = rs.getString("VNTNPK");

            newur.setVntnpk(vntnpk);

            String vnp = rs.getString("VNP");

            newur.setVnp(vnp);

            String vntulk = rs.getString("VNTULK");

            newur.setVntulk(vntulk);

            String vulitsa = rs.getString("VULITSA");

            newur.setVulitsa(vulitsa);

            String vdom = rs.getString("VDOM");

            newur.setVdom(vdom);

            String vkorp = rs.getString("VKORP");

            newur.setVkorp(vkorp);

            Date dcrt = rs.getDate("DCRT");

            newur.setRegDate(dcrt);

            String vorgcrt = rs.getString("VORGCRT");

            newur.setRegName(vorgcrt);

            Date  diskl = rs.getDate("DISKL");

            newur.setUnRegDate(diskl);

            String vorgiskl = rs.getString("VORGISKL");

            newur.setUnRegName(vorgiskl);

            Integer nkopf = rs.getInt("NKOPF");

            newur.setNkOpf(nkopf);

            ret_val.add(newur);

        }

        return ret_val;
    }


    public JurMINJST getDatabyUnp(Integer unp)
    {

        Connection conn = null;

        try {

            String sql = "SELECT *  FROM TABLE (CAST (jpr.q2(" + unp + ") AS t_q1_array))";

            conn = dataSourceMVDUST.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs =  ps.executeQuery();

            ps.close();

        } catch (SQLException e) { throw new RuntimeException(e); } finally {
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} } }

        return null;
    }



}
