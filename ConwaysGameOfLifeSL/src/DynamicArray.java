
/**
 * @program DynamicArray
 * @description:
 * @author: Zong Shi, Natsuki Hasegawa
 * @create 2019-08-13 11:29
 */

public class DynamicArray {

	//private static final int num = 4;
	private static final int num = 10;

	private static int scale = (num)/2;

	Cell[][] arrIni;
	Cell[][] arrNext;

	public DynamicArray(){

		arrIni= new Cell[num][num];
		
		arrIni = fillArrayWithDead(arrIni);
		
		
		//blinker:
//		arrIni[3][3].setCurrentStatus(true);
//		arrIni[3][4].setCurrentStatus(true);
//		arrIni[3][5].setCurrentStatus(true);
		
		/*
		//Toad: 
		arrIni[2][2].setCurrentStatus(true);
	    arrIni[2][3].setCurrentStatus(true);
	    arrIni[2][4].setCurrentStatus(true);
	    arrIni[3][1].setCurrentStatus(true);
	    arrIni[3][2].setCurrentStatus(true);
	    arrIni[3][2].setCurrentStatus(true);
	    arrIni[3][3].setCurrentStatus(true);
		*/
		
		/*
	    //hershel
	    arrIni[3][3].setCurrentStatus(true);
	    arrIni[4][3].setCurrentStatus(true);
	    arrIni[4][4].setCurrentStatus(true);
	    arrIni[4][5].setCurrentStatus(true);
	    arrIni[5][3].setCurrentStatus(true);
	    arrIni[5][5].setCurrentStatus(true);
	    arrIni[6][5].setCurrentStatus(true);
	    */
		
//		arrIni[2][2].setCurrentStatus(true);
//	    arrIni[2][3].setCurrentStatus(true);
//	    arrIni[2][4].setCurrentStatus(true);
//	    arrIni[2][7].setCurrentStatus(true);
//	    arrIni[2][8].setCurrentStatus(true);
		
		
		//spaceship:
		arrIni[3][3].setCurrentStatus(true);
		arrIni[3][4].setCurrentStatus(true);
		arrIni[3][5].setCurrentStatus(true);
		arrIni[3][6].setCurrentStatus(true);
		arrIni[3][7].setCurrentStatus(true);
		arrIni[3][8].setCurrentStatus(true);
		arrIni[4][2].setCurrentStatus(true);
		arrIni[4][8].setCurrentStatus(true);
		arrIni[5][8].setCurrentStatus(true);
		arrIni[6][2].setCurrentStatus(true);
		arrIni[6][7].setCurrentStatus(true);
		arrIni[7][4].setCurrentStatus(true);
		arrIni[7][5].setCurrentStatus(true);
		
		
		/*
		//r-pentomino
		arrIni[4][5].setCurrentStatus(true);
		arrIni[4][6].setCurrentStatus(true);
		arrIni[5][4].setCurrentStatus(true);
		arrIni[5][5].setCurrentStatus(true);
		arrIni[6][5].setCurrentStatus(true);
		*/
		
		/*
		// line:
		arrIni[3][2].setCurrentStatus(true);
		arrIni[3][3].setCurrentStatus(true);
		arrIni[3][4].setCurrentStatus(true);
		arrIni[3][5].setCurrentStatus(true);
		arrIni[3][6].setCurrentStatus(true);
		arrIni[3][7].setCurrentStatus(true);
		*/
		

	}

	/**
	 * 
	 * @Title：extendsArray 
	 * @Description：This method will double the size of 2D array in case any live cell existing in the row/column which is next to the boundary of 2D array.
	 * @param ： 
	 * @return ：void 
	 * @throws
	 */
	public void extendsArray(){
		for (int i = 1; i < arrIni.length-1; i++) {
			if (
					arrIni[i][1].getCurrentStatus() == true || 
					arrIni[1][i].getCurrentStatus() == true || 
					arrIni[arrIni.length - 2][i].getCurrentStatus() == true || 
					arrIni[i][arrIni.length - 2].getCurrentStatus() == true) {

				Cell[][] temp = extend();
				arrIni = temp; 
				break;
			}
		}
	}

	public Cell[][] extend() {

		Cell[][] arr = new Cell[(arrIni.length)*2][(arrIni.length)*2];

		arr = fillArrayWithDead(arr);

		for(int i = scale; i<arr.length-scale; i++){
			for(int j = scale; j<arr.length-scale; j++)
			{
				arr[i][j].setCurrentStatus(arrIni[i-scale][j-scale].getCurrentStatus());
				arr[i][j].setNextStatus(arrIni[i-scale][j-scale].getNextStatus());
			}

		}
		scale = scale*2;

		return arr;
	}

	/**
	 * Fill array with dead cells for initialisation
	 * @return
	 */
	public Cell[][] fillArrayWithDead(Cell[][] input) {

		for(int i = 0; i<input.length;i++){
			for(int j = 0; j<input.length; j++){
				input[i][j] = new Cell(i, j, false, false);
			}
		}
		return input;

	}

	/**
	 * 
	 * @Title：updateStatus 
	 * @Description：This method is to update nextStatus of each cell.
	 * @param ： 
	 * @return ：void 
	 * @throws
	 */
	public void updateStatus() {
		for(int i=1;i<arrIni.length-1;i++) {
			for(int j=1;j<arrIni.length-1;j++) {
				
				Cell c = arrIni[i][j];
				
				int counter = checkNeighbor(c);
				
				if(c.getCurrentStatus()==true){
					if(counter==2||counter==3){
						c.setNextStatus(true);
					}
				}
				else if(c.getCurrentStatus()==false){
					if(counter==3) {
						c.setNextStatus(true);
					}
				}
			}
		}

		arrNext = nextGenArray(arrIni);

	}


	/**
	 * 
	 * @Title：nextGenArray 
	 * @Description：This method will create a copy of main 2D array, and it's currentStatus will be replaced by nextStatus, int he meanwhile, the nextStatus will be replaced by "false". 
	 * @param ：@param arr
	 * @param ：@return 
	 * @return ：Cell[][] 
	 * @throws
	 */
	public Cell[][] nextGenArray(Cell[][] arr){
		
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


	/**
	 * 
	 * @Title：checkNeighbor 
	 * @Description：this method will be used to count how many live cells near the cell. 
	 * @param ：@param c
	 * @param ：@return 
	 * @return ：return number of live cells.
	 * @throws
	 */
	public int checkNeighbor(Cell c)
	{
		int counter = 0;
		int cellx = c.getX();
		int celly = c.getY();

		//check right side
		if(arrIni[celly][cellx+1].getCurrentStatus()==true) {
			counter++;
		}
		
		//check top right side
		if(arrIni[celly-1][cellx+1].getCurrentStatus()==true) {
			counter++;
		}

		//check bottom right side
		if(arrIni[celly+1][cellx+1].getCurrentStatus()==true){
			counter++;
		}

		//check top side
		if(arrIni[celly-1][cellx].getCurrentStatus()==true) {
			counter++;
		}

		//check top left side
		if(arrIni[celly-1][cellx-1].getCurrentStatus()==true) {
			counter++;
		}

		//check left side
		if(arrIni[celly][cellx-1].getCurrentStatus()==true) {
			counter++;
		}
		//check left bottom side
		if(arrIni[celly+1][cellx-1].getCurrentStatus()==true) {
			counter++;
		}

		//check down side
		if(arrIni[celly+1][cellx].getCurrentStatus()==true) {
			counter++;
		}
		return counter;

	}
	
	public void shrinkArray()
	  {
	   System.out.println("Shrinking run");
	   boolean flag = false;
	   int length = arrIni.length;
	  
	   //checking the right part alive cells
	   tag1:
	   for(int i=0;i<length;i++)
	   {
	    for(int j = length -scale/2; j<length; j++)
	    {
	     if(arrIni[i][j].getCurrentStatus()==true)
	     {
	      flag = true;
	      System.out.println("test1");
	      break tag1;
	     }
	    }
	   }
	   
	   //checking the left part alive cells
	   tag2:
	   for(int i=0; i<length; i++) {
	    for(int j=0; j<scale/2+1; j++)
	    {
	     if(arrIni[i][j].getCurrentStatus()==true)
	     {
	      flag = true;
	      System.out.println("test2");
	      break tag2;
	      
	     }
	    }
	   }
	   
	   //checking the top part alive cells
	   tag3:
	   for(int i = 0; i<scale/2+1;i++)
	   {
	    for(int j = 0; j<length; j++)
	    {
	     if(arrIni[i][j].getCurrentStatus()==true)
	     {
	      flag = true;
	      System.out.println("test3");
	      break tag3;
	     }
	    }
	   }
	   
	   //checking the bottom part alive cells
	   tag4:
	   for(int i = length-scale/2; i<length ; i++)
	   {
	    for(int j = 0; j<length ; j++)
	    {
	     if(arrIni[i][j].getCurrentStatus()==true)
	     {
	      flag = true;
	      System.out.println("test4");
	      break tag4;
	     }
	    }
	   }
	   
	   if(flag == false)
	   {
	    Cell[][] temp = shrink();
	    arrIni = temp;
	   }
	  }
	 
	public Cell[][] shrink()
	  {
	   Cell[][] cellShrink = new Cell[arrIni.length/2][arrIni.length/2];
	   for(int i = 0; i < arrIni.length/2; i++)
	   {
	    for(int j=0; j<arrIni.length/2; j++)
	    {
	     cellShrink[i][j] = new Cell(i,j,false,false);
	    }
	   }
	   
	   for(int i = scale/2; i< arrIni.length-scale/2; i++)
	   {
	    for(int j = scale/2 ; j< arrIni.length-scale/2;j++)
	    {
	     cellShrink[i-scale/2][j-scale/2].setCurrentStatus(arrIni[i][j].getCurrentStatus());
	     cellShrink[i-scale/2][j-scale/2].setNextStatus(arrIni[i][j].getNextStatus());
	    }
	   }
	   scale = scale/2;
	   
	   return cellShrink;
	  }

	/**
	 * 
	 * @Title：swapArr 
	 * @Description：This method will update initial array to present next generation of life.
	 * @param ： 
	 * @return ：void 
	 * @throws
	 */
	public void swapArr()
	{
		arrIni = arrNext;
	}

	public Cell[][] getArr()
	{
		return arrIni;
	}

	public Cell[][] getNextArr()
	{
		return arrNext;
	}
	
	public int getScale()
	{
		return scale;
	}
	
	public int getNum()
	{
		return num;
	}
}


