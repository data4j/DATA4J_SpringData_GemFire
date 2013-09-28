package org.data4j.exe;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.data4j.srv.IUserCacheService;
import org.data4j.user.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;


/**
 * CacheUpdater Class updates cache and logs entries...
 * 
 * @author data4j.org
 * @since 29 Sept 2013
 * @version 1.0.0
 *
 */
public class CacheUpdater implements Runnable, BeanFactoryAware {
	
	private static Logger log = Logger.getLogger(CacheUpdater.class);
	
	private IUserCacheService userCacheService;
	
	private BeanFactory beanFactory;
	
	/**
     * Adds entries to the cache and logs...
     *
     */
	public void run() {		
		
		//New Users are created for the first member of the cluster... 
		User firstUser = createUser();
		firstUser.setId("1");
		firstUser.setName("Bruce");
		firstUser.setSurname("Willis");
		
		User secondUser = createUser();
		secondUser.setId("2");
		secondUser.setName("Russell");
		secondUser.setSurname("Crowe");
		
		//First Entry is added to user-cache...
		userCacheService.addToUserCache(firstUser);
		
		//Second Entry is added to user-cache...
		userCacheService.addToUserCache(secondUser);

		//Cache Entries are being printed...
		printCacheEntries();		
	}
	
	/**
     * Prints entries of user-cache...
     *
     */
	private void printCacheEntries() {
		Collection<User> userCollection = null;
		try {
			while(true) {
				userCollection = (Collection<User>)userCacheService.getUserRegion().values();
				for(User user : userCollection) {
					log.debug("User Cache Content on first Member : " + user);
				}
				Thread.sleep(30_000);			
			}
		} catch (InterruptedException e) {
			log.error(e);
		}
	}
	
	/**
     * Creates new user
     *
     * @return User user
     */
	private User createUser() {
		return beanFactory.getBean(User.class);
	}
	
	public void setUserCacheService(IUserCacheService userCacheService) {
		this.userCacheService = userCacheService;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
}
