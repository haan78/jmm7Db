/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlProcedure;

import java.util.List;
import java.util.Map;

/**
 *
 * @author BARIS
 */

public class MyProcedureResult {
    private List<List<Map<String,Object>>> data;
    private Map<String,Object> outs;

    public MyProcedureResult(List<List<Map<String,Object>>> data, Map<String,Object> outs) {
        this.data = data;
        this.outs = outs;
    }
    
    
    public List<List<Map<String,Object>>> getData() {
        return data;
    }
    
    public Map<String,Object> getOutputs() {
        return outs;
    }
    
}
