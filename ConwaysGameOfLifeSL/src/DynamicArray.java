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
  
  Cell[][] arrIni;
  
  Cell[][] arrNext;

  public DynamicArray(){
	   
	  arrIni= new Cell[num+2][num+2];
	  for (int i = 0; i < arrIni.length; i++) {
      for (int j = 0; j < arrIni.length; j++) {
        arrIni[i][j] = new Cell(i, j, false, false);
      }
    }
	  for (int i = 1; i < arrIni.length-1; i++) {
	      for (int j = 1; j < arrIni.length-1; j++) {
	        arrIni[i][j] = new Cell(i, j, true, false);
	      }
	    }
   
  }
  
 public static void main(String[] args) {
	 new DynamicArray();
 }
 
  public static Cell[][] extendsArray(Cell[][] array){
	  
	  
	
    Cell[][] arr = new Cell[(array.length-2)*2][(array[0].length-2)*2];
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
  
  public void checkExpansion(Cell[][] arrIni) {
	  
	  for (int i = 1; i < arrIni.length-1; i++) {

	      if (arrIni[i][1].getCurrentStatus() == true || arrIni[1][i].getCurrentStatus() == true || arrIni[arrIni.length - 2][i].getCurrentStatus() == true || arrIni[i][arrIni.length - 2].getCurrentStatus() == true) {
	        Cell[][] temp = extendsArray(arrIni);
	        arrIni = temp;
	      }
	    }
  }
  
  public void updateStatus() {
	  for(int i=1;i<arrIni.length-1;i++) {
		  for(int j=1;j<arrIni.length-1;j++) {
			  Cell c = arrIni[i][j];
			  int counter = checkNeighbor(c);
			  if(c.getCurrentStatus()==true)
			  {
				  if(counter!=2&&counter!=3)
				  {
					  c.setNextStatus(false);
				  }
				  
			  }
			  else if(c.getCurrentStatus()==false)
			  {
				 if(counter==3) {
					 c.setNextStatus(true);
				 }
			  }
			  
		  }
	  }
	  
	  arrNext = nextGenArray(arrIni);
	  
  }
  
  public Cell[][] nextGenArray(Cell[][] arr)
  {
	Cell[][] tempNextGen = arr;  
  
	  for(int i=1;i<tempNextGen.length-1;i++) {
		  for(int j=1;j<tempNextGen.length-1;j++) {
			  Cell c = tempNextGen[i][j];
			  c.setCurrentStatus(c.getNextStatus());
			  c.setNextStatus(false);
		  }
	  }
	  return tempNextGen;
  }
  
  
  
  

  public int checkNeighbor(Cell c)
  {
	  int counter = 0;
	  int cellx = c.getX();
	  int celly = c.getY();
	  
	  //check right side
	  if(arrIni[cellx+1][celly].getCurrentStatus()==true) {
		  counter++;
	  }
	  //check top right side
	  if(arrIni[cellx+1][celly-1].getCurrentStatus()==true) {
		  counter++;
	  }
	  
	  //check bottom right side
	  if(arrIni[cellx+1][celly+1].getCurrentStatus()==true){
		counter++;
	  }
	  
	  //check top side
	  if(arrIni[cellx][celly-1].getCurrentStatus()==true) {
		  counter++;
	  }
	  
	  //check top left side
	  if(arrIni[cellx-1][celly-1].getCurrentStatus()==true) {
		  counter++;
	  }
	  
	  //check left side
	  if(arrIni[cellx-1][celly].getCurrentStatus()==true) {
		  counter++;
	  }
	  //check left bottom side
	  if(arrIni[cellx-1][celly+1].getCurrentStatus()==true) {
		  counter++;
	  }
	  
	  //check down side
	  if(arrIni[cellx][celly+1].getCurrentStatus()==true) {
		  counter++;
	  }
	  return counter;

  }
  
  public void swapArr()
  {
	  arrIni = arrNext;
  }
  
  public Cell[][] getArr()
  {
	  return arrIni;
  }
  
  
  
  }


    