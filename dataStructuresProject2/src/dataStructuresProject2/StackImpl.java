package dataStructuresProject2;

public class StackImpl {
	private Node head;
	int size;
	
	public StackImpl() {
		head = null;
	}
	
	public void Push(char data) {
		
		if(head == null) {
			head = new Node(data);
			
		}else {
			Node newNode = new Node(data);
			newNode.setNext(head);
			head = newNode;
			
		}
		size++;
	
	}
	
	public Node Pop() {
		Node temp = head;
		head = head.getNext();
		
		return temp;
	}
	
	public char getHead() {
		return head.GetData();
	}
	
	public String toString() {
		String result = new String();
		Node temp = head;
		while(temp != null) {
			result = result + String.valueOf(temp.GetData()).toString() + "\n";
			temp = temp.getNext();
		}
		
		return result;
	}
	
}
