package com.matanalbert.assignment3.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LHMAuto<K, V> implements Map<K, V> {
    private LinkedHashMap<K, V> LHM;

    public LHMAuto() {
        LHM = new LinkedHashMap<>();
    }

    @Override
    public int size() {
        return LHM.size();
    }

    @Override
    public boolean isEmpty() {
        return LHM.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return LHM.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return LHM.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return LHM.get(key);
    }

    @Override
    public V put(K key, V value) {
        return LHM.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry<? extends K, ? extends  V> entry : map.entrySet()) {
            LHM.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return LHM.keySet();
    }

    @Override
    public Collection<V> values() {
        return LHM.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return LHM.entrySet();
    }

    @Override
    public V remove(Object key) {
        return LHM.remove(key);
    }



    public void create(K key, V value) {
        put(key, value);
    }

    public void read(K key) {
        get(key);
    }

    public void update(K key, V value) {
        if (LHM.containsKey(key)) {
            create(key, value);
        }
    }

    public void delete(K key) {
        remove(key);
    }

}
