/**
 * Created by Alex Rimmer on 11/04/2017.
 */
public class Ownable extends Square
{
	protected Player owner;
	protected int price;
	protected boolean mortgaged;

	protected Ownable()
	{

	}

	protected Ownable(String name, int price)
	{
		super(name);
		this.owner = null;
		this.price = price;
		this.mortgaged = false;
	}

	public void SetOwner(Player owner)
	{
		this.owner = owner;
	}

	public Player GetOwner()
	{
		return this.owner;
	}

	public void SetPrice(int price)
	{
		this.price = price;
	}

	public int GetPrice()
	{
		return this.price;
	}

	public void SetMortgaged(boolean mortgaged)
	{
		this.mortgaged = mortgaged;
	}

	public boolean GetMortgaged()
	{
		return this.mortgaged;
	}
}
