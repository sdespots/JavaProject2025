package pojoModel;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

import exception.TriviaAPIException;
import services.TriviaAPIService;

public class TriviaResponse {

	@JsonProperty("response_code")
	private int responseCode;
	private ArrayList<TriviaRequest> results;

	// Getters

	public int getResponseCode() {
		return responseCode;
	}

	public ArrayList<TriviaRequest> getResults() {
		return results;
	}

	// Setters

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public void setResults(ArrayList<TriviaRequest> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "TriviaResponse{" + "responseCode=" + responseCode + ", results=" + results + '}';
	}


}
