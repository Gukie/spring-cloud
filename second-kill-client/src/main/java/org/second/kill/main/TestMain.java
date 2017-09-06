package org.second.kill.main;

import java.util.concurrent.CountDownLatch;

import org.second.kill.service.SecondKillService;
import org.second.kill.service.impl.SecondKillServiceImpl;

/**
 * @author gushu
 * @date 2017/09/06
 */
public class TestMain {

	private SecondKillService secondKillService  =  new SecondKillServiceImpl();
	public static void main(String[] args) throws InterruptedException {
		TestMain testMain = new TestMain();
		testMain.me();
	}
	private void me() throws InterruptedException {
		for (int round = 0; round < 3; round++) {
			secondKillService.resetOrder();
			int participantNum = 20;
			final CountDownLatch startLatch = new CountDownLatch(1);
			final CountDownLatch endLatch = new CountDownLatch(participantNum);
//			Executor threadPool = Executors.newFixedThreadPool(10);
			for (int i = 0; i < participantNum; i++) {
				final int index = i;
				Thread thread = new Thread(new Runnable() {
					public void run() {
						try {
							startLatch.await();
							String result = secondKillService.buy(String.valueOf(index));
							System.out.println(index + ":" + result);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally {
							endLatch.countDown();
						}
						
					}
				});
				thread.start();
			}
			System.out.println("****** round "+round+" start ******");
			startLatch.countDown();
			endLatch.await();
			
		}
	}
}
