/**
 * This is a class for the vertex in the Graph structure
 * @author sinead urisohn
 * @version 9/05/2015
 *
 */
public class Edge {
	
	private Vertex dest;//second vertex in the edge
	private int cost;//cost of traversing edge
	
	public Edge(Vertex dest, int cost) {
		this.dest = dest;
		this.cost = cost;
	}
	//getters and setters
	public Vertex getDest() {
		return dest;
	}
	public void setDest(Vertex dest) {
		this.dest = dest;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
}
