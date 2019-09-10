package org.matheuslimat.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

public class FollowableImpl implements Followable{

	@Override
	public List<InstagramUserSummary> getFollowers (InstagramSearchUsernameResult userResult, Instagram4j instagram)
			throws ClientProtocolException, IOException {
		String nextMaxIdFollowers = null;

		// lista de seguidores
		List<InstagramUserSummary> usersFollowers = new ArrayList<InstagramUserSummary>();
		
		while (true) {
			InstagramGetUserFollowersResult followers = instagram.sendRequest(
					new InstagramGetUserFollowersRequest(userResult.getUser().getPk(), nextMaxIdFollowers));
			usersFollowers.addAll(followers.getUsers());
			
			nextMaxIdFollowers = followers.getNext_max_id();
			
			if (nextMaxIdFollowers == null) {
				break;
			}
		}
		return usersFollowers;
	}
	
	@Override
	public List<InstagramUserSummary> getFollowing (InstagramSearchUsernameResult userResult, Instagram4j instagram)
			throws ClientProtocolException, IOException {
		String nextMaxIdFollowings = null;

		// lista de seguidores
		List<InstagramUserSummary> usersFollowing = new ArrayList<InstagramUserSummary>();
		
		while (true) {
			InstagramGetUserFollowersResult followings = instagram.sendRequest(
					new InstagramGetUserFollowingRequest(userResult.getUser().getPk(), nextMaxIdFollowings));
			usersFollowing.addAll(followings.getUsers());
			
			nextMaxIdFollowings = followings.getNext_max_id();
			
			if (nextMaxIdFollowings == null) {
				break;
			}
		}
		return usersFollowing;
	}

}
