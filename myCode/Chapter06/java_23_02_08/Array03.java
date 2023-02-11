package Chapter06.java_23_02_08;

public class Array03 {
  public static void main(String[] args) {
    char[] charArray = new char[26];
    for(int i = 0;i<26;i++){
      charArray[i]=(char) ('a'+i);
    }
    for (int i = 0; i < 26; i++) {
      System.out.println(charArray[i]);
    }
  }
}
