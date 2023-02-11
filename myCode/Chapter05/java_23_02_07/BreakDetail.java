package Chapter05.java_23_02_07;


public class BreakDetail {
  public static void main(String[] args) {
    label1:{
      for(int i = 1; i<=10;i++){
        System.out.println("i=" + i);
        label2: {
          for (int j = 1; j <= i; j++) {
            if (i == 3) {
              break label1;
            }
            System.out.println(j);
            
          }
        }
        
      }
    }
  }
}
