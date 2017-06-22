package cn.jufuns.core.shiro.cache.mgt;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

import cn.jufuns.core.shiro.cache.JedisCache;
import cn.jufuns.core.shiro.cache.jedis.support.DefaultJedisManager;

public class JedisCacheManager implements CacheManager, Destroyable {

	private DefaultJedisManager jedisManager;

	@Override
	public Cache<Object, Object> getCache(String name) {
		return new JedisCache<Object, Object>(name, getJedisManager());
	}

	@Override
	public void destroy() {
		// 如果和其他系统，或者应用在一起就不能关闭
		// getJedisManager().getJedis().shutdown();
	}

	public DefaultJedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(DefaultJedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

}