/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlPl.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author BARIS
 */
public class MySqlQueryResult implements List<MySqlRowResult> {

    private final ArrayList<MySqlRowResult> query = new ArrayList();
    
    @Override
    public int size() {
        return query.size();
    }

    @Override
    public boolean isEmpty() {
        return query.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return query.contains(o);
    }

    @Override
    public Iterator<MySqlRowResult> iterator() {
        return query.iterator();
    }

    @Override
    public Object[] toArray() {
        return query.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return query.toArray(a);
    }

    @Override
    public boolean add(MySqlRowResult e) {
        return query.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return query.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return query.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends MySqlRowResult> c) {
        return query.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends MySqlRowResult> c) {
        return query.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return query.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return query.retainAll(c);
    }

    @Override
    public void clear() {
        query.clear();
    }

    @Override
    public MySqlRowResult get(int index) {
        return query.get(index);
    }

    @Override
    public MySqlRowResult set(int index, MySqlRowResult element) {
        return query.set(index, element);
    }

    @Override
    public void add(int index, MySqlRowResult element) {
        query.add(index, element);
    }

    @Override
    public MySqlRowResult remove(int index) {
        return query.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return query.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return query.lastIndexOf(o);
    }

    @Override
    public ListIterator<MySqlRowResult> listIterator() {
        return query.listIterator();
    }

    @Override
    public ListIterator<MySqlRowResult> listIterator(int index) {
        return query.listIterator(index);
    }

    @Override
    public List<MySqlRowResult> subList(int fromIndex, int toIndex) {
        return query.subList(fromIndex, toIndex);
    }
    
}
