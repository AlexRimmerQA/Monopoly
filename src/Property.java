import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 11/04/2017.
 */
public class Property extends Ownable
{
	private ArrayList<Integer> rentValues;
	private int rentStage;
	private int upgradeCost;

	public Property(String name, int price, int upgradeCost, ArrayList<Integer> rentValues)
	{
		super(name, price);
		this.upgradeCost = upgradeCost;
		this.rentValues = rentValues;
		this.rentStage = 0;
	}

	public void LandOn(Player player)
	{
		System.out.println(player.GetName() + " has landed on " + this.name);
		if(owner == null)
		{
			//allow to purchase the property
		}
		else
		{
			if(owner == player.GetName())
			{
				System.out.println("They own this property, they stay for free");
			}
			else
			{
				System.out.println("Somebody else owns this property. " + player.GetName() + " pays £" + rentValues.get(rentStage) + " to " + this.owner);
				//Remove money from player that landed on this
				//Add money to player who owns this
			}
		}
	}
}
