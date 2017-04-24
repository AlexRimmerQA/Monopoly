import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class PayEachPlayer extends Action
{
	private int amount;
	private Player[] players;

	public PayEachPlayer(int amount, Player[] players)
	{
		this.amount = amount;
		this.players = players;
	}

	public void Execute(Player player, ArrayList<Square> board)
	{
		int totalOwed = 0;
		for(Player thePlayer : players)
		{
			if(thePlayer != player)
			{
				thePlayer.AddMoney(amount);
				totalOwed += amount;
			}
		}

		if(player.GetMoney() >= totalOwed)
		{
			player.RemoveMoney(totalOwed);
		}
		else
		{
			System.out.println(player.GetName() + " does not have enough money to pay");
			boolean enoughMoney = player.MoneyManagement(totalOwed, board);
			if(enoughMoney == true)
			{
				System.out.println(player.GetName() + " now has enough money to pay");
				player.RemoveMoney(totalOwed);
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
