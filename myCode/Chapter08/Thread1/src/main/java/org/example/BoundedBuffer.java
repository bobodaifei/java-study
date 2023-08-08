package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//源码
public class BoundedBuffer {

  final Lock lock = new ReentrantLock();

  final Condition notFull = lock.newCondition();// notFull  缓存不满

  final Condition notEmpty = lock.newCondition();//notEmpty 缓存非空

  final Object[] items = new Object[100];

  int putptr, takeptr, count;

  public static void main(String[] args) {
    new BoundedBuffer();
  }

  public void put(Object x) throws InterruptedException {
    lock.lock();
    try {
      while (count == items.length) {
        notFull.await();//缓存不满这个条件是假的 及意思是 缓存是满的
      }
      items[putptr] = x;
      if (++putptr == items.length) {
        putptr = 0;
      }
      ++count;
      notEmpty.signal();//缓存非空这个条件是真的
    } finally {
      lock.unlock();
    }

  }

  public Object take() throws InterruptedException {

    lock.lock();
    try {
      while (count == 0) {
        notEmpty.await();//缓存非空这个条件是假的   及意思是  现在缓存是空的
      }
      Object x = items[takeptr];
      if (++takeptr == items.length) {
        takeptr = 0;
      }
      --count;
      notFull.signal();//缓存不满这个条件是真的
      return x;
    } finally {
      lock.unlock();
    }
  }
}
