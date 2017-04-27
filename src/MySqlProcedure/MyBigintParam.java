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
public class MyBigintParam extends MyProcedureParam {

    private Long value;
    
    public MyBigintParam(Long value ) {
        setValue(value);
    }

    public MyBigintParam(Long value,String name) {
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
    
}
