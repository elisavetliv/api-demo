package com.demo.model.data;

import java.io.IOException;
import java.util.Collections;



import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;


public class ElasticsearchData{
	//172.31.14.167
	//3.125.160.49
	private final RestClient restClient = RestClient.builder(
		       new HttpHost("localhost", 9200, "http")).build();
	

	
		 
	public void postData(String country, String cost) {
		HttpEntity entity = new NStringEntity(
				 "{\n" +
				 "    \"country\" : \""+country +"\",\n" +                                      
				 "    \"cost\" : \""+cost+"\"\n" +
				 "}", ContentType.APPLICATION_JSON);
				 
		 try {
					Response indexResponse = restClient.performRequest(
					 "POST",
					 "/clearing-cost/post",
					 Collections.<String, String>emptyMap(),
					 entity);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public String getData(String country) {
		
		HttpEntity entity1 = new NStringEntity(
				 "{\n" +
				"    \"query\" : {\n" +
				"    \"match\": { \"country\":\""+country+"\"} \n" +
				"    } \n"+
				"}", ContentType.APPLICATION_JSON);
				                                                               
		Response response;
		try {
			response = restClient.performRequest("GET", "/clearing-cost/_search?filter_path=hits.hits._source",
					Collections.singletonMap("pretty", "true"),entity1);
			
			return fixResponse(EntityUtils.toString(response.getEntity()));
		} catch (IOException e) {
			e.printStackTrace();
			return "Error";
		}
		
		
				
	}
	
	public void putData(String country, String newCost) {
		HttpEntity entity1 = new NStringEntity(
				 "{\n" + 
				 "    \"script\" : {\n" + 
				 "      \"source\": \"ctx._source.cost=\'"+newCost+"\'\",\n" + 
				 "      \"lang\": \"painless\"  \n" + 
				 "    },\n" + 
				 "    \"query\": {\n" + 
				 "        \"match\" : {\n" + 
				 "            \"country\": \""+country+"\" \n" + 
				 "        }\n" + 
				 "    }\n" + 
				 "}", ContentType.APPLICATION_JSON);
		
		Response response;
		try {
			response = restClient.performRequest("POST", "/clearing-cost/_update_by_query",
					Collections.singletonMap("pretty", "true"),entity1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void deleteData(String country) {
		HttpEntity entity1 = new NStringEntity(
				 "{\n" +
				"    \"query\" : {\n" +
				"    \"match\": { \"country\":\""+country+"\"} \n" +
				"    } \n"+
				"}", ContentType.APPLICATION_JSON);
				                                                               
		Response response;
		try {
			response = restClient.performRequest("POST", "/clearing-cost/_delete_by_query",
					Collections.singletonMap("pretty", "true"),entity1);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private  String fixResponse(String response) {
		return  response.replace("\n", "").replace(" ", "").split("\"_source\":")[1].split("}]")[0];
		
	}
	

		 

}
	 
	