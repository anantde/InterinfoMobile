package mNotification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import mDao.MobLoginDAO;

import org.json.simple.JSONObject;

public class AndroidSendNotification{
	@SuppressWarnings("unchecked")
	/**
	 * @param title
	 * @param message
	 * @param userid
	 * @return
	 */
	public void SendNotification(String title, String message, int userid) {
		title = "Interinfo Mobile";
		message = "New Report is assigned by Admin...!";
		try {
			String authKey = "AAAAZ0DVtb4:APA91bGMR7RE9hdRTuYZArk_-crySDQgF4POC69-DoonNbYsNGK6D6EV9wzZFYuTLttdV8H3H0tWPvKnF9B47FEU8WHvqnlCK21lP3gGJ31oRZOnbt45gZfgh_sAXtVT8QwxPOdheHHXlHm8UjMTVmWwoNdsDWQHTg"; // You
			String FMCurl = "https://fcm.googleapis.com/fcm/send";
			System.out.println("Time in ms: "+ System.currentTimeMillis());
			long uniqueId = System.currentTimeMillis();
			URL url = new URL(FMCurl);
			
			MobLoginDAO loginDAO = new MobLoginDAO();
			
			
			JSONObject data = new JSONObject();

			JSONObject info = new JSONObject();
			info.put("title", title); // Notification title
			info.put("message", message); // Notification body
			info.put("id", uniqueId); // unique id
			data.put("data", info);

			ArrayList<String> deviceList = loginDAO.getDeviceIds(userid);
			for (int i = 0; i < deviceList.size(); i++) {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setUseCaches(false);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Authorization", "key=" + authKey);
				conn.setRequestProperty("Content-Type", "application/json");
				data.put("to", deviceList.get(i));
				OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(data.toString());
				wr.flush();
				wr.close();

				int responseCode = conn.getResponseCode();
				System.out.println("Response Code : " + responseCode);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			    String inputLine;
			    StringBuffer response = new StringBuffer();
			    
			    
			    while ((inputLine = in.readLine()) != null) {
			        response.append(inputLine);
			    }
			    in.close();
				
			}
			

		} catch (Exception e) {
			System.out
					.println("Error while pushing notification in pushFCMNotification() NotificationToAndroid !!! ");
			e.printStackTrace();
		}
	}
}
