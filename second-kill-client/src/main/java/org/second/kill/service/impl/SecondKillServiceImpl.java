package org.second.kill.service.impl;

import org.second.kill.base.BaseResult;
import org.second.kill.db.Database;
import org.second.kill.service.SecondKillService;
import org.second.kill.utils.SecondKillUtil;
import org.springframework.stereotype.Service;

/**
 * @author gushu
 * @date 2017/09/06
 */
@Service
public class SecondKillServiceImpl implements SecondKillService {

	public String buy(String userId) {

		synchronized (SecondKillUtil.lock) {
			int currRemainNum = Database.REMAINING_NUM.get();
			if (currRemainNum <= 0) {
				return BaseResult.NOTHING;
			}
			Database.REMAINING_NUM.decrementAndGet();
			int orderIdx = Database.LOTTERY_NUM - currRemainNum;
			return Database.GOODS_STORE.get(Database.GOODS_ORDER[orderIdx]);
		}
	}

	public void resetOrder() {
		Database.REMAINING_NUM.set(Database.LOTTERY_NUM);
		Database.resetGoodsOrder();
	}
	
	

}
