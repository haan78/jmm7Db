/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqPl;

import java.util.List;
import java.util.Map;

/**
 *
 * @author BARIS
 */

public class MySqlProcedureResult {
    public final List<List<Map<String,Object>>> queries;
    public final Map<String,Object> outputs;

    public MySqlProcedureResult(List<List<Map<String,Object>>> queries, Map<String,Object> outputs) {
        this.queries = queries;
        this.outputs = outputs;
    }    
}
