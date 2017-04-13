import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alex Rimmer on 11/04/2017.
 */
public class Property extends Ownable
{
	private ArrayList<Integer> rentValues;
	private int rentStage;
	private int upgradeCost;
	private String propertyGroup;

	public Property(String name, int price, int upgradeCost, String propertyGroup, ArrayList<Integer> rentValues)
	{
		super(name, price);
		this.upgradeCost = upgradeCost;
		this.propertyGroup = propertyGroup;
		this.rentValues = rentValues;
		this.rentStage = 0;
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
				System.out.println("They own this property, they stay for free");
			}
			else
			{
				System.out.println("Somebody else owns this property. " + player.GetName() + " pays £" + rentValues.get(rentStage) + " to " + this.owner.GetName());
				if(player.GetMoney() >= rentValues.get(rentStage))
				{
					player.RemoveMoney(rentValues.get(rentStage));
					owner.AddMoney(rentValues.get(rentStage));
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
