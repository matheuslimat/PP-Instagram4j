package org.matheuslimat.models;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUnfollowRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

public class UndesirableImpl implements Undesirable {

	@Override
	public Set<InstagramUserSummary> getUnfollowers(InstagramSearchUsernameResult userResult, Instagram4j instagram)
			throws ClientProtocolException, IOException {
		System.out.println("Listando os não seguidores....");
		InstagramGetUserFollowersResult followers = instagram
				.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk()));
		InstagramGetUserFollowersResult following = instagram
				.sendRequest(new InstagramGetUserFollowingRequest(userResult.getUser().getPk()));

		// lista de seguidores
		List<InstagramUserSummary> usersFollowers = followers.getUsers();
		// lista de seguindo
		List<InstagramUserSummary> usersFollowing = following.getUsers();

		// Set de não seguidores
		Set<InstagramUserSummary> unfollowers = new HashSet<InstagramUserSummary>();

		// percorre a lista de seguindo e coloca no set
		for (InstagramUserSummary followingUser : usersFollowing) {
			unfollowers.add(followingUser);
		}

		// remove dos seguindo os contido nos seguidores
		unfollowers.removeAll(usersFollowers);

		return unfollowers;
	}

	@Override
	public void removeAllUnfollowers(Set<InstagramUserSummary> unfollowers, InstagramSearchUsernameResult userResult,
			Instagram4j instagram, Integer timeSleepUnfollow)
			throws ClientProtocolException, IOException, InterruptedException {
		for (InstagramUserSummary unfollow : unfollowers) {
			instagram.sendRequest(new InstagramUnfollowRequest(unfollow.getPk()));
			Thread.sleep(timeSleepUnfollow);
			System.out.println(unfollow.getUsername() + " Removido!");
		}
		System.out.println("Feito! Todos os não seguidores foram removidos do seu instagram.");

	}

}
