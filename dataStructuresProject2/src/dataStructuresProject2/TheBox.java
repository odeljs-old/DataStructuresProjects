package dataStructuresProject2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
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
	int operandSize = 0;
	
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
			;
				myArray = textData.split("\\s");
		
	}
	
	//Controls entry to loading the stacks and computations
	public void StacksControl() {
	
		
		
	
		for(int i = 0; i < myArray.length; i+=3) {
			negCheckLeft = false;
			negCheckRight = false;
			SizeCompare(myArray[i], myArray[i + 1], myArray[i + 2]);
		
		}
	}

	//1. Removes negative values and stores negCheck as a boolean
	//2. Compares the two operands and pads with zeros
	private void SizeCompare(String operand1, String operator, String operand2) {
		operandSize = 0;
		int additionalLength = 0;
		
		
		if(operand1.contains("-")){
			operand1 = operand1.replace("-", "");
			
				negCheckLeft = true;
			
		}
		
		if(operand2.contains("-")){
			operand2 = operand2.replace("-", "");
			
				negCheckRight = true;
			
		}
		
		
		
		BigInteger intOperand1 = new BigInteger(operand1);
		BigInteger intOperand2 = new BigInteger(operand2);
		
		
		if(operand1.length() == operand2.length()) {
			
			if(intOperand1.compareTo(intOperand2) == 1) {
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
				operandSize++;
				additionalLength++;
				
			}while(operand2.length() != operand1.length() + additionalLength);
		SplitAndInput(operand1, operand2, operator);
		}
	
		
	}
		
	//Inputs String operands into character arrays and then pushes them onto the desired stacks
		private void SplitAndInput(String leftOperand1, String rightOperand2, String operator) {
			
			char[] left = leftOperand1.toCharArray();
			operandSize = left.length + operandSize;
			char[] right = rightOperand2.toCharArray();
		
			for(int i = 0; i < left.length; i++) {
				leftOperand.Push(left[i]);
				
			}
			
			
			for(int i = 0; i < right.length; i++) {
				rightOperand.Push(right[i]);
				
			}
			
			MathControl(operator, leftOperand1, rightOperand2);
	}
		
		
		//Math Control 
		//Provides entry into subtraction and addition methods by checking first the operator
		//and then the negCheck boolean values to determine the operation to be performed on the operands
		public void MathControl(String operator, String leftOperand1, String rightOperand2) {
			
			BigInteger leftOperand = new BigInteger(leftOperand1);
			BigInteger rightOperand = new BigInteger(rightOperand2);
			
			
			//addition operator
			if(operator.equals("+")) {
			
				if(negCheckLeft == false && negCheckRight == false) {
					
					Addition();
				
				}
				if(negCheckLeft == false && negCheckRight == true) {
					
					Subtraction(leftOperand1, rightOperand2);
					
					if(leftOperand.compareTo(rightOperand) == -1) {
						
						result.Push('-');
						resultSize++;	
					}
					
				}
				if(negCheckLeft == true && negCheckRight == false) {
					
					if(leftOperand.compareTo(rightOperand) == -1) {
						Subtraction(leftOperand1, rightOperand2);
					}
					else {
						Subtraction(leftOperand1, rightOperand2);
						result.Push('-');
						resultSize++;
					}
					
					
				}
				if(negCheckLeft == true && negCheckRight == true) {
					
					Addition();
					result.Push('-');
					resultSize++;
				}
					
				}
			//subtraction operator	
			else {
				
				
				if(leftOperand.compareTo(rightOperand) == 1) {
					if(negCheckLeft == false && negCheckRight == false) {
						Subtraction(leftOperand1, rightOperand2);
				
					}
					if(negCheckLeft == false && negCheckRight == true) {
					
						Addition();
					
					}
					if(negCheckLeft == true && negCheckRight == false) {
						Addition();
						result.Push('-');
						resultSize++;
					}
					if(negCheckLeft == true && negCheckRight == true) {
						Subtraction(leftOperand1, rightOperand2);
						result.Push('-');
						resultSize++;
					}
				}
					else {
						if(negCheckLeft == false && negCheckRight == false) {
							
							Subtraction(leftOperand1, rightOperand2);
							result.Push('-');
							resultSize++;
						}
						if(negCheckLeft == false && negCheckRight == true) {
						
							Addition();
						
						}
						if(negCheckLeft == true && negCheckRight == false) {
							Addition();
							result.Push('-');
							resultSize++;
						}
						if(negCheckLeft == true && negCheckRight == true) {
							Subtraction(leftOperand1, rightOperand2);
							
						}
					}
				}
			PrintResult();
			}
		
		
		//Returns the place value 
		public int GetPlaceValue(int sum) {
			
		int placeValue = sum - 10;
		return placeValue;
		}		
		
		//Addition operation
		public void Addition() {
			int k = 0;
			int remainder = 0;
			
			for(int i = 0; i < operandSize; i++) {
				k = remainder + Character.getNumericValue(rightOperand.Pop().GetData()) + Character.getNumericValue(leftOperand.Pop().GetData());
			
				if(k > 9) {
					k = GetPlaceValue(k);
					resultSize++;
					remainder = 1;
				}else {
					resultSize++;
					remainder = 0;
					}	
				if(i == operandSize - 1 && remainder == 1) {
					char myResult = Character.forDigit(k, 10);
					result.Push(myResult);
					result.Push('1');
				}else {
					char myResult = Character.forDigit(k, 10);
					result.Push(myResult);
					}
				}
			}
			
		
		
		//subtraction operation
		public void Subtraction(String myLeftOperand, String myRightOperand) {
			BigInteger myLeftOperand1 = new BigInteger(myLeftOperand);
			BigInteger myRightOperand2 = new BigInteger(myRightOperand);
			int k = 0;
			boolean remainder = false;
			int leftOperandPop = 0;
			int rightOperandPop = 0;
			
			for(int i = 0; i < operandSize; i++) {
				int myLeftOperandHead = 0;
				int myRightOperandHead = 0;
				if(myLeftOperand1.compareTo(myRightOperand2) == -1) {
					myLeftOperandHead = Character.getNumericValue(rightOperand.getHead());
					myRightOperandHead = Character.getNumericValue(leftOperand.getHead());
					leftOperandPop = Character.getNumericValue(rightOperand.Pop().GetData());
					rightOperandPop = Character.getNumericValue(leftOperand.Pop().GetData());
					
				}else {
					myLeftOperandHead = Character.getNumericValue(leftOperand.getHead());
					myRightOperandHead = Character.getNumericValue(rightOperand.getHead());
					rightOperandPop = Character.getNumericValue(rightOperand.Pop().GetData());
					leftOperandPop = Character.getNumericValue(leftOperand.Pop().GetData());
				
				}
				
				if(remainder == false) {
				
					if(myLeftOperandHead < myRightOperandHead) {
						k = (10 + leftOperandPop) - rightOperandPop;
						
						remainder = true;
					}else {
						k = leftOperandPop - rightOperandPop;
						remainder = false;
								
					}
					char myResult = Character.forDigit(k, 10);
					result.Push(myResult);
					resultSize++;
				}else {
					if(myLeftOperandHead < myRightOperandHead) {
						k = leftOperandPop - rightOperandPop;
						remainder = true;
					}else {
						k = (leftOperandPop - 1) - rightOperandPop;
						remainder = false;
						}
					char myResult = Character.forDigit(k, 10);
					result.Push(myResult);
					resultSize++;
				}
			}
		}
					
		//Print the Result
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
			

		
		

		

