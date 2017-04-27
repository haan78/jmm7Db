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
public class MyChar extends MyParam {

    private String value;
    private boolean noEmpty = true;    

    public MyChar(String value ) {        
        setValue(value);
    }

    public MyChar(String value,String name) {
        setValue(value);
        setName(name);        
    }

    public final void setValue(String value) {
        this.value = value;
    }

    public void setNoEmpty(boolean noEmpty) {
        this.noEmpty = noEmpty;
    } 

    @Override
    public int getSQLType() {
        return java.sql.Types.CHAR;
    }
    
    @Override
    public Object getParamValue() {
        if ( value == null ) return null; else
        if ( (noEmpty) && (value.isEmpty()) ) return null; else
        return value;
    }
    
}
