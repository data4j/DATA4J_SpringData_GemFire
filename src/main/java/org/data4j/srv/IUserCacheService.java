package org.data4j.srv;

import org.data4j.user.User;

import com.gemstone.gemfire.cache.Region;

/**
 * IUserCacheService Interface exposes cache functionality.
 * 
 * @author data4j.org
 * @since 29 Sept 2013
 * @version 1.0.0
 *
 */
public interface IUserCacheService {

	/**
     * Adds User entries to cache
     *
     * @param User value 
     *
     */
	void addToUserCache(User user);
	
	/**
     * Removes User entries from cache
     *
     * @param String key
     *
     */
	void removeFromUserCache(String id);
	
	/**
     * Gets User Region
     *
     * @return Region<String, User> region
     */
	Region<String, User> getUserRegion();
	
	
}
