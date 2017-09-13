/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PgSqlFunction;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author BARIS
 */
public class PgRenderObject {
    private final Object obj;
    private final String ldf;
    private final String sdf;
    private boolean inObject;
    public PgRenderObject(Object obj,String longDateFormat,String shortDateFormat) {
        this.obj = obj;
        ldf = longDateFormat;
        sdf = shortDateFormat;
    }
    
    public Object toObject() {
        return obj;
    }
    
    @Override
    public String toString() {
        return (String)obj;
    }
    
    public int toInt() {
        return Integer.parseInt( (String)obj );
    }
    
    public float toFloat() {
        return Float.parseFloat( (String)obj );
    }
    
    public long toLong() {
        return Long.parseLong( (String)obj );
    }
    
    public short toShort() {
        return Short.parseShort( (String)obj );
    }
    
    public double toDouble() {
        return Double.parseDouble( (String)obj );
    }
    
    public char toChar() {
        return ((String)obj).charAt(0);
    }
    
    public boolean toBool() {
        return ((String)obj).equals("1");
    }
    
    private Date _toDate(Object v) throws ParseException {
        String sd = (String)v;
        SimpleDateFormat df;
        if ( sd.length() == ldf.length() ) {
            df = new SimpleDateFormat(ldf);
        } else {
            df = new SimpleDateFormat(sdf);
        }
        return df.parse(sd);
    }
    
    private String _DatetoString(Date d) {
        SimpleDateFormat df;
        
        if ( new SimpleDateFormat("HH:mm:ss").format(d).equals("00:00:00") ) {
            df = new SimpleDateFormat(sdf);
        } else {
            df = new SimpleDateFormat(ldf);
        }
        return df.format(d);
    }
    
    public Date toDate() throws ParseException {
        return _toDate(obj);
    }
    
    
    public String[] toStringArray() {
        Object[] oa = (Object[])obj;
        String[] arr = new String[ oa.length ];
        for (int i=0; i<arr.length; i++ ) {
            arr[i] = (String)oa[i];
        }
        return arr;
    }
    
    public int[] toIntArray() {
        Object[] oa = (Object[])obj;
        int[] arr = new int[ oa.length ];
        for (int i=0; i<arr.length; i++ ) {
            arr[i] = Integer.parseInt( (String)oa[i] );
        }
        return arr;
    }
    
    public float[] toFloatArray() {
        Object[] oa = (Object[])obj;
        float[] arr = new float[ oa.length ];
        for (int i=0; i<arr.length; i++ ) {
            arr[i] = Float.parseFloat( (String)oa[i] );
        }
        return arr;
    }
    
    public long[] toLongArray() {
        Object[] oa = (Object[])obj;
        long[] arr = new long[ oa.length ];
        for (int i=0; i<arr.length; i++ ) {
            arr[i] = Long.parseLong( (String)oa[i] );
        }
        return arr;
    }
    
    public short[] toShortArray() {
        Object[] oa = (Object[])obj;
        short[] arr = new short[ oa.length ];
        for (int i=0; i<arr.length; i++ ) {
            arr[i] = Short.parseShort((String)oa[i] );
        }
        return arr;
    }
    
    public double[] toDoubleArray() {
        Object[] oa = (Object[])obj;
        double[] arr = new double[ oa.length ];
        for (int i=0; i<arr.length; i++ ) {
            arr[i] = Double.parseDouble((String)oa[i] );
        }
        return arr;
    }
    
    public boolean[] toBoolArray() {
        Object[] oa = (Object[])obj;
        boolean[] arr = new boolean[ oa.length ];
        for (int i=0; i<arr.length; i++ ) {
            arr[i] = ((String)oa[i]).equals("1");
        }
        return arr;
    }
    
    public Date[] toDateArray() throws ParseException {
        Object[] oa = (Object[])obj;
        Date[] arr = new Date[ oa.length ];
        for (int i=0; i<arr.length; i++ ) {
            arr[i] = _toDate(oa[i]);
        }
        return arr;
    }
    
    public Object toArray(Class<?> type) {
        Object[] oa = (Object[])obj;
        Object arr = Array.newInstance(type, oa.length);
        for (int i=0; i<oa.length; i++) {
            Array.set(arr, i, oa[i]);
        }
        return arr;        
    }
    
    public Object[] toObjectArray() {
        return (Object[])obj;
    }
    
    private String objectToPg(Object o) throws IllegalArgumentException, IllegalAccessException {
        inObject = true;
        String s = "ROW(";
        Field[] fl = o.getClass().getFields();
        
        for (int i=0; i<fl.length; i++) {
            if ( i > 0 ) s += ",";
            s += valueToPg( fl[i].get(o) );
        }
        
        s += ")";
        return s;
    }
    
    private String valueToPg(Object o) throws IllegalArgumentException, IllegalAccessException {
        
        if ( o == null ) {
            return "NULL";
        }

        
        String tip = o.getClass().getTypeName();
        if ( o.getClass().isArray() ) {
            return arrayToPg(  (Object[])o );
        } else if ( tip.equals("java.lang.String") ) {
            //return inObject ? "'"+(String)o+"'" : (String)o;
            return "'"+(String)o+"'";
        } else if ( tip.equals("java.lang.Integer") ) {
            return Integer.toString( (int)o );
        } else if (tip.equals("float")) {
            return Float.toString( (float)o );
        } else if (tip.equals("java.lang.Long")) {
            return Long.toString( (long)o );
        } else if (tip.equals("short")) {
            return Short.toString( (short)o );
        } else if (tip.equals("double")) {
            return Double.toString( (double)o );
        } else if (tip.equals("boolean")) {
            return ((boolean)o) == true ? "1" : "0";
        } else if (tip.equals("java.lang.Boolean")) {
            return ((Boolean)o) == true ? "1" : "0";
        } else if ( tip.equals("char") ) {
            return ((String)o);
        } else if (tip.equals("java.util.Date")) {
            return "'"+_DatetoString((Date)o)+"'";
            //return inObject ? "'"+_DatetoString((Date)o)+"'" : _DatetoString((Date)o);
        } else {
            return objectToPg(o);
        }        
    }
    
    private String arrayToPg(Object[] arr) throws IllegalArgumentException, IllegalAccessException {
        inObject = true;
        String s = "ARRAY[";
        for (int i=0; i<arr.length; i++) {
            if ( i > 0 ) s += ",";
            s += valueToPg( arr[i] );
        }
        s += "]";
        return s;
    }
    
    public String toPgString() throws IllegalArgumentException, IllegalAccessException {
        inObject = false;
        return valueToPg(obj);
    }    
}
