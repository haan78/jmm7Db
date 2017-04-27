/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlProcedure;

import MySqlProcedure.Param.MyParam;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author BARIS
 */
public class MyProcedure {
    
    private final ArrayList<MyParam> params;
    private final Connection connection;
    private String lastSQL;
    
    public MyProcedure param(MyParam param) {
        params.add(param);
        return this;
    }

    public MyProcedure(Connection connection) {
        params = new ArrayList<>();
        this.connection = connection;        
    }

    public String getLastSQL() {
        return lastSQL;
    }    

    
    public MyProcedureResult call(String procedure) throws SQLException {
        
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
        
        List<List<Map<String,Object>>> data = new ArrayList<>();
        
        boolean hasMore = cstmt.execute();        
        
        HashMap<String,Object> outs = new HashMap<>();
        for (int i=0; i<outids.length; i++) {
            outs.put( params.get( outids[i] ).getName() , cstmt.getObject( outids[i]+1 ) ); // +1 in nedeni java parametreleri birden balayarak sayiyior
        }
        
        while( hasMore ) {
            ResultSet rs = cstmt.getResultSet();
            if ( rs!=null ) {
                ResultSetMetaData md = rs.getMetaData();
                
                ArrayList<Map<String,Object>> q = new ArrayList<>();
                while(rs.next()) {
                    HashMap<String,Object> row = new HashMap<>();
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
        return new MyProcedureResult(data,outs);
    }
    
    public Object function(String functionName) throws SQLException {
        Object result = null;
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
        
        if ( rs!=null ) {
            result = rs.getObject(1);
        }
        params.clear();        
        return result;
    }    
    
}
