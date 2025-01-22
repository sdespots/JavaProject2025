package apiTriviaProject;

import java.net.URI;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import exception.TriviaAPIException;
import services.TriviaAPIService;
import pojoModel.TriviaResponse;

public class TriviaAPIServiceTest {

	// declare the BASE_URL as a constant
	private static final String BASE_URL = "https://opentdb.com/api.php";
	

	@Test
	public void testGetAPIDataSuccess() throws TriviaAPIException, Exception {

		try {

			// Call the API
			TriviaAPIService triviaService = new TriviaAPIService();
			TriviaResponse results = triviaService.getAPIData(5, "9", "easy", "multiple");

			// Check if the response is empty
			Assert.assertNotNull("The response is empty", results);

			System.out.println("This is a test!");

			// Check if the response code is 0 based on the API Documentation
			Assert.assertTrue("Failure: Expected response code to be 0, but it was " + results.getResponseCode(),
					results.getResponseCode() == 0);

			// Check if the response contains questions
			Assert.assertTrue("The results should contain trivia questions", results.getResults().size() > 0);

		} catch (Exception e) {
			throw new TriviaAPIException("Error occurred while calling the Trivia API", e);
		}
	}

	@Test
	public void testGetAPIDataAmountLessThanOne() throws TriviaAPIException, Exception {
		try {

			// Create the TriviaAPIService instance
			TriviaAPIService triviaService = new TriviaAPIService();

			// Call the method that should throw an exception
			triviaService.getAPIData(0, "9", "easy", "multiple");

			// If there is no exception, fail the test
			Assert.fail();

		} catch (TriviaAPIException exception) {

			// Check the exception message
			Assert.assertEquals("Amount must be greater than 1.", exception.getMessage());
			System.out.println(exception.getMessage());
		}
	}

	@Test
	public void testGetAPIHTTPStatusCodeSuccess() throws TriviaAPIException, Exception {

		// Create a URIBuilder object and start building the URL with the base URL
		URIBuilder uriBuilder = new URIBuilder(BASE_URL);
		uriBuilder.addParameter("amount", String.valueOf(5));
		uriBuilder.addParameter("category", "10");
		uriBuilder.addParameter("difficulty", "Medium");
		uriBuilder.addParameter("type", "Any");

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
			Assert.assertEquals("Expected HTTP status code to be 200", 200, status_code);

		} catch (Exception e) {
			throw new TriviaAPIException("Error occurred while calling the Trivia API", e);
		}
	}

}
