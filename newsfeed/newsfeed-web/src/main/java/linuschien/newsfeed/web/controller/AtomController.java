package linuschien.newsfeed.web.controller;

import linuschien.newsfeed.data.repository.NewsContentRepository;
import linuschien.newsfeed.web.view.AtomView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/atom")
public class AtomController {

	public final static String ATOM = "atomFeed";

	@Autowired
	private NewsContentRepository newsContentRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() {

		ModelAndView modelView = new ModelAndView();
		modelView.setView(new AtomView());
		modelView.addObject(ATOM, newsContentRepository.findAll());

		return modelView;
	}

}
