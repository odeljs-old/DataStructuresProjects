package dataStructuresProject2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TheBox {
	String textData;
	StackImpl rightOperand = new StackImpl();
	StackImpl leftOperand = new StackImpl();
	StackImpl result = new StackImpl();
	String[] myArray;
	boolean negCheckLeft = false; 
	boolean negCheckRight = false;
	int resultSize = 0;
	int arrayPrintPointer = 0;
	
	//reads data from file
	public void ReadFile() {
			try(BufferedReader br = new BufferedReader(new FileReader("addsAndSubtracts.txt"))) {
				StringBuilder sb = new StringBuilder();
			
				String line = br.readLine();
				while(line != null) {
					sb.append(line).append("\t");
					line = br.readLine();
			 		}
			 	textData = sb.toString();
				}
			 catch(IOException e) {
				e.printStackTrace();
			 	}
			
				myArray = textData.split("\\s");
		System.out.println(myArray.length);
	//for(int i = 0; i < myArray.length; i++) {
		//System.out.println(myArray[i]);
	//}
	}
	
	//Controls entry to loading the stacks and computations
	public void StacksControl() {
	
		//SizeCompare(myArray[3], myArray[4], myArray[5]);
		
	//Loop for rest of values
		for(int i = 0; i < myArray.length; i+=3) {
			SizeCompare(myArray[i], myArray[i + 1], myArray[i + 2]);
		}
	}

	//Compares the two operands and pads with zeros
	private void SizeCompare(String operand1, String operator, String operand2) {
		
		int additionalLength = 0;
		
		
		if(operand1.contains("-")){
			operand1 = operand1.replace("-", "");
			//if(operand1.length() > operand2.length()) {
				negCheckLeft = true;
			//}
			//if(operand1.length() < operand2.length()) {
				//negCheckRight = true;
			//}
		}
		
		if(operand2.contains("-")){
			operand2 = operand2.replace("-", "");
			//if(operand1.length() > operand2.length()) {
				negCheckRight = true;
			//}
			//if(operand1.length() < operand2.length()) {
				//negCheckRight = true;
			//}
		}
		
		
		int intOperand1 = Integer.parseInt(operand1);
		int intOperand2 = Integer.parseInt(operand2);
		//System.out.println(intOperand1 + "\n" + intOperand2);
		
		
		
		if(operand1.length() == operand2.length()) {
			if(intOperand1 > intOperand2) {
				SplitAndInput(operand2, operand1, operator);
			}else {
				SplitAndInput(operand1, operand2, operator);
			}
			
		}
		if(operand1.length() > operand2.length()) {
			
			do {
				
			rightOperand.Push('0');
			additionalLength++;
			}while(operand1.length() != operand2.length() + additionalLength);
		
			SplitAndInput(operand1, operand2, operator);
		}
		if(operand1.length() < operand2.length()){
			do {
				leftOperand.Push('0');
				additionalLength++;
				
			}while(operand2.length() != operand1.length() + additionalLength);
		SplitAndInput(operand1, operand2, operator);
		}
	
		
	}
		//split
		//input onto stack
		private void SplitAndInput(String leftOperand1, String rightOperand2, String operator) {
			
			char[] left = leftOperand1.toCharArray();
			char[] right = rightOperand2.toCharArray();
			for(int i = 0; i < left.length; i++) {
				leftOperand.Push(left[i]);
				
			}
			
			
			for(int i = 0; i < right.length; i++) {
				rightOperand.Push(right[i]);
			
			}
			
			DoMath(operator, leftOperand1, rightOperand2);
	}
		//Perform negative checks and operator then call appropriate method
		public void DoMath(String operator, String leftOperand1, String rightOperand2) {
			
			int pass = 0;
			if(leftOperand1.length() > rightOperand2.length()) {
				pass = leftOperand1.length();
			}
			if(leftOperand1.length() < rightOperand2.length()) {
				pass = rightOperand2.length();
			}
			if(leftOperand1.length() == rightOperand2.length()) {
				pass = leftOperand1.length();
			}
			
			if(operator.equals("+")) {
			
				if(negCheckLeft == false && negCheckRight == false) {
					Addition(pass);
				
				}
				if(negCheckLeft == false && negCheckRight == true) {
					
					Subtraction(pass);
					result.Push('-');
					resultSize++;
				}
				if(negCheckLeft == true && negCheckRight == false) {
					Subtraction(pass);
					
				}
				if(negCheckLeft == true && negCheckRight == true) {
					Addition(pass);
					result.Push('-');
					resultSize++;
				}
					
				}//else {
					//if(negCheckLeft == false && negCheckRight == false) {
						//Subtraction(rightOperand2);
					//}
				
			else {
				//System.out.println("Entered operator");
				if(Math.abs(Integer.parseInt(leftOperand1)) > Math.abs(Integer.parseInt(rightOperand2))) { 
					//System.out.println("Entered operator");
					if(negCheckLeft == false && negCheckRight == false) {
						Subtraction(pass);
				
					}
					if(negCheckLeft == false && negCheckRight == true) {
					
						Addition(pass);
					
					}
					if(negCheckLeft == true && negCheckRight == false) {
						Addition(pass);
						result.Push('-');
						resultSize++;
					}
					if(negCheckLeft == true && negCheckRight == true) {
						Subtraction(pass);
						result.Push('-');
						resultSize++;
					}
				}
					else {
						if(negCheckLeft == false && negCheckRight == false) {
							Subtraction(pass);
							result.Push('-');
							resultSize++;
						}
						if(negCheckLeft == false && negCheckRight == true) {
						
							Addition(pass);
						
						}
						if(negCheckLeft == true && negCheckRight == false) {
							Addition(pass);
							result.Push('-');
							resultSize++;
						}
						if(negCheckLeft == true && negCheckRight == true) {
							Subtraction(pass);
							
						}
					}
				}
			PrintResult();
			}
		
				
				
				
			
			
			//System.out.println(result.Pop().GetData());
			//System.out.println(result.Pop().GetData());
			//System.out.println(result.Pop().GetData());
		//}else {}
			

		
		
		public int GetPlaceValue(int sum) {
			
		int placeValue = sum - 10;
		return placeValue;
		}		
		
		public void Addition(int biggerOperand2) {
			int k = 0;
			int remainder = 0;
			//System.out.println(biggerOperand2.length());
			for(int i = 0; i < biggerOperand2; i++) {
				k = remainder + Character.getNumericValue(rightOperand.Pop().GetData()) + Character.getNumericValue(leftOperand.Pop().GetData());
				//System.out.println(k);
			if(k > 9) {
				k = GetPlaceValue(k);
				
				//result.Push((char)k);
				resultSize++;
				remainder = 1;
			}else {
				
				//char myResult = Character.forDigit(k, 10);
				//result.Push(myResult);
				resultSize++;
				remainder = 0;
				}	 
			char myResult = Character.forDigit(k, 10);
			result.Push(myResult);
			//System.out.println(result.Pop().GetData());
			
			}
			
		}
		
		public void Subtraction(int biggerOperand2) {
			
			int k = 0;
			boolean remainder = false;
			
			for(int i = 0; i < biggerOperand2; i++) {
				//System.out.println("Has Entered");
				if(remainder == false) {
					//System.out.println("remainder false");
					if(Character.getNumericValue(leftOperand.getHead()) < Character.getNumericValue(rightOperand.getHead())) {
						k = (10 + Character.getNumericValue(leftOperand.Pop().GetData())) - Character.getNumericValue(rightOperand.Pop().GetData());
						char j = (char)((Character.getNumericValue(leftOperand.Pop().GetData())) - 1);
						//System.out.println(j);
						leftOperand.Push(j);
						//char myResult = Character.forDigit(k, 10);
						//result.Push(myResult);	
						remainder = true;
					}else {
						k = Character.getNumericValue(leftOperand.Pop().GetData()) - Character.getNumericValue(rightOperand.Pop().GetData());
						remainder = false;
						//char myResult = Character.forDigit(k, 10);
						//result.Push(myResult);		
					}
					char myResult = Character.forDigit(k, 10);
					result.Push(myResult);
					resultSize++;
				}else {
					//System.out.println("remainder true");
					if(Character.getNumericValue(leftOperand.getHead()) < Character.getNumericValue(rightOperand.getHead())) {
						k = (Character.getNumericValue(leftOperand.Pop().GetData())) - Character.getNumericValue(rightOperand.Pop().GetData());
						remainder = true;
					}else {
						k = (Character.getNumericValue(leftOperand.Pop().GetData()) - 1) - Character.getNumericValue(rightOperand.Pop().GetData());
						remainder = false;
						}
					char myResult = Character.forDigit(k, 10);
					result.Push(myResult);
					resultSize++;
				}
					//result.Push((char)k);
				//System.out.println(k);
				//result.Push((char)k);	
				//char myResult = Character.forDigit(k, 10);
				//result.Push(myResult);	
				//resultSize++;
			}
			//System.out.println(result.Pop().GetData());	
			//System.out.println(result.Pop().GetData());
			//System.out.println(result.Pop().GetData());
			}
			//System.out.println(result.Pop().GetData());
		
		public void PrintResult() {
			
			
			System.out.print(myArray[arrayPrintPointer] + " " + myArray[arrayPrintPointer + 1] +" " + myArray[arrayPrintPointer + 2] + " = ");
			for(int j = 0; j < resultSize; j++) {
			System.out.print(result.Pop().GetData());
			
			}
			
			System.out.println("\n");
			resultSize = 0;
			arrayPrintPointer+=3;
		}
		}
			

		
		

		

