import java.util.Scanner;

public class Test {
	public static void main(String[] args) {

		Scanner scan4 = new Scanner(System.in);
		String ans = null;
		do {
			System.out.println("Do you want to save this record (yes/no) ? ");
			ans = scan4.nextLine(); // jack
			
		}while( ! (ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("no")) );
		
		System.out.println("ans is "+ans);
		
		System.out.println("End of main");
		
	}
}

