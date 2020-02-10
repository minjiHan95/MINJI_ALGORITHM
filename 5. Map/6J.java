package proj_ex1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Main{
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		
		HashMap<Integer,Integer> frequency = new HashMap<>();
		
		int x= scanner.nextInt();
		for(int i=0; i<x; i++) {
			int num = scanner.nextInt();
			if(frequency.containsKey(num)==false) {
				frequency.put(num, 0);
			}
			int size = frequency.size();
			int fre = frequency.get(num)+1;
			frequency.put(num, fre);
			
			writer.write(String.format("%d %d\n", size, fre));
		}
		writer.flush();
		writer.close();
	}
}
