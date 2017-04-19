import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Alex Rimmer on 11/04/2017.
 */
public class Monopoly
{
	private int numPlayers;
	Player[] players;

	private int currentPlayer = 0;

	private Scanner reader = new Scanner(System.in);

	ArrayList<Square> board;

	public void Initialise()
	{
		board = new ArrayList<>();

		board.add(new Go());
		board.add(new Property("Old Kent Road", 60, 50, "Brown", new ArrayList<>(Arrays.asList(2,10,30,90,160,250))));
		board.add(new CommunityChest());
		board.add(new Property("Whitechapel Road", 60, 50, "Brown", new ArrayList<>(Arrays.asList(4,20,60,180,320,450))));
		board.add(new Tax("Income Tax", 200));
		board.add(new Railroad("Kings Cross Station", 200));
		board.add(new Property("The Angel Islington", 100, 50, "Light Blue", new ArrayList<>(Arrays.asList(6,30,90,270,400,550))));
		board.add(new Chance());
		board.add(new Property("Euston Road", 100, 50, "Light Blue", new ArrayList<>(Arrays.asList(6,30,90,270,400,550))));
		board.add(new Property("Pentonville Road", 120, 50, "Light Blue", new ArrayList<>(Arrays.asList(8,40,100,300,450,600))));
		board.add(new Jail());
		board.add(new Property("Pall Mall", 140, 100, "Purple", new ArrayList<>(Arrays.asList(10,50,150,450,625,750))));
		board.add(new Utility("Electric Company", 150));
		board.add(new Property("Whitehall", 140, 100, "Purple", new ArrayList<>(Arrays.asList(10,50,150,450,625,750))));
		board.add(new Property("Northumberland Avenue", 160, 100, "Purple", new ArrayList<>(Arrays.asList(12,60,180,500,700,900))));
		board.add(new Railroad("Marylebone Station", 200));
		board.add(new Property("Bow Street", 180, 100, "Orange", new ArrayList<>(Arrays.asList(14,70,200,550,750,950))));
		board.add(new CommunityChest());
		board.add(new Property("Marlborough Street", 180, 100, "Orange", new ArrayList<>(Arrays.asList(14,70,200,550,750,950))));
		board.add(new Property("Vine Street", 200, 100, "Orange", new ArrayList<>(Arrays.asList(16,80,220,600,800,1000))));
		board.add(new FreeParking());
		board.add(new Property("The Strand", 220, 150, "Red", new ArrayList<>(Arrays.asList(18,90,250,700,875,1050))));
		board.add(new Chance());
		board.add(new Property("Fleet Street", 220, 150, "Red", new ArrayList<>(Arrays.asList(18,90,250,700,875,1050))));
		board.add(new Property("Trafalgar Square", 240, 150, "Red", new ArrayList<>(Arrays.asList(20,100,300,750,925,1100))));
		board.add(new Railroad("Fenchurch St Station", 200));
		board.add(new Property("Leicester Square", 260, 150, "Yellow", new ArrayList<>(Arrays.asList(22,110,330,800,975,1150))));
		board.add(new Property("Coventry Street", 260, 150, "Yellow", new ArrayList<>(Arrays.asList(22,110,330,800,975,1150))));
		board.add(new Utility("Waterworks", 150));
		board.add(new Property("Piccadilly", 280, 150, "Yellow", new ArrayList<>(Arrays.asList(22,120,360,850,1025,1200))));
		board.add(new GoToJail());
		board.add(new Property("Regent Street", 300, 200, "Green", new ArrayList<>(Arrays.asList(26,130,390,900,1100,1275))));
		board.add(new Property("Oxford Street", 300, 200, "Green", new ArrayList<>(Arrays.asList(26,130,390,900,1100,1275))));
		board.add(new CommunityChest());
		board.add(new Property("Bond Street", 320, 200, "Green", new ArrayList<>(Arrays.asList(28,150,450,1000,1200,1400))));
		board.add(new Railroad("Liverpool Street Station", 200));
		board.add(new Chance());
		board.add(new Property("Park Lane", 350, 200, "Blue", new ArrayList<>(Arrays.asList(35,175,500,1100,1300,1500))));
		board.add(new Tax("Super Tax", 100));
		board.add(new Property("Mayfair", 400, 200, "Blue", new ArrayList<>(Arrays.asList(50,200,600,1400,1700,2000))));
	}

	public Monopoly()
	{
		Initialise();

		System.out.println("Welcome to Monopoly");
		System.out.print("Number of players (2-4): ");
		numPlayers = Integer.parseInt(reader.nextLine());

		players = new Player[numPlayers];

		for(int i = 0; i < numPlayers; i++)
		{
			players[i] = new Player();
			System.out.print("Please enter Player " + (i+1) + "'s name: ");
			players[i].SetName(reader.nextLine());
		}

		/*for(int i = 0; i < 4; i++)
		{
			System.out.println(players[i].ToString());
		}*/
	}

	public void PlayerMenu()
	{

	}

	public boolean Run()
	{
		boolean rolledDice = false;
		int doubleCount = 0;

		//While the player can still roll the dice again
		while(rolledDice == false)
		{
			System.out.println("");
			System.out.println(players[currentPlayer].GetName() + "'s turn");
			System.out.println(players[currentPlayer].GetName() + " currently has £" + players[currentPlayer].GetMoney());

			//If the player is currently not in jail
			if(players[currentPlayer].GetJailed() == false)
			{
				PlayerMenu();
				System.out.println("Options: ");
				System.out.println(" - 1: Roll Dice");
				System.out.println(" - 2: Manage Properties");
				System.out.println(" - 3: Trade");
				switch (reader.nextLine())
				{
					case "1": // Roll dice
					{
						rolledDice = true;
						int die1 = RollDie(); // random num 1-6
						int die2 = RollDie(); // random num 1-6
						System.out.println("Rolled: " + die1 + " and " + die2);

						// If the player rolled a double
						if (die1 == die2)
						{
							doubleCount++; // count up that they got a double
							System.out.println(players[currentPlayer].GetName() + " rolled a double!");

							// If they haven't reached 3 doubles
							if (doubleCount < 3)
							{
								System.out.println(players[currentPlayer].GetName() + " can roll again");
								rolledDice = false;
							}
							else // 3 doubles in a row and the player goes to jail
							{
								//Find the jail square and set their position to that square
								System.out.println("Three doubles in a row? You must be cheating. Off to jail");
								for (Square square : board)
								{
									if (square.GetName() == "Jail")
									{
										int index = board.indexOf(square);
										players[currentPlayer].SetPosition(index);
										players[currentPlayer].SetJailed(true);
									}
								}
							}
						}
						else // reset the double counter if they roll normally
						{
							doubleCount = 0;
						}
						players[currentPlayer].Move(die1 + die2, board);
						break;
					}
					case "2": // Manage properties
					{

						break;
					}
					case "3": // Trade
					{

						break;
					}
				}
			}
			else // The player is currently in jail
			{
				System.out.println(players[currentPlayer].GetName() + " is currently in jail");
				// If the player hasn't been in jail for three turns
				if(players[currentPlayer].GetJailCounter() < 3)
				{
					// increase the amount of time in jail by another turn
					players[currentPlayer].SetJailCounter(players[currentPlayer].GetJailCounter() + 1);

					System.out.println("Options: ");
					System.out.println(" - 1: Roll Dice (must roll double to escape)");
					int optionNum = 2;

					if(players[currentPlayer].GetJailCards() > 0)
					{
						System.out.println(" - "+ optionNum +": Use get out of jail free card");
						optionNum++;
					}

					if(players[currentPlayer].GetMoney() >= 50)
					{
						System.out.println(" - " + optionNum + ": Pay £50 Bail");
						optionNum+=2;
					}

					switch (reader.nextLine())
					{
						case "1": // Roll dice
						{
							rolledDice = true;
							int die1 = RollDie(); // random num 1-6
							int die2 = RollDie(); // random num 1-6
							System.out.println("Rolled: " + die1 + " and " + die2);
							if(die1 == die2) // Free to go
							{
								System.out.println(players[currentPlayer].GetName() + " rolled a double and got out of jail");
								players[currentPlayer].Move(die1 + die2, board);
							}
							else
							{
								System.out.println(players[currentPlayer].GetName() + " is still in jail");
							}
						}
						case "2": // Possibly jail card or pay £50
						{
							if(optionNum == 4) // option 2 is pay £50
							{
								System.out.println(players[currentPlayer].GetName() + " has paid £50 bail to leave jail");
								players[currentPlayer].RemoveMoney(50);
								players[currentPlayer].SetJailed(false);
								players[currentPlayer].SetJailCounter(0);
							}
							else if(optionNum == 3 || optionNum == 5) // option 2 is a jail card
							{
								System.out.println(players[currentPlayer].GetName() + " has used a get out of jail free card to leave jail");
								players[currentPlayer].RemoveJailCard();
								players[currentPlayer].SetJailed(false);
								players[currentPlayer].SetJailCounter(0);
							}
						}
						case "3": // Can only be pay £50 if they were given the option for 3
						{
							if(optionNum == 5) // Make sure they were given both the jail card and money options
							{
								System.out.println(players[currentPlayer].GetName() + " has paid £50 bail to leave jail");
								players[currentPlayer].RemoveMoney(50);
								players[currentPlayer].SetJailed(false);
								players[currentPlayer].SetJailCounter(0);
							}
						}
					}
				}
				else // The player cant roll anymore because they have been in jail 3 turns
				{
					System.out.println("Options: ");
					System.out.println(" - 1: Pay £50 Bail");
					if(players[currentPlayer].GetJailCards() > 0)
					{
						System.out.println(" - 2: Use get out of jail free card");
					}

					switch(reader.nextLine())
					{
						case "1": // Pay £50
						{
							if(players[currentPlayer].GetMoney() >= 50)
							{
								System.out.println(players[currentPlayer].GetName() + " has paid £50 bail to leave jail");
								players[currentPlayer].RemoveMoney(50);
								players[currentPlayer].SetJailed(false);
								players[currentPlayer].SetJailCounter(0);
							}
							else
							{
								System.out.println(players[currentPlayer].GetName() + " does not have enough money to pay for this");
								boolean enoughMoney = players[currentPlayer].MoneyManagement(50,board);
								if(enoughMoney == true)
								{
									System.out.println(players[currentPlayer].GetName() + " has paid £50 bail to leave jail");
									players[currentPlayer].RemoveMoney(50);
									players[currentPlayer].SetJailed(false);
									players[currentPlayer].SetJailCounter(0);
								}
								else
								{
									System.out.println(players[currentPlayer].GetName() + " could not get enough money to pay their debts.");
									System.out.println(players[currentPlayer].GetName() + " is bankrupt");
									System.out.println(players[currentPlayer].GetName() + "'s things will be returned to the bank");
									players[currentPlayer].Bankrupt(board);
								}
							}
						}
						case "2":
						{
							if(players[currentPlayer].GetJailCards() > 0)
							{
								System.out.println(players[currentPlayer].GetName() + " has used a get out of jail free card to leave jail");
								players[currentPlayer].RemoveJailCard();
								players[currentPlayer].SetJailed(false);
								players[currentPlayer].SetJailCounter(0);
							}
						}
					}
				}
			}
		}

		if(currentPlayer == numPlayers-1) currentPlayer = 0; else currentPlayer++;
		return true;
	}

	private int RollDie()
	{
		return (int)(Math.random() * 6) + 1;
	}
}
