package org.second.kill.db;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gushu
 * @date 2017/09/06
 */
public class Database {

	
	public final static int LOTTERY_NUM = 10;
	
	public final static AtomicInteger REMAINING_NUM = new AtomicInteger(LOTTERY_NUM);
	public final static BitSet ORDER_RESULT = new BitSet(LOTTERY_NUM);  
	public final static List<String> GOODS_STORE = new ArrayList<String>();
	public final static int[] GOODS_ORDER = new int[LOTTERY_NUM];
	
	static{
		GOODS_STORE.add("陶瓷");
		GOODS_STORE.add("玉佩");
		GOODS_STORE.add("铁锅");
		GOODS_STORE.add("iphone");
		GOODS_STORE.add("数据线");
		GOODS_STORE.add("路虎");
		GOODS_STORE.add("烟灰盒");
		GOODS_STORE.add("月饼");
		GOODS_STORE.add("钢笔");
		GOODS_STORE.add("马克杯");
		
		resetGoodsOrder();
	}

	


	public static void resetGoodsOrder() {
		
		List<Integer> randIntList = new ArrayList<Integer>();
		while(randIntList.size() < LOTTERY_NUM){
			int randomInt = generateRandomInt();
			if(randIntList.contains(randomInt)){
				continue;
			}
			randIntList.add(randomInt);
		}
		
		List<Integer> tmpIntList = new ArrayList<Integer>(randIntList);
		Collections.sort(tmpIntList);
		int index = 0;
		for(Integer item: tmpIntList){
			int orderInt = randIntList.indexOf(item);
			GOODS_ORDER[index] = orderInt;
			index++;
		}
	}
	
	public static void main(String[] args) {
		for(int round =0;round <12;round++){
			resetGoodsOrder();
			System.out.println("****************round"+round+"*********************");
			for(int i = 0;i<GOODS_ORDER.length;i++){
				System.out.print(GOODS_ORDER[i]+",");
			}
			System.out.println();
		}
	}




	private static int generateRandomInt() {
		Random random = new Random();
		int randomInt = random.nextInt(87412555);
		return randomInt;
	}
	
}
