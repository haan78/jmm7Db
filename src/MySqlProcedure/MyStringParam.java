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
public class MyStringParam extends MyProcedureParam {

    private String value;
    private boolean noEmpty = true;    

    public MyStringParam(String value ) {        
        setValue(value);
    }

    public MyStringParam(String value,String name) {
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
        return java.sql.Types.VARCHAR;
    }
    
    @Override
    public Object getParamValue() {
        if ( value == null ) return null; else
        if ( (noEmpty) && (value.isEmpty()) ) return null; else
        return value;
    }
}
