import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public boolean isValidParenthesis(int length, Parenthesis[] arr) throws Exception{
		Stack<Parenthesis> stack = new Stack<>();
		boolean isvalid = true;
		
		for(int i=0; i<length; i++) {
			Parenthesis p = arr[i];
			
			if(p.type) {
				stack.push(p);
			}
			else {
				if(stack.size()>0 && stack.peek().type) {
					stack.pop();
				}
				else {
					return false;
				}
			}
		}
		if(stack.size()>0) {
			return false;
		}
		
		
		return isvalid;
	}
	
	public void testCase(int testCase)throws Exception {
		String input = scanner.next();
		int length = input.length();
		
		Parenthesis [] myArr = new Parenthesis[length];
		
		for(int i=0; i<length; i++) {
			myArr[i] = new Parenthesis(i, input.charAt(i));
		}
		
		boolean isvalid = isValidParenthesis(length, myArr);
		
		if(isvalid) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	}
	
	public static void main(String[] args) throws Exception {
		Main app = new Main();
		int testCase = scanner.nextInt();
		for(int i=0; i<testCase; i++) {
			app.testCase(i);
		}
	}
}

class Parenthesis {
	private static final boolean OPEN = true;
	private static final boolean CLOSE = false;
	
	public final boolean type;
	public final int index;
	
	public Parenthesis(int index, boolean type) {
		this.index = index;
		this.type = type;
	}
	public Parenthesis(int index, char c) {
		this.index = index;
		if(c=='(') {
			this.type = OPEN;
		}
		else {
			this.type = CLOSE;
		}
	}
}