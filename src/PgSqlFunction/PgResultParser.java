/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PgSqlFunction;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author BARIS
 */
public class PgResultParser {

    private final ArrayList<Class> classes;
    private String dateFormat = "yyyy-MM-dd";
    private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    public PgResultParser() {
        classes = new ArrayList<>();
    }

    public Object parse(String str) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
        return read(str, 0);
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    public void addClass(Class T) {
        classes.add(T);
    }
    
    public void clearClasses() {
        classes.clear();
    }
    
    private int knownTypeIndex(String typeName) {
        for(int i=0; i<classes.size(); i++) {
            if ( classes.get(i).getTypeName().equals(typeName) ) {
                return i;
            }
        }
        return -1;
    }

    private Object rowToClass(Object[] row, int level) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {

        Class T = classes.get(level);

        Field[] fields = T.getDeclaredFields();

        Object obj = T.newInstance();        

        for (int i = 0; i < row.length; i++) {
            Field f = fields[i];            
            
            
            if ( f.getModifiers() == Modifier.PUBLIC) {

                PgRenderObject ro = new PgRenderObject(row[i], dateTimeFormat, dateFormat);

                String typeName = f.getAnnotatedType().getType().getTypeName();                

                switch (typeName) {
                    case "java.lang.String":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toString());
                        break;
                    case "java.lang.String[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toStringArray());
                        break;
                    case "int":
                        if (row[i] == null) f.set(obj, 0); else f.set(obj, ro.toInt());
                        break;
                    case "int[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toIntArray());
                        break;
                    case "java.lang.Integer":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toInt());
                        break;
                    case "java.lang.Integer[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toIntArray());
                        break;
                    case "float":
                        if (row[i] == null) f.set(obj, 0); else f.set(obj, ro.toFloat());
                        break;
                    case "float[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toFloatArray());
                        break;
                    case "java.lang.Long":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toLong());
                        break;
                    case "java.lang.Long[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toLongArray());
                        break;
                    case "long":
                        if (row[i] == null) f.set(obj, 0); else f.set(obj, ro.toLong());
                        break;
                    case "long[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toLongArray());
                        break;
                    case "short":
                        if (row[i] == null) f.set(obj, 0); else f.set(obj, ro.toShort());
                        break;
                    case "short[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toShortArray());
                        break;
                    case "double":
                        if (row[i] == null) f.set(obj, 0); else f.set(obj, ro.toDouble());
                        break;                    
                    case "double[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toDoubleArray());
                        break;
                    case "Double":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toDouble());
                        break;
                    case "Double[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toDoubleArray());
                        break;
                    case "char":
                        if (row[i] == null) f.set(obj, (char)0); else f.set(obj, ro.toChar());
                        break;
                    case "boolean":
                        if (row[i] == null) f.set(obj, false); else f.set(obj, ro.toBool());
                        break;
                    case "Boolean":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toBool());
                        break;
                    case "Boolean[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toBoolArray());
                        break;
                    case "java.util.Date":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toDate());
                        break;
                    case "java.util.Date[]":
                        if (row[i] == null) f.set(obj, null); else f.set(obj, ro.toDateArray());
                        break;
                    default:
                        if (row[i] == null) {
                            f.set(obj, null);
                        } else {
                            if (f.getType().isArray()) {
                                f.set(obj, ro.toArray(f.getType().getComponentType()));
                            } else {
                                f.set(obj, rowToClass(ro.toObjectArray(), level + 1));
                            }
                        }
                        break;
                }
            }
        }

        return obj;
    }

    private Object[] arrRead(String str, int level) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
        ArrayList<Object> arr = new ArrayList<>();
        int pc = 0;
        char begin = Character.MIN_VALUE;
        char end = Character.MIN_VALUE;

        String s = "";
        for (int i = 0; i < str.length(); i++) {
            if (pc == 0) {
                if (str.charAt(i) == ',') {
                    arr.add(read(s, level));
                    s = "";
                } else if (s.length() == 0) {
                    switch (str.charAt(i)) {
                        case '{':
                            begin = '{';
                            end = '}';
                            pc = 1;
                            break;
                        case '(':
                            begin = '(';
                            end = ')';
                            pc = 1;
                            break;
                        case '"':
                            begin = Character.MIN_VALUE;
                            end = '"';
                            pc = 1;
                            break;
                        default:
                            break;
                    }
                    if (((int) str.charAt(i)) > 32) {
                        s += str.charAt(i);
                    }
                } else {
                    if (((int) str.charAt(i)) > 32) {
                        s += str.charAt(i);
                    }
                }

            } else {
                if (str.charAt(i) == end) {
                    pc--;
                } else if (str.charAt(i) == begin) {
                    pc++;
                }
                s += str.charAt(i);
            }
        }
        arr.add(read(s, level));

        return arr.toArray();
    }

    private Object read(String data, int level) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
        String str = data.trim();

        if (str.length() < 1) {
            return null;
        } else if (str.toUpperCase().equals("NULL")) {
            return null;
        } else if ((str.charAt(0) == '{') && (str.charAt(str.length() - 1) == '}')) {
            return arrRead(str.substring(1, str.length() - 1), level);
        } else if ((str.charAt(0) == '(') && (str.charAt(str.length() - 1) == ')')) {
            Object[] row = arrRead(str.substring(1, str.length() - 1), level + 1);
            Object obj = rowToClass(row, level);
            return obj;
        } else if ((str.charAt(0) == '"') && (str.charAt(str.length() - 1) == '"')) {
            if (str.substring(1, str.length() - 1).trim().equals("")) {
                return null;
            } else {
                return str.substring(1, str.length() - 1).trim();
            }
        } else {
            if (str.trim().equals("")) {
                return null;
            } else {
                return str;
            }
        }
    }
}
