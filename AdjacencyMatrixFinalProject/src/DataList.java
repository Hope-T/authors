public class DataList {
    private DataNode first;
    private DataNode last;
    private int count;

    public DataList() {
        first = null;
        last = null;
        count = 0;
    }

    public DataNode getFirst(){
        return first;
    }

    public int size() {
        return count;
    }

    public void add(String name, List genres, List works, List influenced, List influencedBy) {
        if(first == null) {
            first = new DataNode(name, genres, works, influenced, influencedBy);
            last = first;
            count++;
            return;
        }
        else{
            DataNode newNode = first;

            while(newNode.next != null){
                newNode = newNode.next;
            }
            newNode.next = new DataNode(name, genres, works, influenced, influencedBy);
            last = newNode.next;
            count++;
        }

    }

    public DataNode find(String toFind) {
        if(first == null)
            return null;

        else  if(first.name.equals(toFind)){
            return first;
        }
        else{
            DataNode newNode = first;
            while(newNode != null){
                if(newNode.name.equals(toFind)){
                    return newNode;
                }
                newNode = newNode.next;
            }
        }
        return null;
    }

    public void remove(String toRemove) {
        DataNode removingNode = first;
        DataNode previous = null;
        if(first == null)
            return;
        if(first.name.equals(toRemove)){
            count--;
            first = first.next;
            return;
        }
        else{
            while(removingNode != null && !(removingNode.name.equals(toRemove))){
                previous = removingNode;
                removingNode = removingNode.next;
            }
            if(removingNode == null)
                return;

            if(removingNode.name.equals(toRemove)) {
                count--;
                previous.next = removingNode.next;

            }

        }


    }


    public void print() {
        if (first == null)
            return;
        else
            System.out.println("Author: " + first.name);
        System.out.println();
        System.out.println("Genres: ");
        first.genres.print();
        System.out.println();
        System.out.println();
        System.out.println("Popular works: ");
        first.popularWorks.print();
        System.out.println();
        System.out.println();
        System.out.println("Influenced: ");
        first.influenced.print();
        System.out.println();
        System.out.println();
        System.out.println("Influenced by: ");
        first.influencedBy.print();
        System.out.println();
        System.out.println();


        printHelper(first.next);
    }

    public void printHelper(DataNode n) {
        if (n == null) {
            return;
        }
        else {
            System.out.println("Author: " + n.name);
            System.out.println();
            System.out.println("Genres: ");
            n.genres.print();
            System.out.println();
            System.out.println();
            System.out.println("Popular works: ");
            n.popularWorks.print();
            System.out.println();
            System.out.println();
            System.out.println("Influenced: ");
            first.influenced.print();
            System.out.println();
            System.out.println();
            System.out.println("Influenced by: ");
            first.influencedBy.print();
            System.out.println();
            System.out.println();
        }
        System.out.println();

        printHelper(n.next);


    }


}


class DataNode {
    String name;
    List genres;
    List popularWorks;
    List influenced;
    List influencedBy;
    DataNode next, previous;

    public DataNode(String authorName, List authorGenres, List authorPopWorks, List influenced, List influencedBy) {
        name = authorName;
        genres = authorGenres;
        popularWorks = authorPopWorks;
        this.influenced = influenced;
        this.influencedBy = influencedBy;
        next = null;
        previous = null;
    }


}