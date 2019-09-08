/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User Summary VO
 * @author Bruno Candido Volpato da Cunha
 *
 */
@Getter
@Setter
@ToString
public class InstagramUserSummary {
    public boolean is_verified;
    public String profile_pic_id;
    public boolean is_favorite;
    public boolean is_private;
    public String username;
    public long pk;
    public String profile_pic_url;
    public boolean has_anonymous_profile_picture;
    public String full_name;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((full_name == null) ? 0 : full_name.hashCode());
		result = prime * result + (has_anonymous_profile_picture ? 1231 : 1237);
		result = prime * result + (is_favorite ? 1231 : 1237);
		result = prime * result + (is_private ? 1231 : 1237);
		result = prime * result + (is_verified ? 1231 : 1237);
		result = prime * result + (int) (pk ^ (pk >>> 32));
		result = prime * result + ((profile_pic_id == null) ? 0 : profile_pic_id.hashCode());
		result = prime * result + ((profile_pic_url == null) ? 0 : profile_pic_url.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstagramUserSummary other = (InstagramUserSummary) obj;
		if (full_name == null) {
			if (other.full_name != null)
				return false;
		} else if (!full_name.equals(other.full_name))
			return false;
		if (has_anonymous_profile_picture != other.has_anonymous_profile_picture)
			return false;
		if (is_favorite != other.is_favorite)
			return false;
		if (is_private != other.is_private)
			return false;
		if (is_verified != other.is_verified)
			return false;
		if (pk != other.pk)
			return false;
		if (profile_pic_id == null) {
			if (other.profile_pic_id != null)
				return false;
		} else if (!profile_pic_id.equals(other.profile_pic_id))
			return false;
		if (profile_pic_url == null) {
			if (other.profile_pic_url != null)
				return false;
		} else if (!profile_pic_url.equals(other.profile_pic_url))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
    
    
    
}