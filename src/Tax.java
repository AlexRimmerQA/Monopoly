/**
 * Created by Alex Rimmer on 12/04/2017.
 */
public class Tax extends Square
{
	private int taxAmount;

	public Tax(String name, int taxAmount)
	{
		this.name = name;
		this.taxAmount = taxAmount;
	}

	public void LandOn(Player player)
	{
		// Player loses tax amount;
	}

	public void SetTax(int taxAmount)
	{
		this.taxAmount = taxAmount;
	}

	public int GetTax()
	{
		return this.taxAmount;
	}
}
