import java.util.PrimitiveIterator;

/**
 * @program DynamicArray
 * @description:
 * @author: Zong Shi
 * @create 2019-08-13 11:29
 */

public class DynamicArray {

  private static final int num = 4;

  private static int scale = num/2;




  public static void main(String[] args) {

    Cell[][] arrIni = new Cell[num][num];


    for (int i = 0; i < arrIni.length; i++) {
      for (int j = 0; j < arrIni[i].length; j++) {
        arrIni[i][j] = new Cell(i, j, true, false);
      }
    }
    for (int i = 0; i < arrIni.length; i++) {

      if (arrIni[i][0].getCurrentStatus() == true || arrIni[0][i].getCurrentStatus() == true || arrIni[arrIni.length - 1][i].getCurrentStatus() == true || arrIni[i][arrIni.length - 1].getCurrentStatus() == true) {
        Cell[][] arr2 = extendsArray(arrIni);
      }
    }

  }

  public static Cell[][] extendsArray(Cell[][] array){
    Cell[][] arr = new Cell[array.length*2][array[0].length*2];
    for(int i = 0; i<arr.length;i++){
      for(int j = 0; j<arr.length; j++){
        arr[i][j] = null;
      }
    }

    for(int i = scale; i<arr.length-scale;i++){
      for(int j = scale; j<arr.length-scale;j++)
      {
        arr[i][j] = array[i-scale][j-scale];
      }

    }
    scale = scale*2;
    return arr;
  }

  public int checkNeighbor(Cell c)
  {
    if(c[c.getY()+1][c.getX()])//dfdfd

  }


}
    