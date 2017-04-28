/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlPl.Result;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author BARIS
 */
public class MySqlRowResult implements Map<String, Object> {
    
    private final HashMap<String,Object> row;

    public MySqlRowResult() {
        row = new HashMap<>();
    }   

    @Override
    public int size() {
        return row.size();
    }

    @Override
    public boolean isEmpty() {
        return row.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return row.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return row.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return row.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return row.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return row.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        row.putAll(m);
    }

    @Override
    public void clear() {
        row.clear();
    }

    @Override
    public Set<String> keySet() {
        return row.keySet();
    }

    @Override
    public Collection<Object> values() {
        return row.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return row.entrySet();
    }
    
}
