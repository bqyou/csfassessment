package ibf2022.batch1.csf.assessment.server.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;

@Repository
public class MovieRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	// db.comments.find({"title": "title"}).count();

	public int countComments(String title) {
		Criteria c = Criteria.where("title").is(title);
		Query count = Query.query(c);
		Long total = mongoTemplate.count(count, "comments");
		int i = total.intValue();
		return i;
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	// db.comments.insertOne({
	// title:"title",
	// name:"name",
	// rating:"rating",
	// comment: "comment"
	// })

	public void insertComment(Comment c) {
		Document d = c.toDocument();
		mongoTemplate.insert(d, "comments");
	}
}
