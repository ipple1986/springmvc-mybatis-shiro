package cn.jufuns.core.shiro.cache.jedis;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.io.DefaultSerializer;
import org.apache.shiro.io.Serializer;
import org.apache.shiro.session.Session;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public abstract class AbstractJedisManager<K, V, C> implements JedisManager<K, V> {

	private Logger LOGGER = Logger.getLogger(AbstractJedisManager.class);
	private Serializer<K> keySerializer;
	private Serializer<V> valueSerializer;
	private JedisPool jedisPool;
	private int dbIndex;
	
	@SuppressWarnings("unchecked")
	public AbstractJedisManager() {
		
		this.keySerializer = new DefaultSerializer<K>();
		this.valueSerializer = new DefaultSerializer<V>();
		Type genType = getClass().getGenericSuperclass();   
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		LOGGER = Logger.getLogger((Class<C>)params[2]);
	}
	public AbstractJedisManager(int dbIndex) {
		this.dbIndex = dbIndex;
		this.keySerializer = new DefaultSerializer<K>();
		this.valueSerializer = new DefaultSerializer<V>();
	}
	public Jedis getJedis() {
		Jedis jedis = null;
		try {
			jedis = getJedisPool().getResource();
		} catch (JedisConnectionException e) {
			String message = StringUtils.trimWhitespace(e.getMessage());
			LOGGER.error(message);
			System.exit(0);
			throw new JedisConnectionException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jedis;
	}

	public void releaseResource(Jedis jedis, boolean isBroken) {
		if (jedis == null)
			return;
		jedis.close();
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}
	@Override
	public V get( K key) throws Exception {
		return get(this.dbIndex,key);

	}
	@Override
	public V get(int dbIndex, K key) throws Exception {
		Jedis jedis = null;
		byte[] result = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			result = jedis.get(keySerializer.serialize(key));
			if(null == result)return null;
			//LOGGER.info("查询redis缓存 ,key="+key+"\tvalue="+(valueSerializer.deserialize(result)));
			return valueSerializer.deserialize(result);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			releaseResource(jedis, isBroken);
		}

	}
	@Override
	public void del( K key) throws Exception {
		this.del(this.dbIndex, key);
	}
	@Override
	public void del(int dbIndex, K key) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			Long result = jedis.del(keySerializer.serialize(key));
			//LOGGER.info(String.format("删除redis缓存 key=%s\t%s",key, result));
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			releaseResource(jedis, isBroken);
		}
	}
	
	@Override
	public void set( K key, V value) throws Exception {
		this.set(this.dbIndex, key, value);
	}

	@Override
	public void set(int dbIndex, K key, V value) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			//LOGGER.info(String.format("set redis缓存 key=%s\tvalue=%s",key, value));
			jedis.set(keySerializer.serialize(key), valueSerializer.serialize(value));
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			releaseResource(jedis, isBroken);
		}
	}
	
	@Override
	public void setex(K key, V value, int expireTime) throws Exception {
		this.setex(this.dbIndex, key, value, expireTime);
	}
	
	
	@Override
	public void setex(int dbIndex, K key, V value, int expireTime) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			
			if(jedis.exists(keySerializer.serialize(key))){
				//LOGGER.info(String.format("updateex redis缓存 key=%s\tvalue=%s",key, value));
			}else{
				//LOGGER.info(String.format("setex redis缓存 key=%s\tvalue=%s",key, value));
			}
			if (expireTime > 0){
				jedis.setex(keySerializer.serialize(key), expireTime,valueSerializer.serialize(value));				
			}else{
				jedis.set(keySerializer.serialize(key), valueSerializer.serialize(value));
			}
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			releaseResource(jedis, isBroken);
		}
	}
	@Override
	public Collection<V> keys( String wildcardkey) throws Exception {
		return this.keys(this.dbIndex, wildcardkey);
	}
	@Override
	public Collection<V> keys(int dbIndex, String wildcardkey) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		Set<V> sessions = new HashSet<V>();
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			Set<byte[]> byteKeys = jedis.keys((wildcardkey).getBytes());
			//LOGGER.info(String.format("获取所有激活的session  keys---redis缓存  session size=%d",byteKeys.size()));
			if (byteKeys != null && byteKeys.size() > 0) {
				for (byte[] bs : byteKeys) {
					V obj = valueSerializer.deserialize(jedis.get(bs));
					if (obj instanceof Session) {
						sessions.add(obj);
					}
				}
			}
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			releaseResource(jedis, isBroken);
		}
		return sessions;
	}
}
