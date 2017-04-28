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
public class MyLBinary extends MyParam {
    private byte[] value;
    
    public MyLBinary(byte[] value ) {
        setValue(value);
    }

    public MyLBinary(byte[] value,String name) {
        setValue(value);
        setName(name);        
    }

    public final void setValue(byte[] value) {
        this.value = value;
    }

    @Override
    public int getSQLType() {
        return java.sql.Types.LONGVARBINARY;
    }

    @Override
    public Object getParamValue() {
        return value;        
    }  
}
