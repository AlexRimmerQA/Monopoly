import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class CollectEachPlayer extends Action
{
	private int amount;
	private Player[] players;

	public CollectEachPlayer(int amount, Player[] players)
	{
		this.amount = amount;
		this.players = players;
	}

	public void Execute(Player player, ArrayList<Square> board)
	{
		for(Player thePlayer : players)
		{
			if(thePlayer.GetMoney() >= amount)
			{
				thePlayer.RemoveMoney(amount);
				player.AddMoney(amount);
			}
			else
			{
				System.out.println(thePlayer.GetName() + " does not have enough money to pay");
				boolean enoughMoney = thePlayer.MoneyManagement(amount, board);
				if(enoughMoney == true)
				{
					System.out.println(thePlayer.GetName() + " now has enough money to pay");
					player.RemoveMoney(amount);
				}
				else // bankrupt
				{
					System.out.println(thePlayer.GetName() + " could not get enough money to pay their debts.");
					System.out.println(thePlayer.GetName() + " is bankrupt");
					System.out.println(thePlayer.GetName() + "'s things will be returned to the bank");
					thePlayer.Bankrupt(board);
				}
			}
		}
	}
}
