import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph implements GraphInterface {

    private List[] myVertices;
    private int numVertices = 0;
    private List[] verticesIn;
    private DataList[] authorList;

    public Graph(int numVertices) throws FileNotFoundException {
        this.numVertices = numVertices;
        myVertices = new List[numVertices];
        verticesIn = new List[numVertices];
        authorList = new DataList[numVertices];

        for (int i = 0; i < numVertices; i++) {
            myVertices[i] = new List();
            verticesIn[i] = new List();
            authorList[i] = new DataList();
        }

        populateAuthorList();


    }


    public void addOutEdges(int i, List n) {
        List outEdges = n;
        Node moverNode = outEdges.getFirst();
        boolean found = false;

        while (moverNode != null) {
            found = false;
            for (int j = 0; j < authorList.length; j++) {
                if (i != j && authorList[j].find(moverNode.data) != null) {
                    j = authorList.length - 1;
                    found = true;
                }
            }
            if (found == false) {
                outEdges.remove(moverNode.data);
            }
            moverNode = moverNode.next;
        }

        myVertices[i] = n;
    }


    public void removeInEdge(int i, String to) {

        verticesIn[i].remove(to);
    }

    public void removeOutEdge(int i, String to) {

        myVertices[i].remove(to);
    }


    public boolean hasInEdge(int i, String j) {
        if (verticesIn[i].find(j) == null)
            return false;
        return true;
    }

    public boolean hasOutEdge(int i, String j) {
        if (myVertices[i].find(j) == null)
            return false;
        return true;
    }

    public List outEdges(int i) {

        return myVertices[i];
    }


    public void addInEdges(int i, List n) {
        List inEdges = n;
        Node moverNode = inEdges.getFirst();
        boolean found = false;

        while (moverNode != null) {
            found = false;
            for (int j = 0; j < authorList.length; j++) {
                if (i != j && authorList[j].find(moverNode.data) != null) {
                    j = authorList.length - 1;
                    found = true;
                }
            }
            if (found == false) {
                inEdges.remove(moverNode.data);
            }
            moverNode = moverNode.next;
        }


        verticesIn[i] = inEdges;

    }

    public List inEdges(int i) {

        return verticesIn[i];
    }

    public void populateAuthorList() throws FileNotFoundException {
        //populate authorList
        File myData = new File("Data.txt");
        Scanner sc = new Scanner(myData);
        List genres = new List();
        List works = new List();
        List influenced = new List();
        List influencedBy = new List();
        String name = "";
        int count = 0;

        //creates DataList of authors
        while (sc.hasNextLine()) {
            String letter = sc.next();
            String line = sc.nextLine();
            //String letter = Character.toString(line.charAt(0));


            if (letter.equals("A")) {

                name = line;


            } else if (letter.equals("G")) {
                genres.add(line);
            } else if (letter.equals("W")) {
                works.add(line);
            } else if (letter.equals("O")) {
                influencedBy.add(line);
            } else if (letter.equals("I")) {
                influenced.add(line);
            } else if (letter.equals("/")) {
                authorList[count].add(name, genres, works, influenced, influencedBy);
                genres = new List();
                works = new List();
                influenced = new List();
                influencedBy = new List();
                name = "";
                count++;
            }

        }
    }
}

