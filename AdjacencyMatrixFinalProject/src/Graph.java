import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph implements GraphInterface {

    boolean[][] myMatrix;
    DataList[] authorList;

    public Graph(int numVertices) throws FileNotFoundException {
        myMatrix = new boolean[numVertices][numVertices];
        authorList = new DataList[numVertices];

        //no edges yet
        for (int i = 0; i < numVertices; i++) {
            authorList[i] = new DataList();
            for (int j = 0; j < numVertices; j++) {
                myMatrix[i][j] = false;
            }
        }

      populateAuthorList();


        }



    public void addOutEdges(int i, List n) {

        Node moverNode = n.getFirst();
        while (moverNode != null) {
            for (int k = 0; k < authorList.length; k++) {
                if (authorList[k].getFirst() != null && i != k && moverNode.data.equals(authorList[k].getFirst().name)) {
                    myMatrix[k][i] = true;

                }
            }

            moverNode = moverNode.next;
        }


    }


    public void removeInEdge(int i, String to) {
        for (int k = 0; k < authorList.length; k++) {
            if (authorList[k].getFirst() != null && i != k && to.equals(authorList[k].getFirst().name.substring(1))) {
                myMatrix[i][k] = false;
            }
        }

    }

    public void removeOutEdge(int i, String to) {

        for (int k = 0; k < authorList.length; k++) {
            if (authorList[k].getFirst() != null && i != k && to.equals(authorList[k].getFirst().name.substring(1))) {
                myMatrix[k][i] = false;
            }
        }

    }


    public boolean hasInEdge(int i, String j) {

        for (int k = 0; k < authorList.length; k++) {
            if (authorList[k].getFirst() != null && i != k && j.equals(authorList[k].getFirst().name.substring(1))) {
                if (myMatrix[i][k] == true) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean hasOutEdge(int i, String j) {
        for (int k = 0; k < authorList.length; k++) {
            if (authorList[k].getFirst() != null && i != k && j.equals(authorList[k].getFirst().name.substring(1))) {


                if (myMatrix[k][i] == true) {
                    return true;
                }
            }
        }
        return false;

    }

    public List outEdges(int i) {
        String author = authorList[i].getFirst().name;

        List outEdges = new List();
        for (int j = 0; j < authorList.length; j++) {
            if (myMatrix[j][i] == true) {
                outEdges.add(authorList[j].getFirst().name);
            }
        }


        return outEdges;

    }

    public void addInEdges(int i, List n) {
        //go through list
        //add each edge

        Node moverNode = n.getFirst();
        while (moverNode != null) {
            for (int k = 0; k < authorList.length; k++) {

                if (authorList[k].getFirst() != null && moverNode.data.equals(authorList[k].getFirst().name)) {
                    myMatrix[i][k] = true;
                }

            }

            moverNode = moverNode.next;


        }

    }

    public List inEdges(int i) {
        String author = authorList[i].getFirst().name;
        List inEdges = new List();
        for (int j = 0; j < authorList.length; j++) {
            if (myMatrix[i][j] == true) {
                inEdges.add(authorList[j].getFirst().name);
            }
        }
        return inEdges;

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

