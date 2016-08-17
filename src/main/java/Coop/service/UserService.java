package Coop.service;

import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import Coop.model.User;
import Coop.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired UserMapper userMapper;

    private String validate(User user) {
        if (StringUtils.isBlank(user.getName())) return "이름을 입력하세요.";
        if (StringUtils.isBlank(user.getEmail())) return "이메일 주소를 입력하세요.";
        if (StringUtils.isBlank(user.getPassword())) return "비밀번호를 입력하세요";
        if (StringUtils.isBlank(user.getId())) return "id를 입력하세요";
        return null;
    }

    public String validateBeforeInsert(User user) {
        String s = validate(user);
        if (s != null) return s;	// error Msg를 리턴

        User user2 = userMapper.selectById(user.getId());
        if (user2 != null)
            return "로그인ID가 중복됩니다.";

        return null;
    }

    public String validateBeforeUpdate(User user) {
        String s = validate(user);
        if (s != null) return s;

        User user2 = userMapper.selectById(user.getId());
        if (user2 != null && user.getId() != user2.getId())
            return "로그인ID가 중복됩니다.";

        return null;
    }
    
    public String encryptPasswd(String password) {
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
    
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof MyAuthenticationProvider.MyAuthenticaion)
            return ((MyAuthenticationProvider.MyAuthenticaion) authentication).getUser();
        return null;
    }

    public static void setCurrentUser(User user) {
        ((MyAuthenticationProvider.MyAuthenticaion)
                SecurityContextHolder.getContext().getAuthentication()).setUser(user);
    }


}
