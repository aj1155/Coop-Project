package Coop.service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import Coop.mapper.UserMapper;
import Coop.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired UserMapper userMapper;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//System.out.println("1");
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(id, password);
    }
	
	public Authentication authenticate(String id, String password) throws AuthenticationException {
		//System.out.println("2");
        User user = userMapper.selectById(id);
        if (user == null) return null;
        if (user.getPassword().equals(encryptPasswd(password)) == false) return null;
        //if(user.getPassword().equals(password) == false) return null;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_전체"));
        //grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType()));
        return new MyAuthenticaion(id, password, grantedAuthorities, user);
    }

	@Override
    public boolean supports(Class<?> authentication) {
		//System.out.println("3");
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

	public static String encryptPasswd(String password) {
		//System.out.println("4");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = password.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++)
                sb.append(Integer.toHexString(0xff & digested[i]));
            return sb.toString();
        }
        catch (Exception e) {
            return password;
        }
    }
	
	public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;
        User user;
        public MyAuthenticaion (String id, String password, List<GrantedAuthority> grantedAuthorities, User user) {
            super(id, password, grantedAuthorities);
            this.user = user;
           //System.out.println("5");
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }



}
