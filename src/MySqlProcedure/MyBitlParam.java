/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlProcedure;

/**
 *
 * @author BARIS
 */
public class MyBitlParam extends MyProcedureParam {

    private Boolean value;

    public MyBitlParam(Boolean value ) {
        setValue(value);
    }

    public MyBitlParam(Boolean value,String name) {
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
