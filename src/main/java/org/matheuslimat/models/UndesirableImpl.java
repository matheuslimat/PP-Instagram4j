package org.matheuslimat.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUnfollowRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

/**
 * @author Matheus Lima Tavares da Costa
 */

public class UndesirableImpl implements Undesirable {

	/**
	 * Retorna todos os não seguidores
	 */
	@Override
	public Set<InstagramUserSummary> getUnfollowers(InstagramSearchUsernameResult userResult, Instagram4j instagram)
			throws ClientProtocolException, IOException {
		System.out.println("Listando os não seguidores....");

		Followable followable = new FollowableImpl();
		// lista de seguidores
		List<InstagramUserSummary> usersFollowers = followable.getFollowers(userResult, instagram);

		// lista de seguindo
		List<InstagramUserSummary> usersFollowing = followable.getFollowing(userResult, instagram);
		
		// Set de não seguidores
		Set<InstagramUserSummary> unfollowers = new HashSet<InstagramUserSummary>();

		// percorre a lista de seguindo e coloca no set
		for (InstagramUserSummary followingUser : usersFollowing) {
			unfollowers.add(followingUser);
		}

		unfollowers.removeAll(usersFollowers);

		System.out.println("Numero de n seguidores: " + unfollowers.size());
		for (InstagramUserSummary ius : unfollowers) {
			System.out.println("Não te segue: " + ius.getUsername());
		}

		return unfollowers;
	}

	/**
	 * Deixa de seguir todos os não seguidores
	 */
	@Override
	public void removeAllUnfollowers(Set<InstagramUserSummary> unfollowers, Instagram4j instagram,
			Integer timeSleepUnfollow) throws ClientProtocolException, IOException, InterruptedException {
		for (InstagramUserSummary unfollow : unfollowers) {
			instagram.sendRequest(new InstagramUnfollowRequest(unfollow.getPk()));
			Thread.sleep(timeSleepUnfollow);
			System.out.println(unfollow.getUsername() + " Removido!");
		}

		System.out.println("Feito! Todos os não seguidores foram removidos do seu instagram.");

	}

	/**
	 * Deixa de seguir todos os não seguidores que tem uma quantidade de seguidores
	 * passada via parametro
	 */
	@Override
	public void removeAllUnfollowers(Set<InstagramUserSummary> unfollowers, Instagram4j instagram,
			Integer timeSleepUnfollow, Integer numberFollowers)
			throws ClientProtocolException, IOException, InterruptedException {
		for (InstagramUserSummary unfollow : unfollowers) {
			InstagramSearchUsernameResult userResult = instagram
					.sendRequest(new InstagramSearchUsernameRequest(unfollow.getUsername()));

			if (userResult.getUser().getFollower_count() < numberFollowers
					&& userResult.getUser().getFollower_count() > 0) {
				instagram.sendRequest(new InstagramUnfollowRequest(unfollow.getPk()));
				Thread.sleep(timeSleepUnfollow);
				System.out.println("Qtd de seguidores: " + userResult.getUser().getFollower_count() + " | "
						+ unfollow.getUsername() + " Removido!");
			}
		}

		System.out.println("Feito! Todos os não seguidores que tinha menos de " + numberFollowers
				+ " seguidores foram removidos do seu instagram.");

	}

}
