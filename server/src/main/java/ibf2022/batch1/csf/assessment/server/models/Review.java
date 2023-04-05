package ibf2022.batch1.csf.assessment.server.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

// DO NOT MODIFY THIS CLASS
public class Review {
	// display_title
	private String title;
	// mpaa_rating
	private String rating;
	// byline
	private String byline;
	// headline
	private String headline;
	// summary_short
	private String summary;
	// link.url
	private String reviewURL;
	// multimedia.src
	private String image = null;

	private int commentCount = 0;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRating() {
		return this.rating;
	}

	public void setByline(String byline) {
		this.byline = byline;
	}

	public String getByline() {
		return this.byline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setReviewURL(String reviewURL) {
		this.reviewURL = reviewURL;
	}

	public String getReviewURL() {
		return this.reviewURL;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}

	public boolean hasImage() {
		return null != this.image;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	};

	public int getCommentCount() {
		return this.commentCount;
	}

	@Override
	public String toString() {
		return "Review{title:%s, rating:%s}".formatted(title, rating);
	}

	public static Review toReview(JsonObject o) {
		Review review = new Review();
		review.setTitle(o.getString("display_title"));
		review.setRating(o.getString("mpaa_rating"));
		review.setByline(o.getString("byline"));
		review.setHeadline(o.getString("headline"));
		review.setSummary(o.getString("summary_short"));
		JsonObject link = o.getJsonObject("link");
		review.setReviewURL(link.getString("url"));
		if (o.get("multimedia").toString().equals("null")) {
			review.setImage("null");
		} else {
			JsonObject multimedia = o.getJsonObject("multimedia");
			review.setImage(multimedia.getString("src"));
		}
		return review;
	}

	public static List<Review> toReviewList(String o) throws IOException {
		List<Review> reviewList = new LinkedList<Review>();
		try (
				InputStream is = new ByteArrayInputStream(o.getBytes())) {
			JsonReader r = Json.createReader(is);
			JsonObject jsonObject = r.readObject();
			if (jsonObject.get("results").toString().equals("null")) {
				return reviewList;
			} else {
				reviewList = jsonObject.getJsonArray("results")
						.stream()
						.map(v -> (JsonObject) v)
						.map(v -> Review.toReview(v))
						.toList();
				return reviewList;
			}
		}
	}

	public JsonObject toJson() {
		JsonObjectBuilder j = Json.createObjectBuilder()
				.add("title", getTitle())
				.add("rating", getRating())
				.add("byline", getByline())
				.add("headline", getHeadline())
				.add("summary", getSummary())
				.add("review_url", getReviewURL())
				.add("commentcount", getCommentCount())
				.add("image", getImage());
		return j.build();
	}
}
