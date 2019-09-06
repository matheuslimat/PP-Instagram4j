import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.util.PropertiesReader;

public class App {
	
	public static Instagram4j instagram = null;

	public static void main(String[] args) throws IOException {
		InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream("data.properties");
		
		Properties prop = new Properties();
		prop.load(inputStream);
		
		login(prop);
		
	}
	
	public static void login(Properties prop) throws ClientProtocolException, IOException {
		System.out.println("Realizando login no instagram...");
		String username = prop.getProperty("user");
		String password = prop.getProperty("password");
		instagram = Instagram4j.builder().username(username).password(password).build();
		instagram.setup();
		instagram.login();
	}
	
	public InstagramSearchUsernameResult getUserByHandle(Properties prop) throws ClientProtocolException, IOException {
		InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(prop.getProperty("searchUsername")));
		return userResult;
	}
	
//	public Set<String> getFollowers(InstagramSearchUsernameResult userResult) throws ClientProtocolException, IOException{
//		InstagramGetUserFollowersResult followers = instagram.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk()));
//		
//	}
	
}
