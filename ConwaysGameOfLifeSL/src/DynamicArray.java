import java.util.PrimitiveIterator;

/**
 * @program DynamicArray
 * @description:
 * @author: Zong Shi
 * @create 2019-08-13 11:29
 */

public class DynamicArray {

	private static final int num = 4;

	private static int scale = (num)/2;

	Cell[][] arrIni;

	Cell[][] arrNext;

	public DynamicArray(){

		arrIni= new Cell[num][num];
		for (int i = 0; i < arrIni.length; i++) {
			for (int j = 0; j < arrIni.length; j++) {
				arrIni[i][j] = new Cell(i, j, false, false);
			}
		}

		arrIni[0][2].setCurrentStatus(true);
		//	  arrIni[1][2].setCurrentStatus(true);
		arrIni[1][3].setCurrentStatus(true);
		arrIni[2][1].setCurrentStatus(true);
		arrIni[2][2].setCurrentStatus(true);
		arrIni[2][3].setCurrentStatus(true);

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
/////
	public Cell[][] extend() {

		Cell[][] arr = new Cell[(arrIni.length)*2][(arrIni.length)*2];
		for(int i = 0; i<arr.length;i++){
			for(int j = 0; j<arr.length; j++){
				arr[i][j] = new Cell(i,j,false,false);
			}
		}

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
	 * 
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
		//	  System.out.println(arrIni.length + "Length^^^^^");//test
		for(int i=1;i<arrIni.length-1;i++) {
			for(int j=1;j<arrIni.length-1;j++) {
				Cell c = arrIni[i][j];
				//			  System.out.println("i "+i + " j " + j);//test
				int counter = checkNeighbor(c);
				//			  System.out.println("^^^^^" + i + " " + j);//test
				//			  System.out.println(counter + " counter-------------");//test
				//			  System.out.println(c.currentStatus);//test
				if(c.getCurrentStatus()==true)
				{
					//				  System.out.println(c.currentStatus+"*****************");

					if(counter==2||counter==3)
					{
						c.setNextStatus(true);
						//					  System.out.println("set run+++++++++++" + i + j);//Test
					}
					//				  System.out.println(c.currentStatus);

				}
				else if(c.getCurrentStatus()==false)
				{
					if(counter==3) {
						c.setNextStatus(true);


					}
				}

				//			 System.out.println("Update Test");

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
		//	  
		//	  System.out.println(celly);
		//	  System.out.println(cellx);


		//check right side
		if(arrIni[celly][cellx+1].getCurrentStatus()==true) {
			counter++;
			//		  System.out.println("1~~~~");
		}
		//check top right side
		if(arrIni[celly-1][cellx+1].getCurrentStatus()==true) {
			counter++;
			//		  System.out.println("2~~~~");
		}

		//check bottom right side
		if(arrIni[celly+1][cellx+1].getCurrentStatus()==true){
			counter++;
			//		System.out.println("3~~~");
		}

		//check top side
		if(arrIni[celly-1][cellx].getCurrentStatus()==true) {
			counter++;
			//		  System.out.println("4~~~~");
		}

		//check top left side
		if(arrIni[celly-1][cellx-1].getCurrentStatus()==true) {
			counter++;
			//		  System.out.println("5~~~~");
		}

		//check left side
		if(arrIni[celly][cellx-1].getCurrentStatus()==true) {
			counter++;
			//		  System.out.println("6~~~~");
		}
		//check left bottom side
		if(arrIni[celly+1][cellx-1].getCurrentStatus()==true) {
			counter++;
			//		  System.out.println("7~~~~");
		}

		//check down side
		if(arrIni[celly+1][cellx].getCurrentStatus()==true) {
			counter++;
			//		  System.out.println("8~~~~");
		}
		return counter;

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
}


