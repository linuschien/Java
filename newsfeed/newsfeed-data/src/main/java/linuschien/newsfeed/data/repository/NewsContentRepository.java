package linuschien.newsfeed.data.repository;

import java.util.List;

import linuschien.newsfeed.data.model.NewsContent;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "contents", path = "contents")
public interface NewsContentRepository extends MongoRepository<NewsContent, String> {

	List<NewsContent> findByTitle(@Param("title") String title);

}
