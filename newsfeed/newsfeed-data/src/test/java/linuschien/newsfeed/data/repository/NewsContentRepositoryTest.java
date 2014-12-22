package linuschien.newsfeed.data.repository;

import java.util.Date;
import java.util.List;

import linuschien.newsfeed.data.model.NewsContent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class NewsContentRepositoryTest {

	@Autowired
	private NewsContentRepository newsContentRepository;

	@Test
	public void test() {
		NewsContent newsContent = new NewsContent(null, "Test", "Test by Linus", new Date(), "https://github.com/linuschien");
		newsContent = newsContentRepository.save(newsContent);
		Assert.assertNotNull(newsContent.getId());

		List<NewsContent> newsContents = newsContentRepository.findAll();
		for (NewsContent content : newsContents) {
			System.out.println(content.toString());
		}
	}

}
