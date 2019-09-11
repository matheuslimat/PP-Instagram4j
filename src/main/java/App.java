import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.util.PropertiesReader;
import org.matheuslimat.models.Desirable;
import org.matheuslimat.models.DesirableImpl;
import org.matheuslimat.models.Followable;
import org.matheuslimat.models.FollowableImpl;
import org.matheuslimat.models.Undesirable;
import org.matheuslimat.models.UndesirableImpl;

public class App {

	public static Instagram4j instagram = null;
	public static final Integer TIME_SLEEP_UNFOLLOW = 10000;
	public static final Integer QUANTITY_FOLLOWERS = 6000;

	// ======================== MAIN ==========================
	public static void main(String[] args) throws IOException, InterruptedException {
		InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream("data.properties");

		Properties prop = new Properties();
		prop.load(inputStream);

		login(prop);

	}
	// =========================================================

	public static void login(Properties prop) throws ClientProtocolException, IOException, InterruptedException {
		System.out.println("Realizando login no instagram...");
		String username = prop.getProperty("user");
		String password = prop.getProperty("password");
		instagram = Instagram4j.builder().username(username).password(password).build();
		instagram.setup();
		instagram.login();

		Followable followable = new FollowableImpl();
		
		Undesirable undesirable = new UndesirableImpl();
		// Removendo não seguidores
//		undesirable.removeAllUnfollowers(undesirable.getUnfollowers(getUserByHandle(prop), instagram), instagram,
//				TIME_SLEEP_UNFOLLOW, QUANTITY_FOLLOWERS);
		
		Desirable desirable = new DesirableImpl();
		
		// Listando fãs retorna um set<InstagramUserSummary>
//		desirable.getFans(followable.getFollowers(getUserByHandle(prop), instagram), followable.getFollowing(getUserByHandle(prop), instagram));
		
		desirable.followAllByUsername("coloqueusuarioaprocuraraqui", instagram);

	}

	public static InstagramSearchUsernameResult getUserByHandle(Properties prop)
			throws ClientProtocolException, IOException {
		InstagramSearchUsernameResult userResult = instagram
				.sendRequest(new InstagramSearchUsernameRequest(prop.getProperty("searchUsername")));
		return userResult;
	}
	
}
