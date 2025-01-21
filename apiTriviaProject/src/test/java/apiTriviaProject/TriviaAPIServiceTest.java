package apiTriviaProject;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import services.TriviaAPIService;
import pojoModel.TriviaResponse;

public class TriviaAPIServiceTest {

    @Test
    void testGetAPIDataSuccess() throws Exception {
    	
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
    }
}

