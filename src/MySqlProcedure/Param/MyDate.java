/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlProcedure.Param;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author BARIS
 */
public class MyDate extends MyParam {

    private Date value;
    
    public MyDate(Date value ) {
        setValue(value);
    }

    public MyDate(Date value,String name) {
        setValue(value);
        setName(name);        
    }

    public final void setValue(Date value) {
        this.value = value;
    }

    @Override
    public int getSQLType() {
        return java.sql.Types.DATE;
    }
    
    @Override
    public Object getParamValue() {
        return value;
    }
    
}
