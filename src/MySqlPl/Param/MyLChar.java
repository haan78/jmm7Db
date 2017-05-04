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
public class MyLChar extends MyParam{
    private String value;
    private boolean noEmpty = true;    

    public MyLChar(String value ) {        
        setValue(value);
    }

    public MyLChar(String value,String name) {
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
        return java.sql.Types.LONGNVARCHAR;
    }
    
    @Override
    public Object getParamValue() {
        if ( value == null ) return null; else
        if ( (noEmpty) && (value.isEmpty()) ) return null; else
        return value;
    }

    @Override
    public void setObjectValue(Object val) {
        setValue( (String)val );
    }
    
    @Override
    public Object getObjectValue() {
        return value;
    }
}
