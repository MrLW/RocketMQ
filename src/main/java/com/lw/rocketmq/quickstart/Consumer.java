package com.lw.rocketmq.quickstart;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * ������
 * 
 * @author lw
 *
 */
public class Consumer {

	public static void main(String[] args) throws Exception {
		// ����������,һ��Ϊ����,ȫ��ֻ��һ��
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
		// ����nameserver����
		consumer.setNamesrvAddr("192.168.154.131:9876;192.168.154.132:9876");

		consumer.setInstanceName("Consumer");
		// ��Ҫ�����һ��,����ᱨ���Ӵ���
		consumer.setVipChannelEnabled(false);
		// �����������ѵĸ���
		consumer.setConsumeMessageBatchMaxSize(10);

		consumer.subscribe("TopicTest01", "*");

		// ע�����
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.println("ÿ�����ѵĸ���:" + msgs.size());
				for (MessageExt msg : msgs) {
					System.out.println("����========>" + msg);
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}

		});
		// ע��: �����ڴ���listener֮����ܿ���
		consumer.start();
	}
}
