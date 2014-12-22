package linuschien.newsfeed.data.repository;

import linuschien.newsfeed.data.model.NewsContent;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "contents", path = "contents")
public interface NewsContentRepository extends MongoRepository<NewsContent, String> {

}
