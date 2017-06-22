package cn.jufuns.core.shiro.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import cn.jufuns.core.shiro.cache.jedis.JedisManager;


public class JedisCache<K, V> implements Cache<K, V> {

	private static Logger LOGGER = Logger.getLogger(JedisCache.class);

	/**
	 * 为了不和其他的缓存混淆，采用追加前缀方式以作区分
	 * 
	 * key: cn.jufus.saas.shiro.cache.JedisCache:JedisCache:key
	 */
	private static final String REDIS_CACHE_PREFIX = JedisCache.class.getName().concat(":");
	/**
	 * 使用redis的第二个数据库
	 */
	private int dbIndex = 1;

	private JedisManager<K, V> jedisManager;

	private String name;

	public JedisCache(String name, JedisManager<K, V> jedisManager) {
		this.name = name == null ? "JedisCache" : name;
		this.jedisManager = jedisManager;
	}

	public JedisCache(String name, JedisManager<K, V> jedisManager, int dbIndex) {
		this.name = name == null ? "JedisCache" : name;
		this.jedisManager = jedisManager;
		this.dbIndex = dbIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public V get(K key) throws CacheException {
		try {
			return jedisManager.get(dbIndex, key);
		} catch (Exception e) {
			LOGGER.error("get value by cache throw exception", e);
		}
		return null;
	}

	@Override
	public V put(K key, V value) throws CacheException {
		V previos = get(key);
		try {
			jedisManager.setex(dbIndex, key,value, -1);
		} catch (Exception e) {
			LOGGER.error("加入缓存", e);
		}
		return previos;
	}

	@Override
	public V remove(K key) throws CacheException {
		V previos = get(key);
		try {
			jedisManager.del(dbIndex, key);
		} catch (Exception e) {
			LOGGER.error("删除缓存报错", e);
		}
		return previos;
	}

	@Override
	public void clear() throws CacheException {
		
	}

	@Override
	public int size() {
		if (keys() == null)
			return 0;
		return keys().size();
	}

	@Override
	public Set<K> keys() {
		// TODO
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO
		return null;
	}

	private String buildCacheKey(Object key) {
		return REDIS_CACHE_PREFIX + getName() + ":" + key;
	}

}
