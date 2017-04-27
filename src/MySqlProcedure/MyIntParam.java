/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlProcedure;

/**
 *
 * @author BARIS
 */
public class MyIntParam extends MyProcedureParam {
    
    private Integer value;

    public MyIntParam(Integer value ) {
        setValue(value);
    }

    public MyIntParam(Integer value,String name) {
        setValue(value);
        setName(name);        
    }

    public final void setValue(Integer value) {
        this.value = value;
    }    

    @Override
    public int getSQLType() {
        return java.sql.Types.INTEGER;
    }
    
    @Override
    public Object getParamValue() {
        return value;
    }
}
