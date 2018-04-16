package it.joint.microservices.advertise.componentTests.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

public class EmbeddedElasticsearchServer {

    private static final String DEFAULT_HOME_DIRECTORY = "target/elasticsearch-home";

    private final Node node;

    public EmbeddedElasticsearchServer() {	
        this(DEFAULT_HOME_DIRECTORY);
    }

    public EmbeddedElasticsearchServer(String homeDirectory) {

        Settings.Builder elasticsearchSettings = Settings.builder()
        											.put("path.home", homeDirectory);

        this.node = NodeBuilder.nodeBuilder()
        			.local(true)
                    .settings(elasticsearchSettings.build())
                    .node();
        
        start();
    }

    public void start() {
    	this.node.start();
    }

    public Client getClient() {
        return node.client();
    }

    public void shutdown() {    	
        node.close();
    }
}