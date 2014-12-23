package linuschien.newsfeed.web.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import linuschien.newsfeed.data.model.NewsContent;
import linuschien.newsfeed.web.controller.AtomController;

import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;

public class AtomView extends AbstractAtomFeedView {

	@Override
	protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<NewsContent> newsContents = (List<NewsContent>) model.get(AtomController.ATOM);
		List<Entry> entries = new LinkedList<Entry>();
		for (NewsContent content : newsContents) {
			Entry entry = new Entry();
			entry.setPublished(content.getPublishDate());
			entry.setTitle(content.getTitle());

			Content atomContent = new Content();
			atomContent.setValue(content.getValue());
			atomContent.setType(Content.TEXT);
			entry.setSummary(atomContent);

			entries.add(entry);
		}
		return entries;
	}

}
