
public class Main {

//	public Main() {
////		DynamicArray test = new DynamicArray();
////		Cell[][] testArr = test.getArr();
////		
//		
//		test.extendsArray();
//		
//		testArr = test.getArr();
//		
//		for(int i=0; i<testArr.length; i++) {
//			for(int j = 0; j<testArr.length; j++) {
//				System.out.print(testArr[i][j].getCurrentStatus()+" ");
//			}
//			System.out.println();
//		}
//
//		
//		test.updateStatus();
//		
//
//		
//		test.swapArr();
////		
//		testArr = test.getArr();
////		System.out.println("testArr length " + testArr.length);
//		
////		Cell[][] testNextArr = test.getNextArr();
//		
//		for(int i=0; i<testArr.length; i++) {
//			for(int j = 0; j<testArr.length; j++) {
//				System.out.print(testArr[i][j].getCurrentStatus()+" ");
////				System.out.print(testArr[i][j].getNextStatus()+" ");
//			}
//			System.out.println();
//		}
		
		
		
		
		
		
		
//	}
	
	public static void print(Cell[][] testArr)
	{
		for(int i=0; i<testArr.length; i++) {
			for(int j = 0; j<testArr.length; j++) {
				System.out.print(testArr[i][j].getCurrentStatus()+" ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	public static void main(String[] args) {
		DynamicArray test = new DynamicArray();
		Cell[][] testArr = test.getArr();
		print(testArr);
		
		
		int x = 0;
		while(true)
		{
		test.extendsArray();
		testArr = test.getArr();
		print(testArr);
		test.updateStatus();
		test.swapArr();
		testArr = test.getArr();
		print(testArr);
		x++;
		}
		

	}

}
