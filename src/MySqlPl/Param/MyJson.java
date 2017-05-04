/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlPl.Param;

import com.google.gson.*;

/**
 *
 * @author BARIS
 */
public class MyJson extends MyParam {

    private Object value = null;
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public void setValue(Object value) {
        this.value = value;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }   
     
    @Override
    public int getSQLType() {
        return java.sql.Types.LONGNVARCHAR;
    }

    @Override
    public Object getParamValue() {
        Gson g;
        g = new com.google.gson.GsonBuilder().setDateFormat(dateFormat).create();
        return g.toJson(value);
    }

    @Override
    public void setObjectValue(Object val) {
        if ( value !=null ) {
            Gson g;
            g = new com.google.gson.GsonBuilder().setDateFormat(dateFormat).create();
            setValue( g.fromJson((String)val, value.getClass()) );
        } else {
            setValue(val);
        }        
    }
    
    @Override
    public Object getObjectValue() {
        return value;
    }
    
}
