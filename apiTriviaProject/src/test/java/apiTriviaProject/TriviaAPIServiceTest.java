package apiTriviaProject;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import exception.TriviaAPIException;
import services.TriviaAPIService;
import pojoModel.TriviaResponse;

public class TriviaAPIServiceTest {

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

}
