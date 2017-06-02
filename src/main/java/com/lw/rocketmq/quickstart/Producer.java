package com.lw.rocketmq.quickstart;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * ������
 * 
 * @author lw
 *
 */
public class Producer {

	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
		// ����nameserver����,��;�ֿ�
		producer.setNamesrvAddr("192.168.154.131:9876;192.168.154.132:9876");
		producer.setInstanceName("Producer");
		// ��Ҫ�����һ��,����ᱨ���Ӵ���
		producer.setVipChannelEnabled(false); 
		// ����,ֻ�ó�ʼ��һ�ξ�ok
		producer.start();

		// ��ʼ������Ϣ
		for(int i = 0 ; i < 100 ; i++ ){
			Message msg = new Message("TopicTest01", "TAG1", ( "hello rocketmq " + i ).getBytes() );
			SendResult result = producer.send(msg);
			System.out.println(result);
		}
		// �ͷ���Դ
		producer.shutdown();
	}
}
