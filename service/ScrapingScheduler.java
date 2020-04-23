package service;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScrapingScheduler {

	RConnection r = null;

//	@Scheduled(fixedDelay = 3000000) // 10초간격으로
	@Scheduled(fixedDelay = 10000)
	public void scrapingrun() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String retStr = "";
		BufferedOutputStream bs = null;
		
		try(FileWriter  writer = new FileWriter("C:/Alpha/output2.txt",true);) {
			r = new RConnection();
			r.eval("aa<-source('C:/Alpha/Rstudy/daumnews_schedule.R', encoding=\"utf-8\")");
			REXP x = r.eval("aa$value");
			
			RList list = x.asList();
			
			int cols = list.size();
			int rows = list.at(0).length();
			String[][]s = new String[cols][];
			for(int i=0; i<cols; i++) {
				s[i] = list.at(i).asStrings();				
			}
			for(int j=0; j<rows; j++) {
				for(int i=0; i<cols; i++) {
					retStr += (s[i][j])+"";
				}
				retStr += "\n";
			}	
			
			writer.write(retStr);  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			r.close();
		}
		System.out.println(new java.util.Date() + "스케줄 실행:" + dateFormat.format(calendar.getTime()));

	}

}
