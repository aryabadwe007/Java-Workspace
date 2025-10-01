// Addition of 3 NUMBERS

import java.util.Scanner;
class AdditionNumbers{

	public static int result_addition (int n1, int n2, int n3){
	
	int sum = n1 + n2 + n3;
    return sum;

		
}

	public static void main(String[] args){
	
		Scanner sc = new Scanner (System.in);

	System.out.println("Enter the numbers : ");

	int n1 = sc.nextInt();
	int n2 = sc.nextInt();
	int n3 = sc.nextInt();
	

	int result = result_addition(n1, n2, n3);
	System.out.println("Sum is : " + result);

}
	
}














