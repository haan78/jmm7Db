/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlProcedure;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author BARIS
 */
public class MyTimestampParam extends MyProcedureParam {

    private Date value;
    
    public MyTimestampParam(Date value ) {
        setValue(value);
    }

    public MyTimestampParam(Date value,String name) {
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
    
}
