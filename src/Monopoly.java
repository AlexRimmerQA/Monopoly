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

	private int currentPlayer = 1;

	private Scanner reader = new Scanner(System.in);

	ArrayList<Square> board;

	public void Initialise()
	{
		board.add(new Go());
		board.add(new Property("Old Kent Road", 60, 50, new ArrayList<>(Arrays.asList(2,10,30,90,160,250))));
		board.add(new CommunityChest());
		board.add(new Property("Whitechapel Road", 60, 50, new ArrayList<>(Arrays.asList(4,20,60,180,320,450))));
		board.add(new Tax("Income Tax", 200));
		board.add(new Railroad("Kings Cross Station", 200));
		board.add(new Property("The Angel Islington", 100, 50, new ArrayList<>(Arrays.asList(6,30,90,270,400,550))));
		board.add(new Chance());
		board.add(new Property("Euston Road", 100, 50, new ArrayList<>(Arrays.asList(6,30,90,270,400,550))));
		board.add(new Property("Pentonville Road", 120, 50, new ArrayList<>(Arrays.asList(8,40,100,300,450,600))));
		board.add(new Property("Pall Mall", 140, 100, new ArrayList<>(Arrays.asList(10,50,150,450,625,750))));
		board.add(new Utility("Electric Company", 150));
		board.add(new Property("Whitehall", 140, 100, new ArrayList<>(Arrays.asList(10,50,150,450,625,750))));
		board.add(new Property("Northumberland Avenue", 160, 100, new ArrayList<>(Arrays.asList(12,60,180,500,700,900))));
		board.add(new Railroad("Marylebone Station", 200));
		board.add(new Property("Bow Street", 180, 100, new ArrayList<>(Arrays.asList(14,70,200,550,750,950))));
		board.add(new CommunityChest());
		board.add(new Property("Marlborough Street", 180, 100, new ArrayList<>(Arrays.asList(14,70,200,550,750,950))));
		board.add(new Property("Vine Street", 200, 100, new ArrayList<>(Arrays.asList(16,80,220,600,800,1000))));
		board.add(new Property("The Strand", 220, 150, new ArrayList<>(Arrays.asList(18,90,250,700,875,1050))));
		board.add(new Chance());
		board.add(new Property("Fleet Street", 220, 150, new ArrayList<>(Arrays.asList(18,90,250,700,875,1050))));
		board.add(new Property("Trafalgar Square", 240, 150, new ArrayList<>(Arrays.asList(20,100,300,750,925,1100))));
		board.add(new Railroad("Fenchurch St Station", 200));
		board.add(new Property("Leicester Square", 260, 150, new ArrayList<>(Arrays.asList(22,110,330,800,975,1150))));
		board.add(new Property("Coventry Street", 260, 150, new ArrayList<>(Arrays.asList(22,110,330,800,975,1150))));
		board.add(new Utility("Waterworks", 150));
		board.add(new Property("Piccadilly", 280, 150, new ArrayList<>(Arrays.asList(22,120,360,850,1025,1200))));
		board.add(new Property("Regent Street", 300, 200, new ArrayList<>(Arrays.asList(26,130,390,900,1100,1275))));
		board.add(new Property("Oxford Street", 300, 200, new ArrayList<>(Arrays.asList(26,130,390,900,1100,1275))));
		board.add(new CommunityChest());
		board.add(new Property("Bond Street", 320, 200, new ArrayList<>(Arrays.asList(28,150,450,1000,1200,1400))));
		board.add(new Railroad("Liverpool Street Station", 200));
		board.add(new Chance());
		board.add(new Property("Park Lane", 350, 200, new ArrayList<>(Arrays.asList(35,175,500,1100,1300,1500))));
		board.add(new Tax("Super Tax", 100));
		board.add(new Property("Mayfair", 400, 200, new ArrayList<>(Arrays.asList(50,200,600,1400,1700,2000))));
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

	public boolean Run()
	{
		boolean rolledDice = false;
		int doubleCount = 0;

		while(rolledDice == false)
		{
			System.out.println("Player " + currentPlayer + "'s turn");
			System.out.println("Options: ");
			System.out.println(" - 1: Roll Dice");
			System.out.println(" - 2: Manage Properties");
			System.out.println(" - 3: Trade");
			switch (reader.nextLine())
			{
				case "1":
					rolledDice = true;
					int die1 = RollDie();
					int die2 = RollDie();
					if (die1 == die2)
					{
						if (doubleCount < 3)
						{
							rolledDice = false;
						}
						else
						{
							//Player goes to jail
						}
					}
					//Move die1+die2 amount
					break;
				case "2":

					break;
				case "3":

					break;
			}
		}
		if(currentPlayer == numPlayers) currentPlayer = 1; else currentPlayer++;

		return true;
	}

	private int RollDie()
	{
		return (int)(Math.random() * 6) + 1;
	}
}
