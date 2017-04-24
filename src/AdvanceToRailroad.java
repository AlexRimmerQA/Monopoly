import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class AdvanceToRailroad extends Action
{
	public void Execute(Player player, ArrayList<Square> board)
	{
		int playerPosition = player.GetPosition();

		// Find where the next utility is
		int railroadPosition = playerPosition;

		//Breaks out of the while when the next railroad is found
		while(!(board.get(railroadPosition) instanceof Railroad))
		{
			railroadPosition++;
			if(railroadPosition == board.size())
			{
				railroadPosition = 0; // Reset to GO
			}
		}

		// If the railroad isn't owned or is owned by the player
		Railroad railroad = (Railroad)board.get(railroadPosition);
		if(railroad.GetOwner() == null || railroad.GetOwner() == player)
		{
			// Move the player to that railroad

			if(playerPosition > railroadPosition)
			{
				// Calculate and move the amount needed around the board
				player.Move(board.size() - (playerPosition - railroadPosition), board);
			}
			else // otherwise
			{
				//Move by how many squares ahead the player the square is
				player.Move(railroadPosition - playerPosition, board);
			}
		}
		else // the railroad is owned and not by the player
		{
			// Move them directly as to not activate the rent and give £200 if necessary
			if(playerPosition > railroadPosition)
			{
				player.AddMoney(200);
				System.out.println(player.GetName() + " has passed GO and has collected £200");
			}

			player.SetPosition(railroadPosition);
			// Rent to pay = 10 x roll
			int rentAmount = 0;

			for(Square square : board)
			{
				if(square.GetName().contains(" Station"))
				{
					if (((Railroad) square).GetOwner() == railroad.GetOwner())
					{
						if(rentAmount == 0) // first station
							rentAmount = 25;
						else
							rentAmount *= 2;
					}
				}
			}

			rentAmount *= 2;

			System.out.println("Somebody else owns this railroad. " + player.GetName() + " pays £" + rentAmount + " to " + railroad.GetOwner().GetName());

			if(player.GetMoney() >= rentAmount)
			{
				player.RemoveMoney(rentAmount);
				railroad.GetOwner().AddMoney(rentAmount);
			}
			else
			{
				System.out.println(player.GetName() + " does not have enough money to pay");
				boolean enoughMoney = player.MoneyManagement(rentAmount, board);
				if(enoughMoney == true)
				{
					System.out.println(player.GetName() + " now has enough money to pay");
					player.RemoveMoney(rentAmount);
					railroad.GetOwner().AddMoney(rentAmount);
				}
				else // bankrupt
				{
					System.out.println(player.GetName() + " could not get enough money to pay their debts.");
					System.out.println(player.GetName() + " is bankrupt");
					System.out.println(railroad.GetOwner().GetName() + " will receive all of " + player.GetName() + "'s things");
					player.Bankrupt(railroad.GetOwner(), board);
				}
			}
		}
	}
}
