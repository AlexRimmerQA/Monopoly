/**
 * Created by Alex Rimmer on 11/04/2017.
 */
public class Ownable extends Square
{
	protected Player owner;
	protected int price;

	protected Ownable()
	{

	}

	protected Ownable(String name, int price)
	{
		super(name);
		this.owner = null;
		this.price = price;
	}

	public void SetOwner(Player owner) { this.owner = owner; }

	public Player GetOwner()
	{
		return this.owner;
	}

	public void SetPrice(int price) { this.price = price; }

	public int GetPrice() { return this.price; }
}
