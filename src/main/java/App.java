import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramFollowRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUnfollowRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.instagram4j.util.PropertiesReader;

public class App {
	
	public static Instagram4j instagram = null;
	public static final Integer TIME_SLEEP_UNFOLLOW = 12000;

	// ======================== MAIN ==========================
	public static void main(String[] args) throws IOException, InterruptedException {
		InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream("data.properties");
		
		Properties prop = new Properties();
		prop.load(inputStream);
		
		login(prop);
		removeAllUnfollowers(getUnfollowers(getUserByHandle(prop)), getUserByHandle(prop));
		
	}
	// =========================================================
	
	public static void login(Properties prop) throws ClientProtocolException, IOException, InterruptedException {
		System.out.println("Realizando login no instagram...");
		String username = prop.getProperty("user");
		String password = prop.getProperty("password");
		instagram = Instagram4j.builder().username(username).password(password).build();
		instagram.setup();
		instagram.login();

	}
	
	public static InstagramSearchUsernameResult getUserByHandle(Properties prop) throws ClientProtocolException, IOException {
		InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(prop.getProperty("searchUsername")));
		return userResult;
	}
	
	// Pega os não seguidores comparando dois resutados
	public static Set<InstagramUserSummary> getUnfollowers(InstagramSearchUsernameResult userResult) throws ClientProtocolException, IOException{
		System.out.println("Listando os não seguidores....");
		InstagramGetUserFollowersResult followers = instagram.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk()));
		InstagramGetUserFollowersResult following = instagram.sendRequest(new InstagramGetUserFollowingRequest(userResult.getUser().getPk()));
		
		//lista de seguidores
		List<InstagramUserSummary> usersFollowers = followers.getUsers();
		//lista de seguindo
		List<InstagramUserSummary> usersFollowing = following.getUsers();
		
		//Set de não seguidores
		Set<InstagramUserSummary> unfollowers = new HashSet<InstagramUserSummary>();
		
		
		//percorre a lista de seguindo e coloca no set
		for (InstagramUserSummary followingUser : usersFollowing) {
			unfollowers.add(followingUser);
		}
		
		//remove dos seguindo os contido nos seguidores
		unfollowers.removeAll(usersFollowers);
		
		return unfollowers;
		
	}
	
	public static void removeAllUnfollowers(Set<InstagramUserSummary> unfollowers, InstagramSearchUsernameResult userResult) throws ClientProtocolException, IOException, InterruptedException {
		for (InstagramUserSummary unfollow : unfollowers) {
			instagram.sendRequest(new InstagramUnfollowRequest(unfollow.getPk()));
			Thread.sleep(TIME_SLEEP_UNFOLLOW);
			System.out.println(unfollow.getUsername() + " Removido!");
		}
		System.out.println("Feito! Todos os não seguidores foram removidos do seu instagram.");
	}
	
	
}
