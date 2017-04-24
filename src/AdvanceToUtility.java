import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class AdvanceToUtility extends Action
{
	public void Execute(Player player, ArrayList<Square> board)
	{
		int playerPosition = player.GetPosition();

		// Find where the next utility is
		int utilityPosition = playerPosition;

		//Breaks out of the while when the next utility is found
		while(!(board.get(utilityPosition) instanceof Utility))
		{
			utilityPosition++;
			if(utilityPosition == board.size())
			{
				utilityPosition = 0; // Reset to GO
			}
		}

		// If the utility isn't owned or is owned by the player
		Utility utility = (Utility)board.get(utilityPosition);
		if(utility.GetOwner() == null || utility.GetOwner() == player)
		{
			// Move the player to that square collecting £200 if necessary

			if(playerPosition > utilityPosition)
			{
				// Calculate and move the amount needed around the board
				player.Move(board.size() - (playerPosition - utilityPosition), board);
			}
			else // otherwise
			{
				//Move by how many squares ahead the player the square is
				player.Move(utilityPosition - playerPosition, board);
			}
		}
		else // the utility is owned and not by the player
		{
			// Move them directly as to not activate the rent and give £200 if necessary
			if(playerPosition > utilityPosition)
			{
				player.AddMoney(200);
				System.out.println(player.GetName() + " has passed GO and has collected £200");
			}

			player.SetPosition(utilityPosition);
			// Rent to pay = 10 x roll
			int rentAmount = 10 * player.GetLastRollTotal();

			System.out.println("Somebody else owns this. " + player.GetName() + " pays £" + rentAmount + " to " + utility.GetOwner().GetName());

			if(player.GetMoney() >= rentAmount)
			{
				player.RemoveMoney(rentAmount);
				utility.GetOwner().AddMoney(rentAmount);
			}
			else
			{
				System.out.println(player.GetName() + " does not have enough money to pay");
				boolean enoughMoney = player.MoneyManagement(rentAmount, board);
				if(enoughMoney == true)
				{
					System.out.println(player.GetName() + " now has enough money to pay");
					player.RemoveMoney(rentAmount);
					utility.GetOwner().AddMoney(rentAmount);
				}
				else // bankrupt
				{
					System.out.println(player.GetName() + " could not get enough money to pay their debts.");
					System.out.println(player.GetName() + " is bankrupt");
					System.out.println(utility.GetOwner().GetName() + " will receive all of " + player.GetName() + "'s things");
					player.Bankrupt(utility.GetOwner(), board);
				}
			}
		}
	}
}
