package Coop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.devices.exceptions.InvalidDeviceTokenFormatException;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
@Service
public class IOSPushService {
	
	private static String token = "ac6cad02f0cc9a770b203af13cf2e34978a6c26e23fa67092f1899c6ea7dde96";
	private static String keystore = "/usr/share/tomcat9/webapps/Coop/res/push/apns_pro.p12";
	private static String password = "coop!@#$";
	
	
	public static void iosPush(){
		System.out.println("Setting up Push notifictaion");
		
		try{
			PushNotificationPayload payload = new PushNotificationPayload();
			payload.addAlert("Coop : 새로운 댓글이 등록됬습니다");
			//payload.addBadge(1);
			payload.addSound("default");
			System.out.println(payload);
			System.out.println("payload setup successfull.");
			
			Push.payload(payload,keystore,password,false,token);
		}catch(Exception e){
			System.out.println("exception "+e.getMessage());
			e.printStackTrace();
		}
	}
	

	
	public void push(String key,String message) throws CommunicationException, KeystoreException, InvalidDeviceTokenFormatException{
		boolean sendSingle = true; 
		List<String> tokens = new ArrayList<String>();
		tokens.add(key);
		

		 
		PushNotificationManager pushManager = new PushNotificationManager();
		pushManager.initializeConnection(new AppleNotificationServerBasicImpl("/usr/share/tomcat9/webapps/Coop/res/push/apns_pro.p12", "coop!@#$", false));
		List<PushedNotification> notifications = new ArrayList<PushedNotification>();
		 
		PushNotificationPayload payload = PushNotificationPayload.complex();
        payload.addAlert(message);
        payload.addSound("default");
		if (sendSingle){
						
		             Device device = new BasicDevice();
		             device.setToken(tokens.get(0));
		             PushedNotification notification = pushManager.sendNotification(device, payload);
		             notifications.add(notification);
		          }else{
		              List<Device> device = new ArrayList<Device>();
		              for (String token : tokens){
		                  device.add(new BasicDevice(token));
		              }
		              notifications = pushManager.sendNotifications(payload, device);
		          }
		 
		List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
		         List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
		         int failed = failedNotifications.size();
		         int successful = successfulNotifications.size();


	}
}
