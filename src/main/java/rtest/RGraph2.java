package rtest;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Service;

@Service
public class RGraph2 {
	
	public byte[] returnRImg3() {
		RConnection r = null;
		byte retImg[] = null;
		try {
			r = new RConnection();
			r.eval("setwd('c:/unico/Rstudy')");
			r.eval("library(ggplot2)");
			r.eval("product_click <-read.table('data/product_click.log')");
			r.eval("colnames(product_click)<- c('date','id')");
			r.eval("color_cnt <- unique(product_click$id)");

			r.eval("chart<-ggplot(data=product_click,aes(x=id))+geom_bar(fill=rainbow(length(color_cnt)))");
			r.eval("ggsave('clicklog.png', chart)");
			r.eval("r=readBin('clicklog.png','raw',1024*1024)");
			r.eval("unlink('clicklog.png')");
			retImg = r.eval("r").asBytes();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
		return retImg;
	}
	public String returnPlotly() {
		RConnection r = null;
		String retStr = "";
		try {		
			r = new RConnection();		
			r.eval("library(plotly)");		
			r.eval("library(ggplot2)");
			r.eval("library(htmltools)");		
			r.eval("p <- ggplot(data = mpg, aes(x = displ, y = hwy, col = drv)) + geom_point()");
			r.eval("my_plotly <- ggplotly(p)");
			r.eval("my_path <- renderTags(my_plotly)");
			retStr = r.eval("my_path$html").asString();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
		System.out.println(retStr);
		return retStr;
	}

	public String returnWC2_1() {
		RConnection r = null;
		String retStr = "";
		try {
			r = new RConnection();
			r.eval("library(wordcloud2)");		
			r.eval("library(htmltools)");			
			r.eval("my_cloud <- wordcloud2(demoFreq, size = 0.5, color = 'random-light', )");
			r.eval("my_path <- renderTags(my_cloud)");
			retStr = r.eval("my_path$html").asString();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
		System.out.println(retStr);
		return retStr;
	}
	public String returnWC2_2(String name) {
		RConnection r = null;
		String retStr = "";
		try {
			r = new RConnection();
			r.assign("filename", name);
			r.eval("source('c:/unico/Rstudy/wordcloud2.R')");		
			retStr = r.eval("my_path$html").asString();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
		System.out.println(retStr);
		return retStr;
	}
}
