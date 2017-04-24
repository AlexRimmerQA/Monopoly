import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class BuildingRepairs extends Action
{
	private int houseAmount;
	private int hotelAmount;

	public BuildingRepairs(int houseAmount, int hotelAmount)
	{
		this.houseAmount = houseAmount;
		this.hotelAmount = hotelAmount;
	}

	public void Execute(Player player, ArrayList<Square> board)
	{
		int totalHouses = 0;
		int totalHotels = 0;

		for(Square square : board)
		{
			if(square instanceof Property)
			{
				 Property property = (Property) square;
				 if(property.GetOwner() == player)
				 {
				 	if(property.GetRentStage() == 5) // Hotel
					{
						//Add another hotel to the total
						totalHotels++;
					}
					else
					{
						// Add up to 4 houses to the total houses
						totalHouses += property.GetRentStage();
					}
				 }
			}
		}

		int totalOwed = (totalHotels * hotelAmount) + (totalHouses * houseAmount);

		System.out.println(player.GetName() + " owns " + totalHotels + " Hotels and " + totalHouses + " Houses");
		System.out.println(player.GetName() + " must pay " + totalOwed);

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
