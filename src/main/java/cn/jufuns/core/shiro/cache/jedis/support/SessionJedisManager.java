package cn.jufuns.core.shiro.cache.jedis.support;

import java.io.Serializable;

import org.apache.shiro.session.Session;

import cn.jufuns.core.shiro.cache.jedis.AbstractJedisManager;

public class SessionJedisManager extends AbstractJedisManager<Serializable,Session,SessionJedisManager>{}
