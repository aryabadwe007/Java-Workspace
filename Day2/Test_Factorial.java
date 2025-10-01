import java.util.Scanner;

class Test_Factorial{

	public static int testing (int n){
	if(n<=1)
	{
		return 1;
	}
	else{
		int fact = 1;

		for(int i = 1; i<=n; i++)
		{
			fact *= i;
		}
			return fact;	
	}
}

	public static void main(String[] args){
		
	Scanner sc = new Scanner (System.in);
	System.out.println("Enter numbers : ");
	int n=sc.nextInt();
	int facto = testing(n);
System.out.println("Factorial is : " + facto);
}
}

