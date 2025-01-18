package services;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojoModel.TriviaRequest;
import pojoModel.TriviaResponse;
import java.net.URI;

public class TriviaAPIService {
	
	// declare the BASE_URL as a constant 
	private static final String BASE_URL = "https://opentdb.com/api.php";
	
	public static void main(String[] args) {
		try {
			// Call the function
			TriviaResponse triviaResponse = TriviaAPIService.getAPIData(2, "16", "Any", "Any");

			// print response code
			System.out.println("Response Code: " + triviaResponse.getResponseCode());

			for (TriviaRequest question : triviaResponse.getResults()) {
				System.out.println(question);
			}

			// return the category
			for (TriviaRequest question : triviaResponse.getResults()) {
				System.out.println(question.getCategory());
			}

			// print question
			for (TriviaRequest question : triviaResponse.getResults()) {
				System.out.println(question.getQuestion());
			}

			// Print the trivia response
			System.out.println("Response: " + triviaResponse);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	
	// method to get API data 
	public static TriviaResponse getAPIData(int amount, String category, String difficulty, String type) throws Exception {
		
		// Check if amount is greater than 1
	    if (amount < 1) {
	        // Throw custom exception if amount is less than 1
	        throw new Exception("Amount must be greater than 1.");
	    }

	    // Create a URIBuilder object and start building the URL with the base URL
	    URIBuilder uriBuilder = new URIBuilder(BASE_URL);
	    
	    // Add the 'amount' parameter to the URL
	    uriBuilder.addParameter("amount", String.valueOf(amount));

	    // Add other parameters only if they are not equal to "Any"
	    if (!category.equals("Any")) {
	        uriBuilder.addParameter("category", category);
	    }
	    if (!difficulty.equals("Any")) {
	        uriBuilder.addParameter("difficulty", difficulty);
	    }
	    if (!type.equals("Any")) {
	        uriBuilder.addParameter("type", type);
	    }

	    // Convert the URIBuilder object to a URI and then to a string
	    URI uri = uriBuilder.build();
	    String url = uri.toString();

	    // Print the URL
	    System.out.println("URL: " + url);

	    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	        
	        // Create an HTTP GET request with the constructed URL
	        HttpGet request = new HttpGet(url);
	        
	        // Execute the HTTP request and get the response
	        CloseableHttpResponse response = httpClient.execute(request);
	        
	        // Retrieve the HTTP status code of the response
	        int status_code = response.getStatusLine().getStatusCode(); 
	        
	        System.out.println("Protocol version: " + response.getStatusLine().getProtocolVersion());
	        System.out.println("Reason phrase: " + response.getStatusLine().getReasonPhrase());
	        System.out.println("Status code: " + status_code);

	        if (status_code == 200) {

	            System.out.println("The request was successful!");
	            
	            // Retrieve the response entity from the HTTP response (retrieves results)
	            HttpEntity entity = response.getEntity(); 
	            
	            // Check if the response entity is not null (contains results)
	            if (entity != null) {
	            	
	            	//System.out.println(entity.getContent());
	                
	                // Convert the response entity to a JSON string
	                String json = EntityUtils.toString(entity);

	                // Create an ObjectMapper instance for deserializing JSON
	                ObjectMapper mapper = new ObjectMapper();
	                
	                // Deserialize the JSON string into a TriviaResponse object and return it
	                return mapper.readValue(json, TriviaResponse.class);
	                
	            } else {
	                // Throw an exception if the response entity is empty
	                throw new Exception("Empty response from server");
	            }

	        } else {
	            // Throw an exception for non-successful response status
	            throw new Exception("Unexpected response status: " + status_code);
	        }

	    }
		
	}
}
