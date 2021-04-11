package dataStructuresProject2;

public class Node {
	//fields
	Node next;
	char data;
	
	
	//constructor 
	public Node(char dataValue) {
		next = null;
		data = dataValue;
	}
	
	
	public char GetData() {
		return data;
	}
	
	public void SetData(char dataValue) {
		data = dataValue;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node nextValue) {
		
		next = nextValue;
		
	}
}
