package rtest;

import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Repository;

@Repository
public class ROracleDAO2 {
	public String createTable() {
		String retStr = "";
		RConnection r = null;
		try {
			r = new RConnection();
			r.eval("library(DBI)");
			r.eval("library(RJDBC)");
			r.eval("setwd('c:/unico/Rstudy')");
			r.eval("drv <- JDBC('oracle.jdbc.driver.OracleDriver','C:/unico/ojdbc6.jar')");
			r.eval("conn <- dbConnect(drv, 'jdbc:oracle:thin:@localhost:1521:xe','jdbctest','jdbctest')");
			r.eval("student<-read.csv('data/example_studentlist.csv')");
			r.eval("dbWriteTable(conn, 'student', student)");
			retStr = r.eval("'테이블 생성과 데이터 저장 성공.......'").asString();	
		} catch (Exception e) {
			e.printStackTrace();
			try {
				retStr = r.eval("'테이블 생성과 데이터 저장 실패.......'").asString();
			} catch(Exception e1) {
				e.printStackTrace();
			}
		} finally {
			r.close();
		}
		return retStr;
	}

	public String dropTable() {
		String retStr = "";
		RConnection r = null;
		try {
			r = new RConnection();
			r.eval("library(DBI)");
			r.eval("library(RJDBC)");
			r.eval("drv <- JDBC('oracle.jdbc.driver.OracleDriver','C:/unico/ojdbc6.jar')");
			r.eval("conn <- dbConnect(drv, 'jdbc:oracle:thin:@localhost:1521:xe','jdbctest','jdbctest')");
			r.eval("dbRemoveTable(conn, 'STUDENT')");
			retStr = r.eval("'삭제 성공.......'").asString();	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				retStr = r.eval("'삭제 실패.......'").asString();
			} catch(Exception e1) {
				e.printStackTrace();
			}
		} finally {
			r.close();
		}
		return retStr;
	}

	public Object listTable() {
		String retStr = "";
		RConnection r = null;
		try {
			r = new RConnection();
			r.eval("library(DBI)");
			r.eval("library(RJDBC)");
			r.eval("drv <- JDBC('oracle.jdbc.driver.OracleDriver','C:/unico/ojdbc6.jar')");
			r.eval("conn <- dbConnect(drv, 'jdbc:oracle:thin:@localhost:1521:xe','jdbctest','jdbctest')");
			r.eval("query <- 'SELECT * FROM STUDENT'");
			RList list = r.eval("dbGetQuery(conn,query)").asList();
			int cols = list.size();
			int rows = list.at(0).length();
			String[][] s = new String[cols][];
			for (int i = 0; i < cols; i++) {
				s[i] = list.at(i).asStrings();
			}
			for (int j = 0; j < rows; j++) {
				for (int i = 0; i < cols; i++) {
					retStr += (s[i][j]) + "";
				}
				retStr += "</br>";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				retStr = r.eval("'테이블 내용 출력 실패.......'").asString();
			} catch(Exception e1) {
				e.printStackTrace();
			}
		} finally {
			r.close();
		}
		return retStr;
	}

}
