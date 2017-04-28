/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlPl.Param;

/**
 *
 * @author BARIS
 */
public class MyFloat extends MyParam {
    
    private Double value = null;
    
    public MyFloat(Double value ) {
        setValue(value);
    }

    public MyFloat(Double value,String name) {        
        setValue(value);
        setName(name);        
    }

    public final void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int getSQLType() {
        return java.sql.Types.FLOAT;
    }
    
    @Override
    public Object getParamValue() {
        return value;
    }
    
}
