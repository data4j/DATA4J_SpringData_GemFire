package org.data4j.srv;

import org.apache.log4j.Logger;
import org.data4j.exe.CacheUpdater;
import org.data4j.user.User;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheTransactionManager;
import com.gemstone.gemfire.cache.CommitConflictException;
import com.gemstone.gemfire.cache.Region;

/**
 * UserCacheService Class is implementation of IUserCacheService Interface.
 * 
 * @author data4j.org
 * @since 29 Sept 2013
 * @version 1.0.0
 *
 */
public class UserCacheService implements IUserCacheService {
	
	private static final Logger log = Logger.getLogger(CacheUpdater.class);
	
	private Cache userCache;
	
	/**
     * Adds Customer entries to cache
     *
     * @param String key
     * @param User value 
     *
     */
	public void addToUserCache(User user) {
		CacheTransactionManager cacheTransactionManager = null;
		try {
			cacheTransactionManager = userCache.getCacheTransactionManager();
			cacheTransactionManager.begin();
			getUserRegion().put(user.getId(), user);
			cacheTransactionManager.commit();
		} catch (CommitConflictException e) {
			cacheTransactionManager.rollback();
			log.error(e);
		}		
	}

	/**
     * Removes User entries from cache
     *
     * @param String id
     *
     */
	public void removeFromUserCache(String id) {
		CacheTransactionManager cacheTransactionManager = null;
		try {
			cacheTransactionManager = userCache.getCacheTransactionManager();
			cacheTransactionManager.begin();
			getUserRegion().remove(id);
			cacheTransactionManager.commit();
		} catch (CommitConflictException e) {
			cacheTransactionManager.rollback();
			log.error(e);
		}		
	}
	
	/**
     * Gets User Region
     *
     * @return Region<String,User> region
     */
	public Region<String, User> getUserRegion() {
		return userCache.getRegion("root/userRegion");
	}

	public void setUserCache(Cache userCache) {
		this.userCache = userCache;
	}
	
}
