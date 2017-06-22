package cn.jufuns.core.shiro.cache.jedis;

import java.util.Collection;

public interface JedisManager<K,V> {
	
	public V get(K key) throws Exception ;
	public V get(int dbIndex, K key) throws Exception ;
	public void del(K key) throws Exception ;
	public void del(int dbIndex, K key) throws Exception ;
	public void set(K key, V value) throws Exception ;
	public void set(int dbIndex, K key, V value) throws Exception ;
	public void setex(K key, V value, int expireTime) throws Exception ;
	public void setex(int dbIndex, K key, V value, int expireTime) throws Exception ;
	public  Collection<V> keys(String wildcardkey) throws Exception;
	public  Collection<V> keys(int dbIndex, String wildcardkey) throws Exception;
}
