package edu.spring.redu;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import rtest.RGraph2;
import rtest.RLeaflet;
@Controller
public class RGraphController2 {
	@Autowired
	RGraph2 rg2;	
	@Autowired
	RLeaflet rl;

	@RequestMapping("/ggplot2")
	public ModelAndView get4() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("img_content", rg2.returnRImg3());
		mav.setViewName("rgraph");
		return mav;
	}	

	@RequestMapping("/plotly")
	public ModelAndView get5() {
		ModelAndView mav = new ModelAndView();
		String result = rg2.returnPlotly();
		System.out.println(result);
		mav.addObject("dynamic_content", result);
		mav.setViewName("plotlyview");
		return mav;
	}
	
	@RequestMapping("/wordcloud2_1")
	public ModelAndView get6() {
		ModelAndView mav = new ModelAndView();
		String result = rg2.returnWC2_1();
		System.out.println(result);
		mav.addObject("dynamic_content", result);
		mav.setViewName("wc2view");
		return mav;
	}
	
	@RequestMapping("/wordcloud2_2")
	public ModelAndView get7(String name) {
		ModelAndView mav = new ModelAndView();
		String result = rg2.returnWC2_2(name);
		System.out.println(result);
		mav.addObject("dynamic_content", result);
		mav.setViewName("wc2view");
		return mav;
	}
	@RequestMapping("/leaflet1")
	public ModelAndView get8(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String real_path = req.getSession().getServletContext().getRealPath("/");
		System.out.println(real_path);
	    real_path = real_path.replace("\\", "/");
	    System.out.println(real_path);		
		File f = new File(real_path+"/resources/leafletchart1");
		if(!f.exists()) f.mkdir();
		String type = req.getParameter("type");
		if(type == null)
			type = "종합";
		String result = rl.returnLeaflet1(real_path+"/resources/leafletchart1", type);
		mav.addObject("leafletChartName", "http://localhost:8000/redu/resources/leafletchart1/"+result);
		mav.setViewName("leafletview");
		return mav;
	}	
	@RequestMapping("/leaflet2")
	public ModelAndView get9(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String real_path = req.getSession().getServletContext().getRealPath("/");
		System.out.println(real_path);
	    real_path = real_path.replace("\\", "/");
	    System.out.println(real_path);		
		File f = new File(real_path+"/resources/leafletchart2");
		if(!f.exists()) f.mkdir();
		String result = rl.returnLeaflet2(real_path+"/resources/leafletchart2");
		mav.addObject("leafletChartName", "http://localhost:8000/redu/resources/leafletchart2/"+result);
		mav.setViewName("leafletview");
		return mav;
	}	
	@RequestMapping("/leaflet3")
	public ModelAndView get10(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String real_path = req.getSession().getServletContext().getRealPath("/");
		System.out.println(real_path);
	    real_path = real_path.replace("\\", "/");
	    System.out.println(real_path);		
	   	File f = new File(real_path+"/resources/leafletchart3");
		if(!f.exists()) f.mkdir();
		String result = rl.returnLeaflet3(real_path+"/resources/leafletchart3");
		mav.addObject("leafletChartName", "http://localhost:8000/redu/resources/leafletchart3/"+result);
		mav.setViewName("leafletview");
		return mav;
	}

}






