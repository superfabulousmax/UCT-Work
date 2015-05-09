import java.util.LinkedList;
import java.util.List;

/**
 * This is a class for the vertex in the Graph structure
 * @author sinead urisohn
 * @version 9/05/2015
 *
 */
public class Vertex {

	//fields
	private String name;//name of the vertex
	private List<Edge> adjacent;//adjacent verticess
	private Vertex prev;//previous vertex on path
	private int scratch;
	
	//constructor

	public Vertex(String name) {
		this.name = name;
		this.adjacent = new LinkedList<Edge>();
		reset();
	}
	
	public void reset()
	{
		prev=null;
		scratch=0;
	}
	
	//getters and setters
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Edge> getAdjacent() {
		return adjacent;
	}
	public void setAdjacent(List<Edge> adjacent) {
		this.adjacent = adjacent;
	}
	public Vertex getPrev() {
		return prev;
	}
	public void setPrev(Vertex prev) {
		this.prev = prev;
	}
	public int getScratch() {
		return scratch;
	}
	public void setScratch(int scratch) {
		this.scratch = scratch;
	}
}
