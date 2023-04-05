package ibf2022.batch1.csf.assessment.server.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Review;;

@Service
public class MovieService {

	@Value("${apiKey}")
	private String apiKey;

	private String apiUrl = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) throws IOException {

		String apiUri = UriComponentsBuilder.fromUriString(apiUrl)
				.queryParam("query", query)
				.queryParam("api-key", apiKey)
				.toUriString();

		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = null;
		resp = template.getForEntity(apiUri, String.class);

		List<Review> reviewList = Review.toReviewList(resp.getBody());
		return reviewList;
	}

}
