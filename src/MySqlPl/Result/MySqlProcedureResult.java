/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlPl.Result;

import MySqlPl.Result.MySqlQueryResultSet;
import MySqlPl.Result.MySqlRowResult;

/**
 *
 * @author BARIS
 */

public class MySqlProcedureResult {
    //public final List<List<Map<String,Object>>> queries;
    //public final Map<String,Object> outputs;
    
    public final MySqlQueryResultSet queries;
    public final MySqlRowResult outputs;

    public MySqlProcedureResult(MySqlQueryResultSet queries, MySqlRowResult outputs) {
        this.queries = queries;
        this.outputs = outputs;
    }    
}
