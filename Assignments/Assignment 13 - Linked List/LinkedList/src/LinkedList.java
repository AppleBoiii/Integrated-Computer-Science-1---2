public class LinkedList //implements ICSList
{
    Node root; //pseudo-root
    private int size;
    Node last;

    public LinkedList() 
    {
        root = new Node(0);
        size = 0;
        last = root;
    }

    private Node getNode(int n) 
    {
        if (n == size - 1) return last;

        Node current = root;
        for (int i = 0; i < n + 1; i++) current = current.next;
        return current;
    }

    public void add(int value) 
    {
        last.next = new Node(value);
        last = last.next;

        size++;
    }

    public void add(int value, int pos) 
    {
        Node current = getNode(pos-1);
        Node after = current.next;
        current.next = new Node(value);
        current.next.next = after;

        size++;
    }

    public int delete(int index) 
    {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node before = getNode(index - 1);
        Node toDelete = before.next;
        Node after = toDelete.next;

        before.next = after;
        size--;

        if (toDelete == last) last = before;
        return toDelete.value;
    }

    public int size() 
    {
        return size;
    }

    public int get(int value) 
    {
        return value;
    }

    public void clear() 
    {
        root.next = null;
        last = root;
        size = 0;
    }

    public boolean contains(int value) 
    {
        return false;
    }

    public int indexOf(int value) 
    {
        return 0;
    }

    public boolean isEmpty() 
    {
        return size == 0;
    }


    public String toString()
    {
        return root.next+"";
    }

    public static void main(String[] args)
    {
        LinkedList ll = new LinkedList();
        ll.add(524);
        ll.add(69);
        ll.add(200);

        System.out.println(ll.size());

        ll.add(420, 1);

        System.out.println(ll.size());

    }
}

class Node
{
    int value;
    Node next;
    Node prev;

    public Node(int value)
    {
        this.value = value;
        next = null;
        prev = null;
    }

    public String toString()
    {
        String outString = "[";

        Node current = this;
        while(current != null)
        {
            outString += current.value;
            current = current.next;
            if(current != null) outString += ", ";
        }

        return outString+"]";
        // return value+", "+next;
    }

}
