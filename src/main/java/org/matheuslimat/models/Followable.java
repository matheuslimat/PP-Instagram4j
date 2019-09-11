package org.matheuslimat.models;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

public interface Followable {

	public List<InstagramUserSummary> getFollowers(InstagramSearchUsernameResult userResult, Instagram4j instagram)
			throws ClientProtocolException, IOException;

	public List<InstagramUserSummary> getFollowing(InstagramSearchUsernameResult userResult, Instagram4j instagram)
			throws ClientProtocolException, IOException;
	
}
