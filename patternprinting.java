public class patternprinting
{
	public static void main(String[] args)
	{
		int noOfRows = 5;
		int m=1;
		int noofSpaces=noOfRows;

		for (int i = 1; i <=noOfRows; i++)
		{
			for(int j=1;j<=noofSpaces;j++) {
				System.out.print(" ");
			}
			if(i==1) {  
				System.out.print(1);
			}else {
				m=m*11;
				String value = String.valueOf(m);
				for (int k = 0; k < value.length(); k++) {
					System.out.print(value.charAt(k) + " ");
				}			
			}
			noofSpaces=noofSpaces-1;
			System.out.println();
		}
	}
}