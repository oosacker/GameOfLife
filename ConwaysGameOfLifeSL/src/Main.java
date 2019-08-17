import java.util.concurrent.TimeUnit;

/**
 * Test code for the Game of Life - uses console to display the cells.
 * 
 * @author Zong Shi, Natsuki Hasegawa
 *
 */
public class Main {

	/**
	 * Prints out a live cell as "o" and dead cell as "-" for display purposes.
	 * @param testArr the cell array to test.
	 */
	public static void print(Cell[][] testArr){
		for(int i=0; i<testArr.length; i++) {

			for(int j = 0; j<testArr.length; j++) {

				if(testArr[i][j].getCurrentStatus()==true)
					System.out.print("o" + " ");

				else 
					System.out.print("-" + " ");

			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}


	public static void main(String[] args) throws Exception {
		DynamicArray test = new DynamicArray();
		Cell[][] testArr = test.getArr();
		print(testArr);

		while(true){
			TimeUnit.MILLISECONDS.sleep(1000);
			test.extendsArray();
			testArr = test.getArr();
			test.updateStatus();
			test.swapArr();
			testArr = test.getArr();
			print(testArr);
			
			int checkingNum = test.getNum();
			if(checkingNum < testArr.length){
			   test.shrinkArray();
			  }
		}
	}
}



