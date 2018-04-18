package it.joint.microservices.advertise.tests.mock.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

public class EmbeddedElasticsearch {

	private static final String DEFAULT_HOME_DIRECTORY = "target/elasticsearch-home";

	private final Node node;

	public EmbeddedElasticsearch() {
		this(DEFAULT_HOME_DIRECTORY);
	}

	public EmbeddedElasticsearch(String homeDirectory) {

		Settings.Builder elasticsearchSettings = Settings.builder().put("path.home", homeDirectory);

		this.node = NodeBuilder.nodeBuilder().local(true).settings(elasticsearchSettings.build()).node();
	}

	public void startNode() {
		node.start();
	}

	public Client getClient() {
		return node.client();
	}

	public void stopNode() {
		node.close();
	}
}