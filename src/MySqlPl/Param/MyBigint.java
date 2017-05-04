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
public class MyBigint extends MyParam {

    private Long value;
    
    public MyBigint(Long value ) {
        setValue(value);
    }

    public MyBigint(Long value,String name) {
        setValue(value);
        setName(name);        
    }

    public final void setValue(Long value) {
        this.value = value;
    }
    
    @Override
    public int getSQLType() {
        return java.sql.Types.BIGINT;
    }

    @Override
    public Object getParamValue() {
        return value;
    }

    @Override
    public void setObjectValue(Object val) {
        setValue( (Long)val );
    }

    @Override
    public Object getObjectValue() {
        return value;
    }
    
}
