package it.joint.microservices.advertise.domain.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import static org.elasticsearch.index.query.MatchQueryBuilder.Operator.AND;

import it.joint.microservices.advertise.domain.model.Advertise;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private ElasticsearchTemplate elasticsearchTemplate;
    
	@Autowired
	public AdvertiseServiceImpl(ElasticsearchTemplate elasticsearchTemplate) {
		this.elasticsearchTemplate = elasticsearchTemplate;
	}

	public List<Advertise> searchAdvertises(String query) {
    	
		log.info("searchAnnunci with query : {}", query);
			
    	SearchQuery searchQuery = new NativeSearchQueryBuilder()
    			  .withQuery(multiMatchQuery(query.toLowerCase()).operator(AND)
    			    .field("title")
    			    .field("content")
    			    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
    			  .build();
    	    	
    	return elasticsearchTemplate.queryForList(searchQuery, Advertise.class);
    }
}