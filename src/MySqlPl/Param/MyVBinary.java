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
public class MyVBinary extends MyParam {
    private byte[] value;
    
    public MyVBinary(byte[] value ) {
        setValue(value);
    }

    public MyVBinary(byte[] value,String name) {
        setValue(value);
        setName(name);        
    }

    public final void setValue(byte[] value) {
        this.value = value;
    }

    @Override
    public int getSQLType() {
        return java.sql.Types.VARBINARY;
    }

    @Override
    public Object getParamValue() {
        return value;        
    }  

    @Override
    public void setObjectValue(Object val) {
        setValue( (byte[])val );
    }
    
    @Override
    public Object getObjectValue() {
        return value;
    }
}
