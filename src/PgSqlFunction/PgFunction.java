/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PgSqlFunction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;


/**
 *
 * @author BARIS
 */
public final class PgFunction {
    private final ArrayList<PgFunctionParam> params; 
    private final PgResultParser parser = new PgResultParser();
    private String defaultDateFormat = "yyyy-MM-dd";
    private String defaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    
    private String functionName;
    private Connection connection;

    public void setDefaultDateFormat(String defaultDateFormat) {
        this.defaultDateFormat = defaultDateFormat;
    }

    public void setDefaultFateTimeFormat(String defaultFateTimeFormat) {
        this.defaultDateTimeFormat = defaultFateTimeFormat;
    }
    
    public PgFunction() {        
        this.params = new ArrayList<>();
    }
    
        public PgFunction(Connection conn) {        
        this.params = new ArrayList<>();
        setConnection(conn);
    }
    
    public PgFunction(Connection conn, String functionName) {        
        this.params = new ArrayList<>();
        setConnection(conn);
        setFunctionName(functionName);
    }    
    
    public void setFunctionName(String name) {
        functionName = name;
    }
    
    public PgFunction param(Object value,String type) {
        PgFunctionParam p = new PgFunctionParam(value,type);
        p.setDateFormat(defaultDateFormat);
        p.setDateTimeFormat(defaultDateTimeFormat);        
        return addParam(p);
    }
    
    public PgFunction param(Object value) {
        PgFunctionParam p = new PgFunctionParam(value,null);
        p.setDateFormat(defaultDateFormat);
        p.setDateTimeFormat(defaultDateTimeFormat);
        return addParam(p);
    }
    
    public PgFunction addParam(PgFunctionParam p) {
        params.add(p);
        return this;
    }
    
    public void clearParams() {
        params.clear();
    }
    
    public void setConnection(Connection conn) {
        connection = conn;
    }
    
    public PgFunction addResutlType(Class T) {
        parser.addClass(T);
        return this;
    }
    
    public void clearTypes() {
        parser.clearClasses();
    }
    
    private String generateSelectSql() throws IllegalArgumentException, IllegalAccessException {
        String sql = "SELECT";
        sql += " "+functionName;
        sql += "(";
        for (int i=0; i<params.size(); i++) {
            if ( i>0 ) sql+=",";
            sql += params.get(i).pgValue();
        }
        sql += ")";
        return  sql;
    }
    
    private String generatePerformSql() throws IllegalArgumentException, IllegalAccessException {
        String sql = "PERFORM";
        sql += " "+functionName;
        sql += "(";
        for (int i=0; i<params.size(); i++) {
            if ( i>0 ) sql+=",";
            sql += params.get(i).pgValue();
        }
        sql += ")";
        return  sql;
    }
    
    public void execute() throws SQLException, IllegalArgumentException, IllegalAccessException {
        Statement stmt = connection.createStatement();
        stmt.execute( generatePerformSql() );
    }
    
    public Object result() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {        
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery( generateSelectSql() );
        if (rs.next()) {
            return parser.parse( rs.getString(1) );
        } else {
            return null;
        }        
    }
    
    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> query( ) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {        
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery( generateSelectSql() );
        ArrayList<T> list = new ArrayList<>();

        while(rs.next()) {
            Object o = parser.parse( rs.getString(1) );
            list.add((T) o);
        }

        return list;
        
    }
    
}

