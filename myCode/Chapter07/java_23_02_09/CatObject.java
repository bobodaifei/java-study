package Chapter07.java_23_02_09;

public class CatObject {
  
  private String catName; 
  private int catAge;
  private String catColor;

  public String getCatName() {
    return catName;
  }
  public void setCatName(String catName) {
    this.catName = catName;
  }

  public int getCatAge() {
    return catAge;
  }
  public void setCatAge(int catAge) {
    this.catAge = catAge;
  }

  public String getCatColor() {
    return catColor;
  }
  public void setCatColor(String catColor) {
    this.catColor = catColor;
  }

  public void run(){
    System.out.println(catAge+"岁的"+catColor+"的"+catName+"叫了一声 喵喵~");
  }



}
