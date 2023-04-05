package ibf2022.batch1.csf.assessment.server.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@Controller
@RequestMapping(path = "/api")
public class MovieController {

	@Autowired
	private MovieService movieSvc;

	@Autowired
	private MovieRepository movieRepo;

	// TODO: Task 3, Task 4, Task 8

	@GetMapping(path = "/search")
	@ResponseBody
	public ResponseEntity<String> getReviews(@RequestParam String query) throws IOException {
		List<Review> reviews = movieSvc.searchReviews(query);
		for (Review review : reviews) {
			review.setCommentCount(movieRepo.countComments(review.getTitle()));
		}
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (Review review : reviews) {
			arrBuilder.add(review.toJson());
		}
		JsonArray result = arrBuilder.build();
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(result.toString());
	}

	@PostMapping(path = "/comment")
	@ResponseBody
	public ResponseEntity<String> postComment(@RequestBody Comment comment) {
		movieRepo.insertComment(comment);
		return ResponseEntity.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body("OK");
	}

}
