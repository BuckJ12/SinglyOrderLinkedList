class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}



class SortedLinkedList {
    private Node head;
    private Node tail;

    public SortedLinkedList() {
        head = null;
        tail = null;
    }
    
   

    public void insertFront(String name) {
        Node newNode = new Node(name);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertRear(String name) {
        Node newNode = new Node(name);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void insertInOrder(String name) {
        Node newNode = new Node(name);
        if (head == null || name.compareTo(head.data) <= 0) {
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else {
            Node current = head;
            while (current.next != null && name.compareTo(current.next.data) > 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) {
                tail = newNode;
            }
        }
    }

    public String removeFront() {
    	Node n=head;
        if (head != null) {
            if (head == tail) {
                tail = null;
            }
            head = head.next;
        }else {
        	return "No data";
        }
        return n.data;
    }

    public boolean remove(String name) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(name)) {
            if (head == tail) {
                tail = null;
            }
            head = head.next;
            return true;
        }
        Node current = head;
        while (current.next != null && !current.next.data.equals(name)) {
            current = current.next;
        }
        if (current.next != null) {
            if (current.next == tail) {
                tail = current;
            }
            current.next = current.next.next;
            return true;
        }
        return false;
    }

    public boolean isInOrder() {
        Node current = head;
        while (current != null && current.next != null) {
            if (current.data.compareTo(current.next.data) > 0) {
                return false;
            }
            current = current.next;
        }
        return true;
    }
    
    public SortedLinkedList Sort() {
    	
    	SortedLinkedList SortedList = new SortedLinkedList();
    	Node curr = head;
		while (curr!=null) {
			SortedList.insertInOrder(curr.data);
			curr = curr.next;
		}
    	return SortedList;
    }

    public String getList() {
        if (head == null) {
            return "List is empty";
        }
        
        StringBuilder list = new StringBuilder();
        Node current = head;
        while (current != null) {
            list.append(current.data);
            if (current.next != null) {
                list.append(" -> ");
            }
            current = current.next;
        }
        return list.toString();
    }


}