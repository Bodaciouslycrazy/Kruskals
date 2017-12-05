import java.util.Comparator;

class EdgeComparator implements Comparator<Edge>{

	@Override
	public int compare(Edge e1, Edge e2) {
		return e1.GetWeight() - e2.GetWeight();
	}
	
	public boolean compareVertices(Edge e1, Edge e2)
	{
		return (e1.GetV1() == e2.GetV2() && e1.GetV2() == e2.GetV2()) || (e1.GetV1() == e2.GetV2() && e1.GetV2() == e2.GetV1());
	}
}
