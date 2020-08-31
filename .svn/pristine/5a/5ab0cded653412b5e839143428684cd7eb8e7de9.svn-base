package com.briup.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.briup.bean.Enviroment;
import com.briup.util.ConnecitonUtils;
import com.briup.util.FileBackup;

public class StoreImpl implements IStore {

	private static final Logger logger = Logger.getLogger(StoreImpl.class);
	@SuppressWarnings("unchecked")
	public void store(Collection<Enviroment> collection) {
		// 将备份数据加载进来
		try {
			Object object = FileBackup.recover("src/main/resources/server-data.txt", true);
			if (object != null) {
				collection.addAll((Collection<? extends Enviroment>) object);
			}

			Connection connection = ConnecitonUtils.getConnection(false);

			// 获取连接

			int num = 0;

			String oldDay = "0";

			PreparedStatement ps = null;

			// 根据Eviroment的采集日期，然后获取到里面的天 最后拼接一下
			for (Enviroment enviroment : collection) {
				String newDay = enviroment.getGetDate().toString().substring(8);
				
				// 保证了同一天只有一个ps对象
				if (!oldDay.equals(newDay)) {
					// 上一次循环，ps缓存池里面还有没执行的sql
					if (ps != null) {
						ps.executeBatch();
						ps.clearBatch();
						// 执行完了以后应该及时关闭
						ps.close();
					}
					
					oldDay = newDay; // 21
					// 拼接sql
					// insert into tbl_data_21 values(xxxx)
					String sql = "insert into tbl_data_" + oldDay + " values(common_seq.nextval,?,?,?,?,?,?,?,?,?)";
					ps = connection.prepareStatement(sql);
}
				
				/*
				 *  创建ps对象，一个ps对象就会有一个缓存池
				 *  如果数据的天数相同，那么久之创建一个ps对象
				 *  如果天数不同那么就创建不同的ps
				 *  怎么去保证同一天只会创建一个ps对象
				 *  需要一个变量去记录天数
				 */
				
				//PreparedStatement ps = connection.prepareStatement(sql);
				
				ps.setString(1, enviroment.getSrcId());
				ps.setString(2, enviroment.getDevId());
				ps.setLong(3, enviroment.getRegionId());
				ps.setString(4,enviroment.getName());
				ps.setLong(5, enviroment.getCount());
				ps.setInt(6,enviroment.getState());
				ps.setDouble(7, enviroment.getData());
				ps.setInt(8, enviroment.getReviceState());
				ps.setDate(9, enviroment.getGetDate());
				
				ps.addBatch();
				num ++;
				if (num % 100 == 0) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			
			// 等整个循环结束，ps里面有可能还存在没有执行的sql
			if (ps != null) {
				ps.executeBatch();
				ps.clearBatch();
			}
			
			logger.info("存储数据成功");
			connection.commit();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
