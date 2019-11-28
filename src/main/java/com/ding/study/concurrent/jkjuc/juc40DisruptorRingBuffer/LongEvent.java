package com.ding.study.concurrent.jkjuc.juc40DisruptorRingBuffer;

/**
 * @author daniel 2019-11-28 0028.
 */
public
// 自定义 Event
class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }
}

/**
 * // 指定 RingBuffer 大小,
 * // 必须是 2 的 N 次方
 * int bufferSize = 1024;
 * <p>
 * // 构建 Disruptor
 * Disruptor<LongEvent> disruptor
 * = new Disruptor<>(
 * LongEvent::new,
 * bufferSize,
 * DaemonThreadFactory.INSTANCE);
 * <p>
 * // 注册事件处理器
 * disruptor.handleEventsWith(
 * (event, sequence, endOfBatch) ->
 * System.out.println("E: "+event));
 * <p>
 * // 启动 Disruptor
 * disruptor.start();
 * <p>
 * // 获取 RingBuffer
 * RingBuffer<LongEvent> ringBuffer
 * = disruptor.getRingBuffer();
 * // 生产 Event
 * ByteBuffer bb = ByteBuffer.allocate(8);
 * for (long l = 0; true; l++){
 * bb.putLong(0, l);
 * // 生产者生产消息
 * ringBuffer.publishEvent(
 * (event, sequence, buffer) ->
 * event.set(buffer.getLong(0)), bb);
 * Thread.sleep(1000);
 * }
 */