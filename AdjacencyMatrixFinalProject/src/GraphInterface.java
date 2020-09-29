import java.io.FileNotFoundException;

public interface GraphInterface {


    public void addOutEdges(int i, List n);
    public void removeInEdge(int i, String to);
    public void removeOutEdge(int i, String to);
    public boolean hasInEdge(int i, String j);
    public boolean hasOutEdge(int i, String j);
    public List outEdges(int i);
    public void addInEdges(int i, List n);
    public List inEdges(int i);
    public void populateAuthorList() throws FileNotFoundException;



}
