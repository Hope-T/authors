import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
public class Main {

    public static void main(String[] args) throws IOException {
        run();

    }


    static void run() throws IOException {
        File myData = new File("Data.txt");
        Scanner sc = new Scanner(myData);
        DataList authors = new DataList();
        List genres = new List();
        List works = new List();
        List influenced = new List();
        List influencedBy = new List();
        String name = "";

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
                authors.add(name, genres, works, influenced, influencedBy);
                genres = new List();
                works = new List();
                influenced = new List();
                influencedBy = new List();
                name = "";
            }


        }

        //creates Graph
        Graph authorsLinked = new Graph(authors.size());

        //adds edges
        DataNode myAuthor = authors.getFirst();
        for (int i = 0; i < authors.size(); i++) {
            authorsLinked.addOutEdges(i, myAuthor.influencedBy);
            authorsLinked.addInEdges(i, myAuthor.influenced);
            myAuthor = myAuthor.next;
        }


        //creates file of authors
        adjacencyListAuthorList(authors);
        sc.close();

        //userInteraction
        Scanner userInput = new Scanner(System.in);
        System.out.println("Is there an author that you already like? Who? (Please type the name as written--that is, correctly--in the database.)");
        String authorName = userInput.nextLine();


        if (authors.find(" " + authorName) != null) {
            System.out.println("Yay! They are in our database.");
            System.out.print("Genres: ");
            List gen = authors.find(" " + authorName).genres;
            gen.print();
            System.out.println();
            System.out.print("Popular Works: ");
            List pop = authors.find(" " + authorName).popularWorks;
            pop.print();
            System.out.println();

            System.out.println("Would you like to search for authors they influenced (type 'I') or authors they were influenced by (type 'O')?");
            String userChoice = userInput.nextLine();


            if (userChoice.equals("I")) {
                DataNode findingI = authors.getFirst();
                int i = 0;

                while(findingI != null){
                    if(findingI.name.equals(" " + authorName))
                        break;
                    else {
                        findingI = findingI.next;
                        i++;
                    }

                }

                List inEdges = authorsLinked.inEdges(i);

                Node moverNode = inEdges.getFirst();
                while (moverNode != null) {
                    if (authors.find(moverNode.data) != null) {
                        DataNode newTopic = authors.find(moverNode.data);
                        System.out.println("Author: " + newTopic.name);
                        System.out.print("Genres: ");
                        newTopic.genres.print();
                        System.out.println();
                        System.out.print("Popular Works: ");
                        newTopic.popularWorks.print();
                        System.out.println();
                        System.out.println();
                    }

                    moverNode = moverNode.next;
                }

            } else if (userChoice.equals("O")) {
                DataNode findingI = authors.getFirst();
                int i = 0;

                while(findingI != null){
                    if(findingI.name.equals(" " + authorName))
                        break;
                    else {
                        findingI = findingI.next;
                        i++;
                    }

                }
                List outEdges = authorsLinked.outEdges(i);

                Node moverNode = outEdges.getFirst();
                while (moverNode != null) {
                    if (authors.find(moverNode.data) != null) {
                        DataNode newTopic = authors.find(moverNode.data);
                        System.out.println("Author: " + newTopic.name);
                        System.out.print("Genres: ");
                        newTopic.genres.print();
                        System.out.println();
                        System.out.print("Popular Works: ");
                        newTopic.popularWorks.print();
                        System.out.println();
                        System.out.println();

                    }

                    moverNode = moverNode.next;
                }
            } else {
                System.out.println("That's not a valid option. Goodbye!");
            }
        }


        else{
            System.out.println("Oh no, they aren't in our database! Add them into the file! Be sure to follow the formatting guidelines. I stands for authors they influenced, and O stands for authors they were influenced by.");
        }

        userInput.close();
    }




    static void adjacencyListAuthorList(DataList authors) throws IOException {

        PrintWriter authorList = new PrintWriter(new FileWriter("authorList.txt"));
        authorList.println("Author List");
        authorList.println();
        DataNode moverNode = authors.getFirst();
        while (moverNode != null) {
            authorList.println("Author: " + moverNode.name);


            authorList.println("Genres: ");
            Node genreNode = moverNode.genres.getFirst();
            while (genreNode != null) {
                authorList.print(genreNode.data + ", ");
                genreNode = genreNode.next;
            }
            authorList.println();

            authorList.println("Popular Works: ");
            Node workNode = moverNode.popularWorks.getFirst();
            while (workNode != null) {
                authorList.print(workNode.data + ", ");
                workNode = workNode.next;
            }
            authorList.println();

            authorList.println("Influenced: ");
            Node influencedNode = moverNode.influenced.getFirst();
            while (influencedNode != null) {
                authorList.print(influencedNode.data + ", ");
                influencedNode = influencedNode.next;
            }
            authorList.println();

            authorList.println("Influenced By: ");
            Node influencedByNode = moverNode.influencedBy.getFirst();
            while (influencedByNode != null) {
                authorList.print(influencedByNode.data + ", ");
                influencedByNode = influencedByNode.next;
            }
            authorList.println();

            moverNode = moverNode.next;
            authorList.println();
            authorList.println();

        }

        authorList.close();

    }

}