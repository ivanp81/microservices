package it.joint.microservices.advertise.tests.mock.broker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.qpid.server.Broker;
import org.apache.qpid.server.BrokerOptions;

import com.google.common.io.Files;

public class EmbeddedBrokerManager {

	//@Value("${spring.rabbitmq.port}")
	//private  String rabbitmqPort;

	public static final String QPID_CONFIG_LOCATION = "src/test/resources/qpid-config.json";
	public static final String QPID_PASSWORD_LOCATION = "src/test/resources/passwd.txt";
	//public static final String APPLICATION_CONFIG_LOCATION = "src/test/resources/application.properties";

    private final Broker broker;
    private final BrokerOptions brokerOptions;
    
    public EmbeddedBrokerManager() throws FileNotFoundException, IOException {
    	
    	broker = new Broker();
    	
    	//Properties properties = new Properties();
    	//properties.load(new FileInputStream(new File(APPLICATION_CONFIG_LOCATION)));
    	//String amqpPort = properties.getProperty("spring.rabbitmq.port");
        
    	File tmpFolder = Files.createTempDir();
        String userDir = System.getProperty("user.dir").toString();
        File file = new File(userDir);
        String homePath = file.getAbsolutePath();
        
        brokerOptions = new BrokerOptions();
        brokerOptions.setConfigProperty("qpid.work_dir", tmpFolder.getAbsolutePath());
        //brokerOptions.setConfigProperty("qpid.amqp_port", amqpPort);
        //brokerOptions.setConfigProperty("qpid.home_dir", homePath);
        brokerOptions.setConfigProperty("qpid.pass_file", homePath + "/" + QPID_PASSWORD_LOCATION);
        brokerOptions.setInitialConfigurationLocation(homePath + "/" + QPID_CONFIG_LOCATION);
        
        System.out.println("BROKER MANAGER CREATED");
    } 

    public void startBroker() throws Exception {
        broker.startup(brokerOptions);
    }

    public void stopBroker() {
        broker.shutdown();
    }
}