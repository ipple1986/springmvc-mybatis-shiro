package cn.jufuns.core.util;

import java.util.Collection;

public interface Repository<K,V> {
    void set(V v);
    void del(K k);
    V get(K k);
    Collection<V> getall();
    Collection<V> getall(String wildcard);
}
