public class List {
    private Node first;
    private Node last;
    private int count;

    public List() {
        first = null;
        last = null;
        count = 0;
    }

    public Node getFirst(){
        return first;
    }

    public int size() {
        return count;
    }

    public void add(String data) {
        if(first == null) {
            first = new Node(data);
            last = first;
            count++;
            return;
        }
        else{
            Node newNode = first;

            while(newNode.next != null){
                newNode = newNode.next;
            }
            newNode.next = new Node(data);
            last = newNode.next;
            count++;
        }

    }

    public Node find(String toFind) {

        if(first == null)
            return null;

        else  if(first.data.equals(toFind)){
            return first;
        }
        else{
            Node newNode = first;
            while(newNode != null){
                if(newNode.data.equals(toFind)){
                    return newNode;
                }
                newNode = newNode.next;
            }
        }
        return null;
    }

    public void remove(String toRemove) {
        Node removingNode = first;
        Node previous = null;
        if(first == null)
            return;
        if(first.data.equals(toRemove)){
            count--;
            first = first.next;
            return;
        }
        else{
            while(removingNode != null && !(removingNode.data.equals(toRemove))){
                previous = removingNode;
                removingNode = removingNode.next;
            }
            if(removingNode == null)
                return;

            if(removingNode.data.equals(toRemove)) {
                count--;
                previous.next = removingNode.next;

            }

        }


    }


    public void print() {
        if (first == null)
            return;
        else
            System.out.print(first.data + ", ");

        printHelper(first.next);
    }

    public void printHelper(Node n) {
        if (n == null) {
            return;
        }
        else
            System.out.print(n.data + ", ");

        printHelper(n.next);


    }


}


class Node {
    String data;
    Node next, previous;

    public Node(String data) {
        this.data = data;
        next = null;
        previous = null;
    }


}