package gobeyond.hazelcast.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HazelcastClient implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		
		HazelcastInstance hz = com.hazelcast.client.HazelcastClient.newHazelcastClient();
		
		log.info("hz :: "+hz.getName() +" Group Name "+hz.getConfig().getGroupConfig().getName());
		
		IMap<Object, Object> maps = hz.getMap("gebeyond-map");
		
		log.info("maps size ::"+maps.size());
		
		log.info("maps ::"+maps);
		
		
		IMap<Object, Object> orderMap = hz.getMap("order-map");
		
		Map<String,String> orderDetails = new HashMap<>();
		
		orderDetails.put("2378472", "started");
		
		orderMap.put(hz.getLocalEndpoint().getUuid(), orderDetails);
		
		orderDetails.put("123546", "Inprogress");
		
		orderMap.put(hz.getLocalEndpoint().getUuid(), orderDetails);
	}

}
