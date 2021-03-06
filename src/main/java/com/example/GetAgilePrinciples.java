package com.example;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.client.builder.AwsClientBuilder;


public class GetAgilePrinciples {
	
	public String handleRequest(Object input, Context context) {
		
		String principles = "";
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder
				.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://dynamodb.us-east-2.amazonaws.com", "us-east-2"))
				.build();
		
		DynamoDB dynamoDB = new DynamoDB(client); 
		Table table = dynamoDB.getTable("Agile");
		GetItemSpec spec = new GetItemSpec()
				.withPrimaryKey("key", "principles");
		
		Item outcome = table.getItem(spec);
    	principles = outcome.toJSONPretty();
		
		return principles;
	}

}
