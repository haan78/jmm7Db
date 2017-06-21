/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PgSqlFunction;

/**
 *
 * @author BARIS
 */
public class PgFunctionParam {
    private String type;    
    private Object value;
    private String dateFormat = "yyyy-MM-dd";
    private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    
    
    public PgFunctionParam(Object value, String type) {
        setValue(value, type);
    }
    
    public PgFunctionParam(Object value) {
        setValue(value);
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }    

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }    
    
    public final void setValue(Object value, String type) {
        this.type = type;
        this.value = value;
    }
    
    public final void setValue(Object value) {
        this.type = null;
        this.value = value;
    }
    
    public String pgValue() throws IllegalArgumentException, IllegalAccessException {
        PgRenderObject ro;
        ro = new PgRenderObject(value, dateTimeFormat, dateFormat);
        if ( type != null ) {
            return ""+ro.toPgString()+"::"+type;    
        } else {
            return ""+ro.toPgString()+"";
        }        
    }
    
}
