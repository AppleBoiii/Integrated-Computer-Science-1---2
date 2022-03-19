public class LinkedList
{
    Node root = new Node(0); //pseudo-root
    public int size = 0;
    Node last = root;

    public LinkedList()
    {

    }

    private Node getNode(int n)
    {
        if(n == size-1) return last;

        Node current = root;
        for(int i=0;i<n+1;i++) current = current.next;
        return current;
    }

    public void add(int value)
    {
        last.next = new Node(value);
        last = last.next;

        size++;
    }

    public int delete(int index)
    {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();



        Node before = getNode(index-1);
        Node toDelete = before.next;
        Node after = toDelete.next;

        before.next = after;
        size--;

        if(toDelete == last) last = before;
        return toDelete.value;
    }

    public int size()
    {
        return size;
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
        System.out.println(ll);
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