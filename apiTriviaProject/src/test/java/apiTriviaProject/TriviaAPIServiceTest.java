package apiTriviaProject;

import java.net.URI;
import java.util.ArrayList;
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
import pojoModel.TriviaRequest;
import org.apache.commons.text.StringEscapeUtils;

public class TriviaAPIServiceTest {

	// declare the BASE_URL as a constant
	private static final String BASE_URL = "https://opentdb.com/api.php";

	
    /*Test to verify successful API data retrieval and to ensure that a valid response is returned with a response code of 0*/
	@Test
	public void testGetAPIDataSuccess() throws TriviaAPIException, Exception {

		// Wait for 5 seconds to prevent hitting the API request limit
		Thread.sleep(5000);

		try {

			// Call the API
			TriviaAPIService triviaService = new TriviaAPIService();
			TriviaResponse results = triviaService.getAPIData(5, 9, "easy", "multiple");

			// Check if the response is empty
			Assert.assertNotNull("The response is empty", results);

			// Check if the response code is 0 based on the API Documentation
			Assert.assertTrue("Failure: Expected response code to be 0, but it was " + results.getResponseCode(),
					results.getResponseCode() == 0);

			// Check if the response contains questions
			Assert.assertTrue("The results should contain trivia questions", results.getResults().size() > 0);

		} catch (Exception e) {
			throw new TriviaAPIException("Error occurred while calling the Trivia API", e);
		}
	}

    /*Test to verify that the API correctly throws an exception when the amount is less than 1*/
	@Test
	public void testGetAPIDataAmountLessThanOne() throws TriviaAPIException, Exception {
		try {

			// Create the TriviaAPIService instance
			TriviaAPIService triviaService = new TriviaAPIService();

			// Call the method that should throw an exception
			triviaService.getAPIData(0, 1, "easy", "multiple");

			// If there is no exception, fail the test
			Assert.fail();

		} catch (TriviaAPIException exception) {

			// Check the exception message
			Assert.assertEquals("Amount must be greater than 1!", exception.getMessage());
			System.out.println(exception.getMessage());
		}
	}

	/*Test to verify that the API correctly throws an exception when the amount exceeds 50*/
	@Test
	public void testGetAPIDataAmountLessThan50() throws TriviaAPIException, Exception {
		try {

			// Create the TriviaAPIService instance
			TriviaAPIService triviaService = new TriviaAPIService();

			// Call the method that should throw an exception
			triviaService.getAPIData(51, 1, "easy", "multiple");

			// If there is no exception, fail the test
			Assert.fail();

		} catch (TriviaAPIException exception) {

			// Check the exception message
			Assert.assertEquals("Amount must be less than 50!", exception.getMessage());
			System.out.println(exception.getMessage());
		}
	}

	/*Test to verify that the Trivia API returns an HTTP 200 status code*/
	@Test
	public void testGetAPIHTTPStatusCodeSuccess() throws TriviaAPIException, Exception {

		// Create a URIBuilder object and start building the URL with the base URL
		URIBuilder uriBuilder = new URIBuilder(BASE_URL);
		uriBuilder.addParameter("amount", String.valueOf(5));
		uriBuilder.addParameter("category", String.valueOf(10));
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

	/*Test to verify that HTML-encoded trivia data is correctly decoded*/
	@Test
	public void testHTMLDecoding() throws TriviaAPIException, Exception {

        // Sample HTML encoded trivia data
        String encodedQuestion = "What is the capital of France?";
        String encodedCorrectAnswer = "Paris &amp; the city of lights";
		ArrayList<String> encodedIncorrectAnswers = new ArrayList<>();
        encodedIncorrectAnswers.add("London &amp; the big city");
        encodedIncorrectAnswers.add("Berlin");

		// Create a TriviaRequest object
		TriviaRequest question = new TriviaRequest();
		// Set the encoded values in the TriviaRequest object
		question.setQuestion(encodedQuestion);
		question.setCorrectAnswer(encodedCorrectAnswer);
		question.setIncorrectAnswers(encodedIncorrectAnswers);

		// Decode the HTML content
		String decodedQuestion = StringEscapeUtils.unescapeHtml4(encodedQuestion);
		String decodedCorrectAnswer = StringEscapeUtils.unescapeHtml4(encodedCorrectAnswer);

		// Create an empty list to keep the decoded incorrect answers
		ArrayList<String> decodedIncorrectAnswers = new ArrayList<>();
		for (String answer : encodedIncorrectAnswers) {
			// Decode each incorrect answer string and add it to the list
			decodedIncorrectAnswers.add(StringEscapeUtils.unescapeHtml4(answer));
		}

		// Set decoded values in the TriviaRequest object
		question.setQuestion(decodedQuestion);
		question.setCorrectAnswer(decodedCorrectAnswer);
		question.setIncorrectAnswers(decodedIncorrectAnswers);

        // Verify that the decoded strings contain only readable characters
        Assert.assertEquals("What is the capital of France?", question.getQuestion());
        Assert.assertEquals("Paris & the city of lights", question.getCorrectAnswer());
        Assert.assertEquals("London & the big city", question.getIncorrectAnswers().get(0));
        Assert.assertEquals("Berlin", question.getIncorrectAnswers().get(1));

	}

}
