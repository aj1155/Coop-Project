package Coop.service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import Coop.model.FCM;

@Service
public class HTTPClientService {
	  static String server = "http://localhost:6472";
	  static RestTemplate rest = new RestTemplate();
	  static HttpHeaders headers = new HttpHeaders();
	  static HttpStatus status;

	 

	  public static String get(String uri) {
		headers.add("Content-Type", "application/json");
	    headers.add("Accept", "*/*");
	    headers.add("Authorization:key","AIzaSyCJvXV6ICI7o5E7y33MzuxuBCwpXdfSK70");
	    
	    HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
	    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
	    setStatus(responseEntity.getStatusCode());
	    return responseEntity.getBody();
	  }

	  public static String post(String uri, FCM json) {   
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "*/*");
		headers.add("Authorization","key=AIzaSyCJvXV6ICI7o5E7y33MzuxuBCwpXdfSK70");
		
	    HttpEntity<FCM> requestEntity = new HttpEntity<FCM>(json, headers);
	    ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.POST, requestEntity, String.class);
	    setStatus(responseEntity.getStatusCode());
	    return responseEntity.getBody();
	  }
	  
	  /*
	  public void put(String uri, String json) {
	    HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
	    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.PUT, requestEntity, null);
	    this.setStatus(responseEntity.getStatusCode());   
	  }

	  public void delete(String uri) {
	    HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
	    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.DELETE, requestEntity, null);
	    this.setStatus(responseEntity.getStatusCode());
	  }
	  */

	  public static HttpStatus getStatus() {
	    return status;
	  }

	  public static void setStatus(HttpStatus status) {
	    status = status;
	  } 

	

}
