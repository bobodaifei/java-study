package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {

  private Map cacheMap = new HashMap<String, Object>();

  public static void main(String[] args) throws Exception {
    Object o = new CacheDemo().get(1);
    System.out.println(o);
  }

  private ReadWriteLock rwl = new ReentrantReadWriteLock();

  public Object get(int key) throws Exception {
    rwl.readLock().lock();
    Object value = null;
    try {
      value = cacheMap.get(key);
      if (value == null) {
        rwl.readLock().unlock();
        rwl.writeLock().lock();
        try {
          value = "aaaa";//实际上是queryDB()
          if (value == null) {
            throw new Exception();
          }
          cacheMap.put(key, value);
        } finally {//两者位置可以替换，因为可以走锁的降级，先降级为读锁，再释放写锁仍然会时读锁
          rwl.writeLock().unlock();
        }
        rwl.readLock().lock();
      }

    } finally {
      rwl.readLock().unlock();
    }
    return value;
  }
}