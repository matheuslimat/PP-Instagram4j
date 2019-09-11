package org.matheuslimat.models;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramFollowRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;
import org.matheuslimat.constantes.ConstantesInstagram;

public class DesirableImpl implements Desirable {

	@Override
	public Set<InstagramUserSummary> getFans(List<InstagramUserSummary> followers, List<InstagramUserSummary> following)
			throws ClientProtocolException, IOException {

		Set<InstagramUserSummary> fans = new HashSet<InstagramUserSummary>();
		fans.addAll(followers);
		fans.removeAll(following);

		for (InstagramUserSummary instagramUserSummary : fans) {
			System.out.println(instagramUserSummary.getUsername());
		}

		return fans;

	}

	@Override
	public void followAllByUsername(String userName, Instagram4j instagram) throws ClientProtocolException, IOException, InterruptedException {

		InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(userName));
		Followable followable = new FollowableImpl();
		List<InstagramUserSummary> followers = followable.getFollowers(userResult, instagram);

		for (InstagramUserSummary follow : followers) {
			Random r = new Random();
			Integer numero = r.nextInt(ConstantesInstagram.MAX_RANDOM_TIME_FOLLOW);
			numero += ConstantesInstagram.MIN_RANDOM_TIME_FOLLOW;
			
			instagram.sendRequest(new InstagramFollowRequest(follow.getPk()));
			System.out.println("Seguindo: " + follow.getUsername() + " Nome Completo: " + follow.getFull_name());
			Thread.sleep(numero);
			
		}

	}

}
