/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlProcedure.Param;

/**
 *
 * @author BARIS
 */
public class MyBinary extends MyParam {

    private byte[] value;
    
    public MyBinary(byte[] value ) {
        setValue(value);
    }

    public MyBinary(byte[] value,String name) {
        setValue(value);
        setName(name);        
    }

    public final void setValue(byte[] value) {
        this.value = value;
    }

    @Override
    public int getSQLType() {
        return java.sql.Types.BINARY;
    }

    @Override
    public Object getParamValue() {
        return value;        
    }    
}
