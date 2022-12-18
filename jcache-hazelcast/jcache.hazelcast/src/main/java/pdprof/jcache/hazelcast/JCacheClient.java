package pdprof.jcache.hazelcast;

import java.util.ArrayList;
import java.util.Iterator;

import javax.cache.Cache;
import javax.cache.Cache.Entry;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import com.hazelcast.cache.HazelcastCachingProvider;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICacheManager;

public class JCacheClient {

	public static void main(String[] args) {

		String address = "localhost:5701";
		String app = "default_host%2Fhttpsession";
		String deleteid = "dummy_session_id";
		if (args.length > 0) {
			address = args[0]; 
		} 
		if (args.length > 1) {
			app = args[1];
		} 
		if (args.length > 2) {
			deleteid = args[2];
		}
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setClusterName("hello-world");
		clientConfig.getNetworkConfig().addAddress(address);
		HazelcastInstance instance = HazelcastClient.newHazelcastClient(clientConfig);
		
		// https://docs.hazelcast.com/imdg/4.2/jcache/icache
		// https://github.com/OpenLiberty/open-liberty/blob/integration/dev/com.ibm.ws.session.cache/src/com/ibm/ws/session/store/cache/CacheHashMap.java

		CachingProvider cachingProvider = Caching.getCachingProvider();
		
		CacheManager cacheManager =  cachingProvider.getCacheManager(null, null, HazelcastCachingProvider.propertiesByInstanceItself(instance));

		Cache<String, ArrayList> meta = cacheManager.getCache("com.ibm.ws.session.meta." + app, String.class, ArrayList.class);
		Cache<String, byte[]> attr = cacheManager.getCache("com.ibm.ws.session.attr." + app, String.class, byte[].class);
		for (String name : cacheManager.getCacheNames()) {
			System.out.println("Cache name: " + name);
		}
		
		Iterator it = meta.iterator();
		while(it.hasNext()) {
			Cache.Entry<String, ArrayList> entry = (Entry<String, ArrayList>)it.next();
			System.out.println("Key: " + entry.getKey());
			for(Object o: entry.getValue()) {
				if (o != null)
					System.out.println(o.toString());
				else 
					System.out.println(o);
			}
		}
		
		it = attr.iterator();
		while(it.hasNext()) {
			Cache.Entry<String, byte[]> entry = ((Entry<String, byte[]>)it.next());
			System.out.println("Key: " + entry.getKey());
		}
		

		System.out.println("Try to remove " + deleteid + " from meta cache.");
		if(meta.remove(deleteid)) {
			System.out.println(deleteid + " session is removed from meta cache." );
			it = attr.iterator();
			while(it.hasNext()) {
				Cache.Entry<String, byte[]> entry = ((Entry<String, byte[]>)it.next());
				if(entry.getKey().startsWith(deleteid)) {
					if (attr.remove(entry.getKey())) {
						System.out.println(entry.getKey() + " attr is removed from attr cache.");
					}
				}
			}
		} else {
			System.out.println(deleteid + " session is not found in meta cache.");
		}
		
		System.exit(0);
		
	}

}