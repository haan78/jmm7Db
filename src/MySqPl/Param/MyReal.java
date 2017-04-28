/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqPl.Param;

/**
 *
 * @author BARIS
 */
public class MyReal extends MyParam {
    
    private Float value = null;
    
    public MyReal(Float value ) {
        setValue(value);
    }

    public MyReal(Float value,String name) {        
        setValue(value);
        setName(name);        
    }

    public final void setValue(Float value) {
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
