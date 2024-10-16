//package com.juaracoding.dao;
//
//import com.juaracoding.core.IDao;
//import com.juaracoding.model.Contoh;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.*;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//@Repository
//public class ContohDaoImplPostgresql implements IDao<Contoh> {

//@Autowired
//private JdbcTemplate jdbcTemplate;
//
//@Override
//public ResponseEntity<Object> save(Contoh contoh, HttpServletRequest request) {
//    int intResult = 0;
//    try
//    {
//        intResult = jdbcTemplate.update("call sp_add_mst_contoh(?,?,?,?,?,?)", contoh.getContohInt(),
//                contoh.getContohDouble(),contoh.getContohString(),contoh.getContohFloat(),contoh.getContohBoolean(),
//                new java.sql.Date(((Date) contoh.getContohDate()).getTime()));
////            if(intResult != 1){
////                throw new Exception();
////            }
//    }
//    catch (Exception e)
//    {
//        return new ResponseEntity<Object>("DATA GAGAL DISIMPAN "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    return new ResponseEntity<Object>(new HashMap<>(),HttpStatus.CREATED);
//}
//
//@Override
//public ResponseEntity<Object> update(Long id, Contoh contoh, HttpServletRequest request) {
//    try
//    {
//        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
//        jdbcTemplate.call(new CallableStatementCreator() {
//            @Override
//            public CallableStatement createCallableStatement(Connection con) throws SQLException {
//
//                CallableStatement cs = con.prepareCall("CALL sp_update_mst_contoh (?,?,?,?,?,?,?)");
//                cs.setInt(1, contoh.getContohInt());
//                cs.setDouble(2, contoh.getContohDouble());
//                cs.setString(3, contoh.getContohString());
//                cs.setFloat(4, contoh.getContohFloat());
//                cs.setBoolean(5, contoh.getContohBoolean());
//                cs.setDate(6,new java.sql.Date(((Date) contoh.getContohDate()).getTime()));
//                cs.setLong(7,id);
//                return cs;
//            }
//        }, parameters);
//    }
//    catch (Exception e)
//    {
//        return new ResponseEntity<Object>("DATA GAGAL DISIMPAN "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    return new ResponseEntity<Object>(new HashMap<>(),HttpStatus.CREATED);
//}
//
//@Override
//public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
//    try
//    {
//        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
//        jdbcTemplate.call(new CallableStatementCreator() {
//            @Override
//            public CallableStatement createCallableStatement(Connection con) throws SQLException {
//                CallableStatement cs = con.prepareCall("CALL sp_delete_mst_contoh (?)");
//                cs.setLong(1,id);
//                return cs;
//            }
//        }, parameters);
//    }
//    catch (Exception e)
//    {
//        return new ResponseEntity<Object>("DATA GAGAL DISIMPAN "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    return new ResponseEntity<Object>(new HashMap<>(),HttpStatus.CREATED);
//}
//
//@Override
//public ResponseEntity<Object> findAll(int page,
//                                      int size,
//                                      String sort,
//                                      String sortBy,
//                                      HttpServletRequest request) {
//    Object object[] = new Object[4];
//    object[0] = page;
//    object[1] = size;
//    object[2] = sort;
//    object[3] = sortBy;
//
//    List<Contoh> contohList  = jdbcTemplate.query("SELECT * FROM sp_find_all(?,?,?,?)", new RowMapper<Contoh>() {
//        @Override
//        public Contoh mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Contoh contoh = new Contoh();
//            contoh.setId(rs.getLong("id"));
//            contoh.setContohInt(rs.getInt("contoh_int"));
//            contoh.setContohDouble(rs.getDouble("contoh_double"));
//            contoh.setContohFloat(rs.getFloat("contoh_float"));
//            contoh.setContohString(rs.getString("contoh_string"));
//            contoh.setContohBoolean(rs.getBoolean("contoh_boolean"));
//            contoh.setContohDate(rs.getDate("contoh_date"));
//            return contoh;
//        }
//    },object);
//
//    if(contohList.isEmpty()){
//        return new ResponseEntity<Object>("Data Tidak Ditemukan",HttpStatus.BAD_REQUEST);
//    }
//    return new ResponseEntity<Object>(contohList,HttpStatus.OK);
//}
//
//@Override
//public ResponseEntity<Object> findByParam(int page, int size ,
//                                          String sort,String sortBy,String column,
//                                          String value, HttpServletRequest request) {
//    Object object[] = new Object[6];
//    object[0] = page;
//    object[1] = size;
//    object[2] = sort;
//    object[3] = sortBy;
//    object[4] = column;
//    object[5] = value;
//
//    List<Contoh> contohList  = jdbcTemplate.query("SELECT * FROM sp_find_by_param(?,?,?,?,?,?)", new RowMapper<Contoh>() {
//        @Override
//        public Contoh mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Contoh contoh = new Contoh();
//            contoh.setId(rs.getLong("id"));
//            contoh.setContohInt(rs.getInt("contoh_int"));
//            contoh.setContohDouble(rs.getDouble("contoh_double"));
//            contoh.setContohFloat(rs.getFloat("contoh_float"));
//            contoh.setContohString(rs.getString("contoh_string"));
//            contoh.setContohBoolean(rs.getBoolean("contoh_boolean"));
//            contoh.setContohDate(rs.getDate("contoh_date"));
//            return contoh;
//        }
//    },object);
//
//    if(contohList.isEmpty()){
//        return new ResponseEntity<Object>("Data Tidak Ditemukan",HttpStatus.BAD_REQUEST);
//    }
//    return new ResponseEntity<Object>(contohList,HttpStatus.OK);
//}
//
//
//@Override
//public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
//    Object [] objects = new Object[1];
//    objects[0] = id;
//    Contoh contoh  = jdbcTemplate.queryForObject("SELECT * FROM sp_find_by_id(?)", new RowMapper<Contoh>() {
//        @Override
//        public Contoh mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Contoh contoh = new Contoh();
//            contoh.setId(rs.getLong("id"));
//            contoh.setContohInt(rs.getInt("contoh_int"));
//            contoh.setContohDouble(rs.getDouble("contoh_double"));
//            contoh.setContohFloat(rs.getFloat("contoh_float"));
//            contoh.setContohString(rs.getString("contoh_string"));
//            contoh.setContohBoolean(rs.getBoolean("contoh_boolean"));
//            contoh.setContohDate(rs.getDate("contoh_date"));
//            return contoh;
//        }
//    },objects);
//    if(contoh == null){
//        return new ResponseEntity<Object>("Data Tidak Ditemukan",HttpStatus.BAD_REQUEST);
//    }
//    return new ResponseEntity<Object>(contoh,HttpStatus.OK);
//}
//}