package com.gmcc.dao.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleTypes;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gmcc.dao.BillSnDAO;
import com.gmcc.dao.hibernate.base.BaseDao;
import com.gmcc.model.BillSn;

public class BillSnDAOHibernate extends BaseDao<BillSn, Long> implements
		BillSnDAO {

	public synchronized String getBillSn(final String snType) {
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		// String sql="select SN_TYPE || "+
		// " decode(need_date, 1, '-' || TO_CHAR(SYSDATE, 'YYYYMMDD') || '-', '') || "+
		// " LPAD(SN_VALUE + 1, nvl(SN_LENGTH,5), '0') snvalue "+
		// " FROM TB_APP_BILL_SN t where t.SN_TYPE='"+snType+"' for update";
		// String snValue=(String)
		// jdbcTemplate.queryForObject(sql,String.class);
		// System.out.println("snValue:"+snValue);
		// String
		// updSql="update TB_APP_BILL_SN t set t.sn_value=t.sn_value+1 where t.sn_type='"+snType+"'";
		// jdbcTemplate.execute(updSql);
		return (String) getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cstmt = conn.prepareCall("{call ? := f_getbillsn(?) }");
				cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
				cstmt.setString(2, snType);
				cstmt.execute();
				System.out.println("snValue:"+cstmt.getString(1));
				return cstmt.getString(1);
//				ResultSet rs = (ResultSet) cstmt.getObject(1);
//				while (rs.next()) {
//					System.out.println("snValue:"+rs.getString(1));
//					
//				}
//				return null;
			}
		});
		// return snValue;
	}
}
