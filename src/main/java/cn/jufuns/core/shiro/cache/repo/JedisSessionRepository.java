package cn.jufuns.core.shiro.cache.repo;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;

import cn.jufuns.core.shiro.cache.jedis.support.SessionJedisManager;
import cn.jufuns.core.shiro.session.SessionStatus;
import cn.jufuns.core.shiro.session.mgt.SessionRepositoryManager;
import cn.jufuns.core.util.Repository;

public class JedisSessionRepository implements Repository<Serializable, Session> {
	private static Logger LOGGER = Logger.getLogger(JedisSessionRepository.class);

	public static final String REDIS_SHIRO_SESSION = "ShiroSessionCachePrefix:";
	public static final String REDIS_SHIRO_ALL = "*ShiroSessionCachePrefix:*";
	private static final int SESSION_VAL_TIME_SPAN = 18000;
	private static final int DB_INDEX = 1;

	private SessionJedisManager jedisManager;

	private String buildRedisSessionKey(Serializable sessionId) {
		return REDIS_SHIRO_SESSION + sessionId;
	}

	public SessionJedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(SessionJedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

	@Override
	public void set(Session v) {
		if (v == null || v.getId() == null)
			throw new NullPointerException("会话为null");
		try {
			Long expireTime = v.getTimeout() / 1000;
			getJedisManager().setex(DB_INDEX, buildRedisSessionKey(v.getId()), v, expireTime.intValue());
		} catch (Exception e) {
			LOGGER.error(String.format("保存会话错误。id:[%s]", v.getId()), e);
		}
	}

	@Override
	public void del(Serializable k) {
		if (k == null) {
			throw new NullPointerException("会话 id为空");
		}
		try {
			getJedisManager().del(DB_INDEX, buildRedisSessionKey(k));
		} catch (Exception e) {
			LOGGER.error(String.format("保存会话错误，id:[%s]", k), e);
		}
	}

	@Override
	public Session get(Serializable k) {
		if (k == null)
			throw new NullPointerException("会话 id为空");
		Session session = null;
		try {
			session = getJedisManager().get(DB_INDEX, buildRedisSessionKey(k));
		} catch (Exception e) {
			LOGGER.error(String.format("获取session异常，id:[%s]", k), e);
		}
		return session;
	}

	@Override
	public Collection<Session> getall() {
		return this.getall(REDIS_SHIRO_ALL);
	}

	@Override
	public Collection<Session> getall(String wildcard) {
		Collection<Session> sessions = null;
		try {
			sessions = getJedisManager().keys(DB_INDEX, wildcard);
		} catch (Exception e) {
			LOGGER.error("获取全部session异常", e);
		}
		return sessions;
	}

}
