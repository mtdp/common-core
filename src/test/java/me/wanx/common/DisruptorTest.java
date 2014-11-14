package me.wanx.common;

import java.io.IOException;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.SingleThreadedClaimStrategy;
import com.lmax.disruptor.SleepingWaitStrategy;

public class DisruptorTest {

	public static void main(String[] args) throws IOException {
		HelperTest h = new HelperTest();
		for(int i = 0; i < 100; i++){
			h.produce(new String("1234"));
		}
		
		
		System.in.read();
	}

}


class EventTest{
	private String obj;
	public Object getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	EventFactory doSomethingEvent = new EventFactory(){
		@Override
		public Object newInstance() {
			return new EventTest();
		}
	};
}

/**
 * 消费者
 * @author gqwang
 *
 */
class EventHandlerTest implements EventHandler{

	@Override
	public void onEvent(Object arg0, long arg1, boolean arg2) throws Exception {
		System.out.println("onEvent..."+arg0.toString());
		
	}
}

class HelperTest{
	
	private int bufferSize = 1024*8;
	private RingBuffer ringBuffer;
	private SequenceBarrier sequence;
	private EventHandlerTest handler;
	private BatchEventProcessor processor;
	
	public HelperTest(){
		ringBuffer = new RingBuffer(new EventTest().doSomethingEvent, new SingleThreadedClaimStrategy(bufferSize), new SleepingWaitStrategy());
		sequence = ringBuffer.newBarrier();
		handler = new EventHandlerTest();
		processor = new BatchEventProcessor(ringBuffer, sequence, handler);
		ringBuffer.setGatingSequences(processor.getSequence());
		
		new Thread(processor).start();
	}
	
	public void produce(Object obj){
		long seq = ringBuffer.next();
		Object obj0 = ringBuffer.get(seq);
		((EventTest)obj0).setObj(obj.toString());
		ringBuffer.publish(seq);
	}
}

