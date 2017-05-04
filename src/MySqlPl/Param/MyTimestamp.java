/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlPl.Param;


import java.util.Date;

/**
 *
 * @author BARIS
 */
public class MyTimestamp extends MyParam {

    private Date value;
    
    public MyTimestamp(Date value ) {
        setValue(value);
    }

    public MyTimestamp(Date value,String name) {
        setValue(value);
        setName(name);        
    }

    public final void setValue(Date value) {
        this.value = value;
    }

    @Override
    public int getSQLType() {
        return java.sql.Types.TIMESTAMP;
    }
    
    @Override
    public Object getParamValue() {
        return value;
    }

    @Override
    public void setObjectValue(Object val) {
        setValue( (Date)val );
    }
    
    @Override
    public Object getObjectValue() {
        return value;
    }
    
}
