package Coop.service;

import org.springframework.stereotype.Service;
import Coop.model.User;



@Service
public class MobileAuthenticationService {
	
	public boolean AuthenticationUser(User user){
		if(user==null){
			return false;
		}
		else{
			return true;
		}
	}
}
