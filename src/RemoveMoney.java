import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class RemoveMoney extends Action
{
	private int amount;

	public RemoveMoney(int amount)
	{
		this.amount = amount;
	}

	public void Execute(Player player, ArrayList<Square> board)
	{
		if(player.GetMoney() >= amount)
		{
			player.RemoveMoney(amount);
		}
		else
		{
			System.out.println(player.GetName() + " does not have enough money to pay");
			boolean enoughMoney = player.MoneyManagement(amount, board);
			if(enoughMoney == true)
			{
				System.out.println(player.GetName() + " now has enough money to pay");
				player.RemoveMoney(amount);
			}
			else // bankrupt
			{
				System.out.println(player.GetName() + " could not get enough money to pay their debts.");
				System.out.println(player.GetName() + " is bankrupt");
				System.out.println(player.GetName() + "'s things will be returned to the bank");
				player.Bankrupt(board);
			}
		}
	}
}
