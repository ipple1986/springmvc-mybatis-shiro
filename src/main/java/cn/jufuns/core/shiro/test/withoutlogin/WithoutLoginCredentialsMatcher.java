package cn.jufuns.core.shiro.test.withoutlogin;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class WithoutLoginCredentialsMatcher implements CredentialsMatcher{

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		return Boolean.TRUE;
	}

}
