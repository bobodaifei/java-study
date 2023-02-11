package Chapter07.java_23_02_10;

public class HanoiWower {
  public static void main(String[] args) {
    T3 t3 = new T3();
    t3.move(3, 'a', 'b', 'c');
  }
}

class T3 {
  // num表示移动的个数 abc表示三个塔
  public void move(int num, char a, char b, char c) {
    if (num == 1) {
      System.out.println(a + "->" + c);
    } else {
      // 如果由多个盘，看作两个0到len-1 len
      // 先移动上面所有盘到b 借助c
      move(num - 1, a, c, b);
      // 把最下面的盘移动到c
      System.out.println(a + "->" + c);
      // 再把b塔的所有盘移动到c，借助a
      move(num - 1, b, a, c);
    }
  }
}
