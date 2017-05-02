/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlPl;

import MySqlPl.Result.MySqlQueryResult;
import MySqlPl.Result.MySqlProcedureResult;
import MySqlPl.Result.MySqlQueryResultSet;
import MySqlPl.Result.MySqlRowResult;
import MySqlPl.Param.MyParam;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author BARIS
 */
public class MySqlPl {
    
    private final ArrayList<MyParam> params;
    private final Connection connection;
    private String lastSQL;
    
    public MySqlPl param(MyParam param) {
        params.add(param);
        return this;
    }

    public MySqlPl(Connection connection) {
        params = new ArrayList<>();
        this.connection = connection;        
    }

    public String getLastSQL() {
        return lastSQL;
    }        
    
    public MySqlProcedureResult call(String procedure) throws SQLException {
        
        //return execute( generateProcedureSql(procedure) );
        String args = "";
        int itarator = 0;
        for ( int i=0; i<params.size(); i++ ) {
            if (i>0) args +=",";
            args+="?";
            if ( params.get(i).getName()!=null ) {
                itarator++;
            }
        }
        CallableStatement cstmt = connection.prepareCall("{ CALL "+procedure+"("+args+") }");
        int[] outids = new int[itarator];
        itarator = 0;
         
        for ( int i=0; i<params.size(); i++ ) {
            if ( params.get(i).getName() == null ) {
                //IN
                if ( params.get(i).getParamValue() != null ) {
                    cstmt.setObject(i+1, params.get(i).getParamValue() , params.get(i).getSQLType() );
                } else {
                    cstmt.setNull(i+1, params.get(i).getSQLType());
                }                
            } else {
                //OUT
                outids[itarator] = i;
                itarator++;
                cstmt.registerOutParameter(i+1, params.get(i).getSQLType() );
                if ( params.get(i).getParamValue() != null ) {
                    cstmt.setObject(i+1, params.get(i).getParamValue() , params.get(i).getSQLType() );
                } else {
                    cstmt.setNull(i+1, params.get(i).getSQLType());
                }                
            }           
        }
        
        //List<List<Map<String,Object>>> data = new ArrayList<>();
        MySqlQueryResultSet data = new MySqlQueryResultSet();
        
        boolean hasMore = cstmt.execute();        
        
        //HashMap<String,Object> outs = new HashMap<>();
        MySqlRowResult outs = new MySqlRowResult();
        for (int i=0; i<outids.length; i++) {
            outs.put( params.get( outids[i] ).getName() , cstmt.getObject( outids[i]+1 ) ); // +1 in nedeni java parametreleri birden balayarak sayiyior
        }
        
        while( hasMore ) {
            ResultSet rs = cstmt.getResultSet();
            if ( rs!=null ) {
                ResultSetMetaData md = rs.getMetaData();
                
                MySqlQueryResult q = new MySqlQueryResult();
                
                while(rs.next()) {                    
                    MySqlRowResult row = new MySqlRowResult();
                    for (int k = 1; k<md.getColumnCount()+1; k++ ) {                
                        row.put(md.getColumnLabel(k),rs.getObject(k));
                    }
                    q.add(row);
                }            
                data.add(q);
            }
            hasMore = cstmt.getMoreResults();
        }
        params.clear();        
        return new MySqlProcedureResult(data,outs);
    }
    
    public Object function(String functionName) throws SQLException {

        String str = "SELECT "+functionName+"(";
        for (int i=0; i<params.size(); i++) {
            if (i>0) str+=",";
            str+="?";
        }
        str+=")";
        
        PreparedStatement pstmt = connection.prepareStatement(str);
        
        for (int i=0; i<params.size(); i++) {
            pstmt.setObject(i+1, params.get(i).getParamValue(), params.get(i).getSQLType() );
        }
        
        ResultSet rs = pstmt.executeQuery();
        
        params.clear();
        if ( (rs!=null) && (rs.next()) ) {            
            return rs.getObject(1);
        } else {
            return null;
        }    
        
    }
    
    public MySqlQueryResult query(String sql) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        
        for (int i=0; i<params.size(); i++) {
            pstmt.setObject(i+1, params.get(i).getParamValue(), params.get(i).getSQLType() );
        }
        
        ResultSet rs = pstmt.executeQuery();
        
        MySqlQueryResult q = new MySqlQueryResult();
        
        if ( rs!=null ) {
            
            ResultSetMetaData md = rs.getMetaData();           
            
            while ( rs.next() ) {
                MySqlRowResult row = new MySqlRowResult();
                for (int k = 1; k<md.getColumnCount()+1; k++ ) {                
                    row.put(md.getColumnLabel(k),rs.getObject(k));
                }
                q.add(row);
            }           
            
        }
        
        params.clear();
        
        return q;
        
    }
    
    public Object result(String sql) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        
        for (int i=0; i<params.size(); i++) {
            pstmt.setObject(i+1, params.get(i).getParamValue(), params.get(i).getSQLType() );
        }
        
        ResultSet rs = pstmt.executeQuery();
        
        if ( (rs!=null) && (rs.next()) ) {            
            return rs.getObject(1);
        } else {
            return null;
        }
        
    }
    
    public int execute(String sql) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        for (int i=0; i<params.size(); i++) {
            pstmt.setObject(i+1, params.get(i).getParamValue(), params.get(i).getSQLType() );
        }
        
        params.clear();
        
        return pstmt.executeUpdate();
        
    }
    
}
