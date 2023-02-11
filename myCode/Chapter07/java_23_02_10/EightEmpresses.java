package Chapter07.java_23_02_10;

public class EightEmpresses {
  public static void main(String[] args) {
    int[] val = new int[8];
    for (int i = 0; i < val.length; i++) {
      val[i] = -1;
    }
    T4 t4 = new T4();
    t4.start(val, 0, 0);
    System.out.println(t4.count);
    System.out.println(t4.judgeCount);
  }
}

class T4 {
  static int count = 0;
  static int judgeCount = 0;

  public void start(int[] val, int line, int column) {

    // 打印每一种可以的情况
    if (line == val.length) {
      print(val);
      return;
    }

    // 每一行进行判定
    for (int i = 0; i < val.length; i++) {
      if (isSuccess(val, line, i)) {// 如果可以
        val[line] = i;// 数组[行数]进行赋值
        start(val, line + 1, 0);
      }
    }
  }

  public boolean isSuccess(int[] val, int line1, int column1) {
    for (int i = 0; i < line1; i++) {
      if ((val[i] == column1) || (Math.abs(column1 - val[i]) == Math.abs(line1 - i))) {
        judgeCount++;
        return false;
      }
    }
    return true;
  }

  public void print(int[] val) {
    count++;
    for (int i = 0; i < val.length; i++) {
      System.out.print(val[i] + " ");
    }
    System.out.println();
  }
}
