import java.util.concurrent.TimeUnit;

public class Main {


	public static void print(Cell[][] testArr){
		for(int i=0; i<testArr.length; i++) {
			for(int j = 0; j<testArr.length; j++) {
				System.out.print(testArr[i][j].getCurrentStatus()+" ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	/**
	 * 
	 * @param testArr
	 */
	public static void print2(Cell[][] testArr){
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
///

	public static void main(String[] args) throws Exception {
		DynamicArray test = new DynamicArray();
		Cell[][] testArr = test.getArr();
		print2(testArr);

		while(true){
			TimeUnit.MILLISECONDS.sleep(1000);
			test.extendsArray();
			testArr = test.getArr();
			test.updateStatus();
			test.swapArr();
			testArr = test.getArr();
			print2(testArr);
			
			int checkingNum = test.getNum();
			
			if(checkingNum < testArr.length)
			  {
			   test.shrinkArray();
			  }
			
		}
	}
}



