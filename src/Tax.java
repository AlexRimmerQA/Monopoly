import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 12/04/2017.
 */
public class Tax extends Square
{
	private int taxAmount;

	public Tax(String name, int taxAmount)
	{
		super(name);
		this.taxAmount = taxAmount;
	}

	public void LandOn(Player player, ArrayList<Square> board)
	{
		System.out.println(player.GetName() + " has landed on " + this.name);
		if(player.GetMoney() >= this.taxAmount)
		{
			System.out.println(player.GetName() + " pays £" + this.taxAmount + " tax");
		}
		else
		{
			System.out.println(player.GetName() + " does not have enough money to pay the tax");
			// Money management menu
		}
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
