package tpdohp;

public class LatticeCoordinate {
	public int x;
	public int y;
	
	public LatticeCoordinate()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public LatticeCoordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean equal = false;
		
		if (o != null)
		{
			LatticeCoordinate coordinate = (LatticeCoordinate)o;
			equal = (coordinate.x == this.x && coordinate.y == this.y);
		}
		
		return equal;
	}
	
	@Override
	public int hashCode()
	{
		return this.x + this.y;
	}
}
