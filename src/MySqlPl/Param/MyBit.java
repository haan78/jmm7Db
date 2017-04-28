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
public class MyBit extends MyParam {

    private Boolean value;

    public MyBit(Boolean value ) {
        setValue(value);
    }

    public MyBit(Boolean value,String name) {
        setValue(value);
        setName(name);        
    }

    public final void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public int getSQLType() {
        return java.sql.Types.BIT;
    }

    @Override
    public Object getParamValue() {
        if ( value == null ) return null;
        else if ( value==true ) return 1;
        else return 0;
        
    }
    
}
