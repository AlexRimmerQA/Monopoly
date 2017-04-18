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
			player.RemoveMoney(this.taxAmount);
		}
		else
		{
			System.out.println(player.GetName() + " does not have enough money to pay");
			boolean enoughMoney = player.MoneyManagement(this.taxAmount, board);
			if(enoughMoney == true)
			{
				System.out.println(player.GetName() + " now has enough money to pay");
				player.RemoveMoney(this.taxAmount);
			}
			else // bankrupt
			{
				System.out.println(player.GetName() + " could not get enough money to pay their debts.");
				System.out.println(player.GetName() + " is bankrupt");
				System.out.println(player.GetName() + "'s things will be returned to the bank");
				player.Bankrupt();
			}
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
