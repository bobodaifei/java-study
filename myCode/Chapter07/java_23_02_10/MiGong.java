package Chapter07.java_23_02_10;

public class MiGong {
  public static void main(String[] args) {
    int[][] map = new int[8][7];
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (i == 0 || i == map.length - 1 || j == 0 || j == map[i].length - 1) {
          map[i][j] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[5][2] = 1;
        map[5][3] = 1;
        map[5][4] = 1;
        map[5][5] = 1;
      }
    }

    T2 t2 = new T2();
    t2.findWay(map, 1, 1);
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
    
  }
}

class T2 {
  // 0可以走 1障碍物 2可以走 3走过，但路不通
  // 当map[6][5]==2时结束
  // 找路方向策略 下 右 上 左
  public boolean findWay(int[][] map, int i, int j) {
    if (map[6][5] == 2) {//结束
      return true;
    }else{
      if (map[i][j]==0) {//0表示未走过
        map[i][j]=2;//标识走过，但结果未知
        for (int n = 0; n < map.length; n++) {
          for (int m = 0; m < map[n].length; m++) {
            System.out.print(map[n][m] + " ");
          }
          System.out.println();
        }
        System.out.println();
        //四个方向
        if (findWay(map, i+1, j)) {
          return true;
        }else if (findWay(map, i, j+1)) {
          return true;
        } else if (findWay(map, i-1, j)) {
          return true;
        } else if (findWay(map, i, j - 1)) {
          return true;
        }else{
          map[i][j] = 3;
          return false;
        }
        
      }else{//1障碍物不可以走 2已经测试过 3死路过
        return false;
      }
    }
  }
}
