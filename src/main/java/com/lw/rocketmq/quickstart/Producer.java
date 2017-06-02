package com.lw.rocketmq.quickstart;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * 生产者
 * 
 * @author lw
 *
 */
public class Producer {

	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
		// 设置nameserver服务,以;分开
		producer.setNamesrvAddr("192.168.154.131:9876;192.168.154.132:9876");
		producer.setInstanceName("Producer");
		// 需要添加这一行,否则会报连接错误
		producer.setVipChannelEnabled(false); 
		// 开启,只用初始化一次就ok
		producer.start();

		// 开始发送消息
		for(int i = 0 ; i < 100 ; i++ ){
			Message msg = new Message("TopicTest01", "TAG1", ( "hello rocketmq " + i ).getBytes() );
			SendResult result = producer.send(msg);
			System.out.println(result);
		}
		// 释放资源
		producer.shutdown();
	}
}
