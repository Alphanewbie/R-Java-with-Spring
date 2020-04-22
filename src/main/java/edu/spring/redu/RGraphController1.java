package edu.spring.redu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import rtest.RGraph;
@Controller
public class RGraphController1 {
	@Autowired
	RGraph rg;
		
	@RequestMapping("/graph1")
	public ModelAndView get1() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("img_content", rg.returnRImg1());
		mav.setViewName("rgraph");
		return mav;
	}	
	@RequestMapping("/graph2")
	public ModelAndView get2() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("img_content", rg.returnRImg2());
		mav.setViewName("rgraph");
		return mav;
	}		
	@RequestMapping("/wordcloud1")
	public ModelAndView get3() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("img_content", rg.returnRImg3());
		mav.setViewName("rgraph");
		return mav;
	}
}






