package dataStructuresProject2;

public class StackImpl {
	private Node head;
	int size;
	
	public StackImpl() {
		head = null;
	}
	
	public void Push(char data) {
		//System.out.println("I have entered Push() with data: " + data);
		if(head == null) {
			head = new Node(data);
			//System.out.println("I have pushed data:" + head.GetData());
		}else {
			Node newNode = new Node(data);
			newNode.setNext(head);
			head = newNode;
			//System.out.println("I have pushed:" + head.GetData());
		}
		size++;
	//System.out.println(data);
	}
	
	public Node Pop() {
		Node temp = head;
		head = head.getNext();
		//System.out.println("This is the head" + temp.GetData());
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
