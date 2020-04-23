package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SpringSchedulerTest {
	// 언제 다시 할것인가
	// corn : 리눅스 시스템 관리자로써
	// 밑에 *는 모든 즉 매 10초마다 반복함. -> 즉 1분마다 반복한다.
   @Scheduled(cron="10 * * * * *")//초,분,시,일,월,요일(1:일요일)
	
	// 스케쥴링 해야 하는 정보 기익
	// fixedDelay : 프로그램이 끝나고 나서 부터 10초 
	// fixedRate : 프로그램이 시작한 뒤로 10초
//   @Scheduled(fixedDelay=10000)//10초간격으로
	public void scheduleRun() {
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat dateFormat=new SimpleDateFormat(
				                                       "yyyy-MM-dd HH:mm:ss");
		try {
			Thread.sleep(2000);
		}catch(Exception e) {
			
		}
		System.out.println(new java.util.Date()+"스케줄 실행:"+dateFormat.format(calendar.getTime()));
		
	}
}
