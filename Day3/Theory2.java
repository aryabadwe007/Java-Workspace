// 1 - Dimensional Array (1D Array)

// Addition of numbers

import java.util.Scanner;

class Theory2{
public static void acceptData(int [] arr)
{
	Scanner sc = new Scanner (System.in);
	for(int i = 0; i<arr.length; i++){
		System.out.println("Enter the Data : ");
		arr[i] = sc.nextInt();
	}
}

public static void displayData(int [] arr)
{
	for (int i = 0; i<arr.length; i++)
	{
		System.out.println(i + ":" + arr[i]);
	}
}

public static int adddata (int [] arr)
{
	int sum = 0;
	for(int i = 0; i<arr.length; i++)
	{
		sum += arr[i];
			
	}	
		System.out.println("Addition is : " + sum);
	return sum;
}
//finding minimum
public static int findMin(int [] arr){
    int min = arr[0];
    for(int i = 0; i < arr.length; i++){
        if(arr[i] < min){
            min = arr[i];
        }
    }
    // Print the final minimum value AFTER the loop finishes
    System.out.println("Minimum is : " + min); 
    return min;
}

public static int findMax(int [] arr){
    int max = arr[0];
    for(int i = 0; i < arr.length; i++){
        if(arr[i] > max){
            max = arr[i];
        }
    }
  
    System.out.println("Maximum is : " + max); 
    return max;
}

public static int searchByValue(int [] arr,int f){
	for(int i=0;i<arr.length;i++){
		if(f==arr[i]){
			return i;
		}
	
	}
	return -1;
}

//Find all Occurrences
/*public static int findallOccurrences(int [] arr , int num){
	int  [] temp = new int[arr.length];
	for(int i = 0; i<arr.length; i++){
		temp [i] = -1;
	}
	{
	
	}
	int cnt = 0;
	
	for (int i =0; i<arr.length; i++){
		if(arr[i] == num){
			temp[cnt] = i;
			cnt++;
		}
		
		}
		
		if (cnt>0){
		return temp[];
		}
		
		return null;

			
}
*/

// Find nth minimum 


public static int findNthMin(int [] arr,int n){
	for (int i=0; i<arr.length; i++){
		int pos = findMinPos(arr, i);
		int temp = arr[i];
		arr[i] = arr[pos];
		arr[pos] = temp;
}
//System.out.println("The"+n+ "element is : " +  );
return arr[n-1];
}

public static int findMinPos(int [] arr, int start){
	int pos = start;
	int min = arr[pos];
	for(int i = start+1; i<arr.length; i++){
		if(min>arr[i]){
			pos = i;
			min = arr[i];
		}
	}return pos;
}
public static void main (String [] args)
{
    int [] arr = new int [10] ;
    Scanner sc = new Scanner (System.in);


    acceptData(arr);
    displayData(arr);
    adddata(arr);
    findMin(arr);
    findMax(arr);


    System.out.println("\nEnter the element to search for (f):"); ///F is element
    int f = sc.nextInt(); 

   
    int pos = searchByValue(arr, f);


    if(pos != -1){
        System.out.println("Element " + f + " found at index: " + pos);
    }
    else{
        System.out.println("Element " + f + " not found in the array.");
    }
	
	
	
	/*System.out.println("\nEnter the element to search for (num):"); ///num is element
    int num = sc.nextInt(); 
	    int[] arrpos = findallOccurrences(arr, num);
	    
		if(arrpos != null){
		Sytem.out.println("all position are");
		findallOccurrences(arr,num);
		
	}
	else{
		System.out.println("Not found");*/
	//}
	
	System.out.println("\nEnter the Number (n) : ");
	int n = sc.nextInt();
	findNthMin(arr , n);
	
}

}