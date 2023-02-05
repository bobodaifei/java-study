package java_23_02_05;

public class ForceConvertDetail {
  public static void main(String[] args) {
    // 强转符号只针对于最近的操作数有效,往往会使用小括号提升优先级
    int x0 = (int)10.1 * 2;
    int x1 = (int)(10.1 * 2);
    // char类型可以保存int的常量值,但不能保存int的变量值，需要强转
    char c1 = 100;
    int m = 100;
    // char c2 = m;错误的
    char c3 = (char)m;
  }
}
