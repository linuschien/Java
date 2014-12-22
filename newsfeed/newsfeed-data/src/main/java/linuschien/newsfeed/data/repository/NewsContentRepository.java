package linuschien.newsfeed.data.repository;

import linuschien.newsfeed.data.model.NewsContent;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsContentRepository extends MongoRepository<NewsContent, String> {

}
