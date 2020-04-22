package edu.spring.redu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import rtest.ROracleDAO1;
import rtest.ROracleDAO2;
@Controller
public class RDBController {
	@Autowired
	ROracleDAO1 rdb1;
	@Autowired
	ROracleDAO2 rdb2;
	@RequestMapping("/visitorAll")
	public ModelAndView get1() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb1.returnDBData(1));
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/visitorAllOrderByName")
	public ModelAndView get2() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb1.returnDBData(2));
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/visitorByName")
	public ModelAndView get3(String name) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb1.returnDBData("\\'"+name+"\\'"));
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/insertVisitor")
	public ModelAndView proc(String name, String content) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb1.insertDBData("\\'"+name+"\\'", "\\'"+content+"\\'"));
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/createTable")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb2.createTable());
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/dropTable")
	public ModelAndView drop() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb2.dropTable());
		mav.setViewName("rOracleView");
		return mav;
	}	
	@RequestMapping("/listTable")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", rdb2.listTable());
		mav.setViewName("rOracleView");
		return mav;
	}	
}






