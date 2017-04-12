/**
 * Created by Alex Rimmer on 11/04/2017.
 */
public class Square
{
	// The name of the square which will be output to the user.
	protected String name;

	// Holds references to the square after and the square previous to this square.
	//private Square nextSquare;
	//private Square prevSquare;

	protected Square()
	{

	}

	protected Square(String name)
	{
		this.name = name;
	}

	// Each type of square will have some sort of function when it is landed on.
	public void LandOn(Player player)
	{

	}

	//Set and get the name of the square.
	public void SetName(String name) { this.name = name; }
	public String GetName()
	{
		return this.name;
	}
}
