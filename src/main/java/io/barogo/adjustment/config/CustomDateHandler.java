package io.barogo.adjustment.config;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class CustomDateHandler extends BaseTypeHandler<ZonedDateTime> {
  
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, ZonedDateTime parameter, JdbcType jdbcType) throws SQLException {
    ps.setTimestamp(i
        , Timestamp.from(parameter.toInstant())
        , GregorianCalendar.from(parameter));
  }

  @Override
  public ZonedDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
    Timestamp sqlTimestamp = rs.getTimestamp(columnName);
    if (sqlTimestamp != null) {
      return ZonedDateTime.ofInstant(sqlTimestamp.toInstant(), ZoneId.of("Asia/Seoul"));
    }
    return null;
  }

  @Override
  public ZonedDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    Timestamp ts = rs.getTimestamp(columnIndex, Calendar.getInstance());
    if(ts != null) {
      return ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of("Asia/Seoul"));
    }
    return null;
  }

  @Override
  public ZonedDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    Timestamp ts = cs.getTimestamp(columnIndex, Calendar.getInstance());
    if(ts != null) {
      return ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of("Asia/Seoul"));
    }
    return null;
  }
}
