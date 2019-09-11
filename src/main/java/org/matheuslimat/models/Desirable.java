package org.matheuslimat.models;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

public interface Desirable {

	public Set<InstagramUserSummary> getFans(List<InstagramUserSummary> followers, List<InstagramUserSummary> following)
			throws ClientProtocolException, IOException;
	
	public void followAllByUsername (String userName, Instagram4j instagram) throws ClientProtocolException, IOException, InterruptedException;
	
}
