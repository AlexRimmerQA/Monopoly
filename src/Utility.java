import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alex Rimmer on 12/04/2017.
 */
public class Utility extends Ownable
{
	public Utility(String name, int price)
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
				System.out.println("They own this, they do not need to pay");
			}
			else
			{
				int rentAmount = 4 * player.GetLastRollTotal();
				String searchFor = "";

				if(this.name.equals("Waterworks"))
				{
					searchFor = "Electric Company";
				}
				else if(this.name.equals("Electric Company"))
				{
					searchFor = "Waterworks";
				}

				for(Square square : board)
				{
					if(square.GetName().equals(searchFor))
					{
						if(((Utility)square).GetOwner() == this.owner)
						{
							rentAmount = 10 * player.GetLastRollTotal();
						}
						break;
					}
				}

				System.out.println("Somebody else owns this. " + player.GetName() + " pays £" + rentAmount + " to " + this.owner.GetName());
				if(player.GetMoney() >= rentAmount)
				{
					player.RemoveMoney(rentAmount);
					owner.AddMoney(rentAmount);
				}
				else
				{
					System.out.println(player.GetName() + " does not have enough money to pay");
					boolean enoughMoney = player.MoneyManagement(rentAmount, board);
					if(enoughMoney == true)
					{
						System.out.println(player.GetName() + " now has enough money to pay");
						player.RemoveMoney(rentAmount);
						owner.AddMoney(rentAmount);
					}
					else // bankrupt
					{
						System.out.println(player.GetName() + " could not get enough money to pay their debts.");
						System.out.println(player.GetName() + " is bankrupt");
						System.out.println(owner.GetName() + " will receive all of " + player.GetName() + "'s things");
						player.Bankrupt(owner);
					}
				}
			}
		}
	}
}
