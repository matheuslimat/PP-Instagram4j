package org.matheuslimat.models;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

public interface Undesirable {

	public Set<InstagramUserSummary> getUnfollowers(InstagramSearchUsernameResult userResult, Instagram4j instagram)
			throws ClientProtocolException, IOException;

	public void removeAllUnfollowers(Set<InstagramUserSummary> unfollowers,
			Instagram4j instagram, Integer timeSleepUnfollow)
			throws ClientProtocolException, IOException, InterruptedException;
	
	public void removeAllUnfollowers(Set<InstagramUserSummary> unfollowers,
			Instagram4j instagram, Integer timeSleepUnfollow, Integer numberFollowers)
			throws ClientProtocolException, IOException, InterruptedException;

}
