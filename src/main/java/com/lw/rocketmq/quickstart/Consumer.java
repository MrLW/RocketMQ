package com.lw.rocketmq.quickstart;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * 消费者
 * 
 * @author lw
 *
 */
public class Consumer {

	public static void main(String[] args) throws Exception {
		// 创建消费者,一般为单例,全局只有一个
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
		// 设置nameserver服务
		consumer.setNamesrvAddr("192.168.154.131:9876;192.168.154.132:9876");

		consumer.setInstanceName("Consumer");
		// 需要添加这一行,否则会报连接错误
		consumer.setVipChannelEnabled(false);
		// 设置批量消费的个数
		consumer.setConsumeMessageBatchMaxSize(10);

		consumer.subscribe("TopicTest01", "*");

		// 注册监听
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.println("每次消费的个数:" + msgs.size());
				for (MessageExt msg : msgs) {
					System.out.println("消费========>" + msg);
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}

		});
		// 注意: 必须在创建listener之后才能开启
		consumer.start();
	}
}
