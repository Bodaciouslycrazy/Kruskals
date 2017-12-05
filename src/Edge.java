
public class Edge {
	
	private int v1;
	private int v2;
	private int weight;
	
	public Edge(int a, int b, int c)
	{
		v1 = a;
		v2 = b;
		weight = c;
	}
	
	public int GetV1()
	{
		return v1;
	}
	
	public int GetV2()
	{
		return v2;
	}
	
	public int GetWeight()
	{
		return weight;
	}
}
