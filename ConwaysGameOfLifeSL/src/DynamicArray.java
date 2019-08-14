
/**
 * DynamicArray - logic code for the Game of Life.
 * 
 * @author: Zong Shi, Natsuki Hasegawa
 * @version: 1.0
 */

public class DynamicArray {

	private static final int num = 10;
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

		arrIni[2][4].setCurrentStatus(true);
		arrIni[3][3].setCurrentStatus(true);
		arrIni[3][4].setCurrentStatus(true);
		arrIni[3][5].setCurrentStatus(true);
		arrIni[4][3].setCurrentStatus(true);
		arrIni[4][5].setCurrentStatus(true);
		arrIni[5][4].setCurrentStatus(true);

	}

	/**
	 * 
	 * This method doubles the size of the 2D array in case a live cell exists in the row/column which is next to the boundary of 2D array. We start the loop from i = 1 and end by arrIni.length-1, because the design will not set live cell to the position next to the boundary.
	 */
	public void extendsArray(){
		for (int i = 1; i < arrIni.length-1; i++) {
			if (arrIni[i][1].getCurrentStatus() == true || arrIni[1][i].getCurrentStatus() == true || arrIni[arrIni.length - 2][i].getCurrentStatus() == true || arrIni[i][arrIni.length - 2].getCurrentStatus() == true) {
				Cell[][] temp = extend();
				arrIni = temp; 
				break;
			}
		}
	}

	/**
	 * This method will check the top/bottom/right/left part of current array, if no live cells exist within scale/2 to the 2D array boundary, current array will shrink to half size and replace the current 2D array.
	 */
	public void shrinkArray()
	{
		
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

	/**
	 * This method creates an empty array that is double the size of the current 2D array. The current 2D array will be placed in the center part of the extended array. This method will return the double sized array.
	 * @return arr the extended cell array.
	 */
	public Cell[][] extend() {

		Cell[][] arr = new Cell[(arrIni.length)*2][(arrIni.length)*2];
		for(int i = 0; i<arr.length;i++){
			for(int j = 0; j<arr.length; j++){
				arr[i][j] = new Cell(i,j,false,false);
			}
		}

		for(int i = scale; i<arr.length-scale;i++){
			for(int j = scale; j<arr.length-scale;j++)
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
	 * This method updates the nextStatus of each cell. Only cells that will be alive in the next iteration will be updated, as the default setting of nextStatus is "false".
	 * 
	 */
	public void updateStatus() {

		for(int i=1;i<arrIni.length-1;i++) {
			for(int j=1;j<arrIni.length-1;j++) {
				Cell c = arrIni[i][j];
				int counter = checkNeighbor(c);

				if(c.getCurrentStatus()==true)
				{

					if(counter==2||counter==3)
					{
						c.setNextStatus(true);

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


	/**
	 * 
	 * This method will create a copy of main 2D array, and the currentStatus of each cell will be replaced by its nextStatus. The nextStatus will also be replaced by "false" as default. 
	 * @param arr Cell array to copy.
	 * @return tempNextGen Updated cell array.
	 * 
	 */
	public Cell[][] nextGenArray(Cell[][] arr)
	{
		Cell[][] tempNextGen = arr;
		for(int i=1; i<tempNextGen.length-1; i++) {
			for(int j=1; j<tempNextGen.length-1; j++) {
				Cell c = tempNextGen[i][j];
				c.setCurrentStatus(c.getNextStatus());
				c.setNextStatus(false);
			}
		}
		return tempNextGen;
	}



	/**
	 * 
	 * This method is used to count how many live cells are near the input Cell. 
	 * @param c Cell object to check.
	 * @return counter Total number of adjacent live cells.
	 * 
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


	/**
	 * This method will check the top/bottom/right/left part of the current array. If no live cells exist within scale/2 to the 2D array boundary, the current array will shrink to half size and replace the current 2D array.
	 * @return cellShrink Shrunken cell array.
	 */
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
	 * This method will update the initial array to represent the next generation of life.
	 * 
	 */
	public void swapArr()
	{
		arrIni = arrNext;
	}
	
	/**
	 * 
	 * Returns the data array to the GUI.
	 * @return arrIni Data array arrIni.
	 * 
	 */
	public Cell[][] getArr()
	{
		return arrIni;
	}


	/**
	 * Returns the next state array.
	 * @return arrNext Next state array.
	 * 
	 */
	public Cell[][] getNextArr()
	{
		return arrNext;
	}
	
	/**
	 * Returns the scale factor.
	 * @return scale Scale factor.
	 */
	public int getScale()
	{
		return scale;
	}

	/**
	 * Returns the array size.
	 * @return num Array size.
	 */
	public int getNum()
	{
		return num;
	}

}


