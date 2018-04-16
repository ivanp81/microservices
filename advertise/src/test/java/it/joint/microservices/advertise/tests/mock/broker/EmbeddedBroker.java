package it.joint.microservices.advertise.tests.mock.broker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.qpid.server.Broker;
import org.apache.qpid.server.BrokerOptions;

public class EmbeddedBroker {

	private static final String DEFAULT_HOME_DIRECTORY = "target/qpid-home";

	public static final String QPID_CONFIG_LOCATION = "src/test/resources/qpid-config.json";
	public static final String QPID_PASSWORD_LOCATION = "src/test/resources/passwd.txt";

    private final Broker broker;
    private final BrokerOptions brokerOptions;
    
    public EmbeddedBroker() throws FileNotFoundException, IOException {
    	
    	broker = new Broker();
    	
    	//Properties properties = new Properties();
    	//properties.load(new FileInputStream(new File(APPLICATION_CONFIG_LOCATION)));
    	//String amqpPort = properties.getProperty("spring.rabbitmq.port");
        
        String homePath = getHomePath();        
        brokerOptions = new BrokerOptions();
        brokerOptions.setConfigProperty("qpid.work_dir", homePath + "/" + DEFAULT_HOME_DIRECTORY + "/work");
        //brokerOptions.setConfigProperty("qpid.amqp_port", amqpPort);
        //brokerOptions.setConfigProperty("qpid.home_dir", homePath);
        brokerOptions.setConfigProperty("qpid.pass_file", homePath + "/" + QPID_PASSWORD_LOCATION);
        brokerOptions.setInitialConfigurationLocation(homePath + "/" + QPID_CONFIG_LOCATION);
    } 

    public void start() throws Exception {
        broker.startup(brokerOptions);
    }

    public void stop() {
        broker.shutdown();
    }
    
    private String getHomePath() {
    	String userDir = System.getProperty("user.dir").toString();
        File file = new File(userDir);
        String homePath = file.getAbsolutePath();
        return homePath;
    }
}