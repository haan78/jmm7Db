/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlPl.Param;

import java.math.BigDecimal;

/**
 *
 * @author BARIS
 */
public class MyDecimal extends MyParam {
    
    private BigDecimal value = null;
    
    public MyDecimal(BigDecimal value ) {
        setValue(value);
    }

    public MyDecimal(BigDecimal value,String name) {        
        setValue(value);
        setName(name);        
    }

    public final void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public int getSQLType() {
        return java.sql.Types.DECIMAL;
    }
    
    @Override
    public Object getParamValue() {
        return value;
    }

    @Override
    public void setObjectValue(Object val) {
        setValue( (BigDecimal)val );
    }
    
    @Override
    public Object getObjectValue() {
        return value;
    }

    
    
}