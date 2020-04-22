package rtest;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Service;
@Service
public class RGraph {
	public byte[] returnRImg1() {
		RConnection r = null;
		byte retImg [] = null;
		try {
			r = new RConnection();		
			r.eval("setwd('c:/unico/Rstudy')");
			r.eval("png('test1.png')");
			r.eval("x <- 1:10");
			r.eval("barplot(x, names='SALES', col=rainbow(15))");
			r.eval("dev.off()");
			r.eval("r<-readBin('test.png','raw',1024*1024)");
			r.eval("unlink('test1.png')");
			retImg = r.eval("r").asBytes();
		} catch(Exception e) {
			System.out.println(e);	
			e.printStackTrace();
		} finally {
			r.close();
		}
		return retImg; 
	}
	public byte[] returnRImg2() {
		RConnection r = null;
		byte retImg [] = null;
		try {
			r = new RConnection();		
			r.eval("library(DBI)");
			r.eval("setwd('c:/unico/Rstudy')");
			r.eval("result <- read.table('data/product_click.log')");
			r.eval("names(result) <- c('CLICK_TIME', 'PID')");
			r.eval("data_p <- table(result$PID)");
			r.eval("data_t <- table(substr(result$CLICK_TIME,9,10)) ");
			r.eval("png('test2.png')");
			r.eval("par(mfrow=c(1,2))");
			r.eval("pie(data_p, radius = 1, col=rainbow(10))");
			r.eval("barplot(data_t, col=terrain.colors(10))");
			r.eval("dev.off()");
			r.eval("r=readBin('test2.png','raw',1020*1020)");
			r.eval("unlink('test2.png')");
			retImg = r.eval("r").asBytes();
		} catch(Exception e) {
			System.out.println(e);	
			e.printStackTrace();
		} finally {
			r.close();
		}
		return retImg; 
	}
	
	public byte[] returnRImg3() {
		RConnection r = null;
		byte retImg [] = null;
		try {
			r = new RConnection();	
			r.eval("setwd('c:/unico/Rstudy')");
			r.eval("png('test3.png')");
			r.eval("library(KoNLP)");
			r.eval("library(XML)");
			r.eval("library(rvest)");	
			r.eval("library(wordcloud)");	
			r.eval("imsi <- read_html('http://hankookilbo.com')");
			r.eval("t <- htmlParse(imsi)");
			r.eval("content<- xpathSApply(t,\"//p[@class='title']\", xmlValue)");
			r.eval("content <- gsub('[[:punct:][:cntrl:]]', '', content)");
			r.eval("content <- trimws(content)");			
			r.eval("word <- sapply(content,extractNoun,USE.NAMES=F)");
			r.eval("cdata <- unlist(word)");
			r.eval("cdata <- Filter(function(x) {nchar(x) >= 2} ,cdata)");
			r.eval("wordcount <- table(cdata)");
			r.eval("wordcount <- head(sort(wordcount, decreasing=T),20)");
			r.eval("wordcloud(names(wordcount),freq=wordcount,scale=c(5,1),"+
					  "rot.per=0.35,min.freq=2,random.order=F,random.color=T,colors=rainbow(20))");
			r.eval("dev.off()");
			r.eval("r=readBin('test3.png','raw',1020*1020)");
			r.eval("unlink('test3.png')");
			retImg = r.eval("r").asBytes();			
		} catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close();			
		}
		return retImg;
	}	
}
