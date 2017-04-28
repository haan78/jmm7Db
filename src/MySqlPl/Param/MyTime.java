/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlPl.Param;

import java.sql.Time;

/**
 *
 * @author BARIS
 */
public class MyTime extends MyParam {
    
    private Time value;

    public MyTime(Time value ) {
        setValue(value);
    }

    public MyTime(Time value,String name) {
        setValue(value);
        setName(name);        
    }
    
    public final void setValue(Time value) {
        this.value = value;
    }

    @Override
    public int getSQLType() {
        return java.sql.Types.TIME;
    }

    @Override
    public Object getParamValue() {
        return value;
    }
    
}
