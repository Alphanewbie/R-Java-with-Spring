package edu.spring.redu;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class JavaRLab2 {
	
	public static void getHotel() throws RserveException, REXPMismatchException {

		RConnection rc = new RConnection();

		rc.eval("library(KoNLP)");
		rc.eval("y <- readLines(\"C:/Alpha/Rstudy/data/hotel.txt\")");
		rc.eval("y<-gsub(\"[^가-힣]\", \" \",y)");

		rc.eval("y2 <- sapply(y, extractNoun, USE.NAMES = F)");
		rc.eval("y2 <- gsub(\"[::space::]+\", \" \",y)");
		rc.eval("y3<- extractNoun(y2)");

		rc.eval("y3 <- Filter(function(x) {nchar(x) >= 2}, y3)");
		rc.eval("yTable <- table(unlist(y3))");
		rc.eval("yTable <- sort(yTable, decreasing = T)");

		REXP x = rc.eval("result <- data.frame(head(yTable, 10))");
		
		RList list = x.asList();
		int v_size = list.size();
		// 관측치의 갯수
		int d_length = list.at(0).length();
		

		int arrayRows = v_size;
		int arrayCols = d_length;
		String[][] s = new String[arrayRows][]; // 데이터프레임의 변수 갯수로 행의 크기를 정한다.

		for (int i = 0; i < arrayRows; i++) {
			s[i] = list.at(i).asStrings();
		}

		System.out.println("R 이 보내온 최빈 명사들 : ");

		for (int j = 0; j < arrayCols; j++) {
			System.out.println(s[0][j] + " : " + s[1][j]);
		}
		
		rc.close();
	}
	
	public static void main(String[] args) throws REXPMismatchException, REngineException {

		JavaRLab2.getHotel();


	}
}
