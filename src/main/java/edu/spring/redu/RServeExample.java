package edu.spring.redu;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RServeExample {
	// 버전 정보 리턴
	public static void getString() throws RserveException, REXPMismatchException {
		RConnection rc = new RConnection();
		String s = "가나다";
		// R의 객체명을 준다. R에 만들 객체는 x, 값은 s다.
		// 자바의 객체를 R에 전달하는 역활
		rc.assign("x", s);
		// "" 안에 있는건 R코드
		// y라는 객체를 만들면서 s값을 넣겠다.
		rc.eval("y<- '" + s + "'");
		// 위와 아래는 같다.
		
		// 이건 R서브에 출력된다. 즉, R의 값을 확인 하고 싶을때, cmd창에서 확인하자.
		rc.eval("if(x == '가나다') print('XXX')");
		rc.eval("if(y == '가나다') print('YYY')");
		
		// R에서 한글을 보내고 싶을때는 2가지 방법
		// asing은 이걸로 인코딩
		rc.eval("Encoding(x)<- 'UTF-8'");
		// eval로 값 할당 했으면 이 걸로 인코딩
		rc.eval("y<-iconv(y, 'CP949', 'UTF-8')");
		// R의 버전 정보를 문자열로 리턴
		// REXP객체로 리턴 받는다
		// R 버전 정보 : R version 3.6.3 (2020-02-29) 가나다 가나다
		REXP x = rc.eval("paste(R.version.string,x,y)");
		System.out.println("R 버전 정보 : " + x.asString());
		rc.close();
	}

	// 와부에서 받아오는 것으모 예외 처리 해줘어야 한다.
	public static void getInteger() throws RserveException, REXPMismatchException {
		RConnection rc = new RConnection();
		REXP x = rc.eval("length(LETTERS)");
		System.out.println("알파벳 갯수 : " + x.asInteger());
		rc.close();
	}

	public static void getDoubles() throws RserveException, REXPMismatchException {
		RConnection rc = new RConnection();
		REXP x = rc.eval("rnorm(20)");
		double[] d = x.asDoubles();
		for (int i = 0; i < d.length; i++) {
			System.out.println(d[i]);
		}
		rc.close();
	}

	public static void getIntegers() throws REngineException, REXPMismatchException {
		RConnection rc = new RConnection();
		int[] dataX = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		rc.assign("x", dataX);
		rc.eval("y <- x + 10");
		//R에서 변수값 출력하는 것처럼 y값을 출력해서 resultX에 저장한다
		int[] resultX = rc.eval("y").asIntegers();
		for (int i = 0; i < resultX.length; i++) {
			System.out.println(resultX[i]);
		}
		rc.close();
	}

	public static void getDataFrame1() throws RserveException, REXPMismatchException {
		RConnection rc = new RConnection();
		REXP x = rc.eval("d<-data.frame(LETTERS[11:20],c(11:20), stringsAsFactors=F)");
		// 변수의 네이밍
		RList list = x.asList();
		int v_size = list.size();
		// 관측치의 갯수
		int d_length = list.at(0).length();
		System.out.println("데이터(관측치)의 갯수 : " + d_length);
		System.out.println("변수의 갯수 : " + v_size);

		int arrayRows = v_size;
		int arrayCols = d_length;
		String[][] s = new String[arrayRows][]; // 데이터프레임의 변수 갯수로 행의 크기를 정한다.

		for (int i = 0; i < arrayRows; i++) {
			s[i] = list.at(i).asStrings();
		}

		for (int i = 0; i < arrayRows; i++) {
			for (int j = 0; j < arrayCols; j++) {
				System.out.print(s[i][j] + "\t");
			}
			System.out.println();
		}
		rc.close();
	}

	public static void getDataFrame2() throws RserveException, REXPMismatchException {
		RConnection rc = new RConnection();
		// 저 소스 파일을 시행한 다음에, 그 폴더를 source를 통해 실행 시킨다.그리고 그 결과를 imsi에 저장하고
		// 그 수행된 결과 값을 추출한
		REXP x = rc.eval("imsi<-source('C:/Alpha/Rstudy/rjavatest.R'); imsi$value");
		RList list = x.asList();

		String pid = list.at("product").asString();
		System.out.print("PID : " + pid);

		int clickcount = list.at("clickcount").asInteger();
		System.out.println("\tCLICKCOUNT : " + clickcount);
		rc.close();
	}

	public static void main(String[] args) throws REXPMismatchException, REngineException {
		System.out.println("------------ R에서 버젼정보 가져오기 --------------");
		RServeExample.getString();
		System.out.println("------------ R에서 정수 데이터 가져오기 --------------");
		RServeExample.getInteger();
		System.out.println("------------ R에서 더블 데이터들 가져오기 -------------");
		RServeExample.getDoubles();
		System.out.println("------------  R에서 데이터 주입 연산후 가져오기 ------");
		RServeExample.getIntegers();
		System.out.println("------------  R에서 데이터 생성(데이터 프레임) 연산후 가져오기------");
		RServeExample.getDataFrame1();
		System.out.println("------------ R에서 데이터 프레임 가져오기 --------------");
		RServeExample.getDataFrame2();

	}
}
