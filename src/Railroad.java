import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alex Rimmer on 12/04/2017.
 */
public class Railroad extends Ownable
{
	// Rent payed/received when only 1 railroad is owned
	// Each extra railroad owned doubles the value

	public Railroad(String name, int price)
	{
		super(name, price);
	}

	public void LandOn(Player player, ArrayList<Square> board)
	{
		System.out.println(player.GetName() + " has landed on " + this.name);
		if(owner == null)
		{
			if(player.GetMoney() >= this.price)
			{
				Scanner reader = new Scanner(System.in);
				System.out.println("Do you wish to purchase " + this.name + " for £" + this.price + "?");
				System.out.println(" - 1: Yes");
				System.out.println(" - 2: No");
				switch(reader.nextLine())
				{
					case "1":
						System.out.println(player.GetName() + " is now the owner of " + this.name);
						this.owner = player;
						player.RemoveMoney(this.price);
						break;
					case "2":
						System.out.println(player.GetName() + " decided against purchasing " + this.name);
						break;
				}
			}
			else
			{
				System.out.println(player.GetName() + " could not afford " + this.name);
			}
		}
		else
		{
			if(owner == player)
			{
				System.out.println("They own this station, they do not need to pay");
			}
			else
			{
				int rentAmount = 0;
				for(Square square : board)
				{
					if(square.GetName().contains(" Station"))
					{
						if (((Railroad) square).GetOwner() == this.owner)
						{
							if(rentAmount == 0) // first station
								rentAmount = 25;
							else
								rentAmount *= 2;
						}
					}
				}

				System.out.println("Somebody else owns this station. " + player.GetName() + " pays £" + rentAmount + " to " + this.owner.GetName());
				if(player.GetMoney() >= rentAmount)
				{
					player.RemoveMoney(rentAmount);
					owner.AddMoney(rentAmount);
				}
				else
				{
					System.out.println(player.GetName() + " does not have enough money to pay");
					// Manage money to get enough to pay rent
				}
			}
		}
	}
}
