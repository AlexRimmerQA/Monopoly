import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alex Rimmer on 11/04/2017.
 */
public class Player
{
	private static int nextId = 1;
	private int id;
	private String name;
	private int money;
	private boolean jailed;
	private ArrayList<Card> jailCards;
	private int jailCounter;
	private int boardPosition;
	private int lastRollTotal;
	private boolean bankrupt;

	public Player()
	{
		id = nextId;
		nextId++;
		money = 500;
		jailed = false;
		jailCounter = 0;
		jailCards = new ArrayList<>();
		bankrupt = false;
		boardPosition = 0;
	}

	public boolean HasJailCard()
	{
		if(!jailCards.isEmpty())
			return true;
		return false;
	}

	public void GiveJailCard(Card jailCard)
	{
		jailCards.add(jailCard);
	}

	public Card TakeJailCard()
	{
		if(HasJailCard())
		{
			Card card = jailCards.get(0);
			jailCards.remove(0);
			return card;
		}
		return null;
	}

	public void SetJailCounter(int jailCounter)
	{
		this.jailCounter = jailCounter;
	}

	public int GetJailCounter()
	{
		return this.jailCounter;
	}

	public boolean GetBankrupt()
	{
		return this.bankrupt;
	}

	public int GetMoney()
	{
		return this.money;
	}

	public void AddMoney(int money)
	{
		this.money += money;
	}

	public void RemoveMoney(int money)
	{
		this.money -= money;
	}

	public void SetName(String name)
	{
		this.name = name;
	}

	public String GetName()
	{
		return this.name;
	}

	public int GetLastRollTotal()
	{
		return this.lastRollTotal;
	}

	public void Move(int amount, ArrayList<Square> board)
	{
		lastRollTotal = amount;
		while(amount != 0)
		{
			boardPosition++;
			if(boardPosition == board.size())
			{
				boardPosition = 0; // Reset to GO
				this.money += 200;
				System.out.println(this.name + " has passed GO and has collected £200");
			}
			amount--;
		}
		board.get(boardPosition).LandOn(this, board);
	}

	public void SetPosition(int boardPosition)
	{
		this.boardPosition = boardPosition;
	}

	public int GetPosition()
	{
		return this.boardPosition;
	}

	public void SetJailed(boolean jailed)
	{
		this.jailed = jailed;
	}

	public boolean GetJailed()
	{
		return this.jailed;
	}

	public boolean MoneyManagement(int amountNeeded, ArrayList<Square> board)
	{
		boolean bankrupt = false;

		while(bankrupt == false)
		{
			Scanner reader = new Scanner(System.in);

			System.out.println("====== BANK ======");
			System.out.println("You are in need of money to pay off your debts");
			System.out.println("Choose from the following options to get some money:");
			System.out.println(" - 1. Mortgage Properties");
			System.out.println(" - 2. Sell Buildings");
			System.out.println(" - 3. Declare Bankruptcy");

			switch(reader.nextLine())
			{
				case "1": // Mortgage a property
				{
					//List all properties that have no buildings in their group and aren't already mortgaged
					ArrayList<Ownable> mortgageable = CanMortgage(board);
					System.out.println("You can mortgage the following properties");
					System.out.println("Please select on option: ");

					int counter = 1;
					for (Ownable ownable : mortgageable)
					{
						System.out.println(" - " + counter + ": " + ownable.GetName() + " for £" + ownable.GetPrice() / 2);
						counter++;
					}
					System.out.println(" - " + counter + ": Cancel");

					//Get what choice the user wants
					int choice = Integer.parseInt(reader.nextLine());

					//If they picked an option which wasn't cancel
					if (choice != counter)
					{
						Ownable toMortgage = mortgageable.get(choice - 1);
						toMortgage.SetMortgaged(true);
						this.money += toMortgage.GetPrice() / 2;
					}
					break;
				}
				case "2": // Sell buildings
				{
					// List all properties that have a house on them which can be sold
					ArrayList<Property> housedProperties = CanSellHouse(board);
					System.out.println("You can sell houses from the following properties");
					System.out.println("Please select on option: ");

					int counter = 1;
					for (Property property : housedProperties)
					{
						System.out.println(" - " + counter + ": " + property.GetName() + " for £" + property.GetUpgradeCost() / 2);
						counter++;
					}
					System.out.println(" - " + counter + ": Cancel");

					//Get what choice the user wants
					int choice = Integer.parseInt(reader.nextLine());

					//If they picked an option which wasn't cancel
					if (choice != counter)
					{
						Property theProperty = housedProperties.get(choice - 1);
						theProperty.SellHouse();
						this.money += theProperty.GetUpgradeCost() / 2;
					}
					break;
				}
				case "3": // Declare bankruptcy
				{
					System.out.println("Are you sure you wish to declare bankruptcy? (Y/N)");
					String input = reader.nextLine();
					if (input.equalsIgnoreCase("Y"))
					{
						bankrupt = true;
					}
					break;
				}
			}

			// if they now have enough money, quit out of money management
			if(this.money >= amountNeeded)
			{
				return true;
			}
		}
		return false; // bankrupt
	}

	private ArrayList<Property> CanSellHouse(ArrayList<Square> board)
	{
		ArrayList<Property> housedProperties = new ArrayList<>();

		for(Square square : board) // For each of the squares on the board
		{
			if(square instanceof Property) // if the square is a property
			{
				Property property = (Property)square;
				//If the property square has an owner and that owner is this player
				if(property.owner != null && property.owner == this)
				{
					//Get all the properties in the same group as this property
					ArrayList<Property> properties = GetPropertiesInGroup(property.GetPropertyGroup(), board);

					//Get the max value for RentStage, to determine what the most houses there are on any of the properties
					int max = 1; // must have a minimum of 1 house to be able to sell a house
					for(Property theProperty : properties)
					{
						if(theProperty.GetRentStage() > max)
						{
							max = theProperty.GetRentStage();
						}
					}

					// If this property's rent stage is equal to the max, it can sell a house
					if(property.GetRentStage() == max)
					{
						housedProperties.add(property);
					}
				}
			}
		}

		return housedProperties;
	}

	private ArrayList<Ownable> CanMortgage(ArrayList<Square> board)
	{
		ArrayList<Ownable> mortgageable = new ArrayList<>();

		for(Square square : board)
		{
			if(square instanceof Ownable)
			{
				Ownable ownable = (Ownable)square;
				//If the ownable square has an owner and that owner is this player
				if(ownable.owner != null && ownable.owner == this)
				{
					if (ownable instanceof Property)
					{
						Property property = (Property) ownable;
						// if any of the properties in the same group as this has a house. This property cannot be mortgaged
						ArrayList<Property> properties = GetPropertiesInGroup(property.GetPropertyGroup(), board);

						// check each property for a house, if any have a house move on to the next square
						for (Property groupProperty : properties)
						{
							if (property.GetRentStage() != 0)
							{
								continue;
							}
						}
					}
					//if the square is owned by this person && isn't already mortgaged
					if (ownable.GetMortgaged() == false)
					{
						mortgageable.add(ownable);
					}
				}
			}
		}
		return mortgageable;
	}

	public void Bankrupt(Player receiver, ArrayList<Square> board)
	{
		// receiver will get all of players stuff

		for(Square square : board) // for each square on the board
		{
			if(square instanceof Ownable) // if its an ownable square
			{
				Ownable ownable = (Ownable)square;
				if(ownable.owner == this) // if this player is the owner of the square
				{
					ownable.SetOwner(receiver); // change the owner of the square to the person who bankrupted this player
				}
			}
		}

		//They also receive all the players remaining money.
		receiver.AddMoney(this.money);
		this.money = 0;

		//Set this player as bankrupt
		this.bankrupt = true;
	}

	public void Bankrupt(ArrayList<Square> board)
	{
		// bank will get all the players stuff - set to null

		for(Square square : board) // for each square on the board
		{
			if(square instanceof Ownable) // if its an ownable square
			{
				Ownable ownable = (Ownable)square;
				if(ownable.owner == this) // if this player is the owner of the square
				{
					ownable.SetOwner(null); // change the owner of the square to null for the bank
				}
			}
		}

		//Set this player as bankrupt
		this.bankrupt = true;

	}

	public ArrayList<Property> GetPropertiesInGroup(String group, ArrayList<Square> board)
	{
		ArrayList<Property> properties = new ArrayList<>();

		for(Square square : board)
		{
			if(square instanceof Property)
			{
				Property property = (Property)square;
				if(property.GetPropertyGroup().equalsIgnoreCase(group))
				{
					properties.add(property);
				}
			}
		}

		return properties;
	}

	public String ToString()
	{
		return "Player " + this.id +
				"\n - Name: " + this.name +
				"\n - Money: " + this.money +
				"\n - Board Position: " + this.money +
				"\n - Jailed: " + this.jailed +
				"\n - Bankrupt: " + this.bankrupt;
	}
}
