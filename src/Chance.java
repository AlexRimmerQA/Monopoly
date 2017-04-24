import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 12/04/2017.
 */
public class Chance extends Square
{
	private static Deck cards;
	private static Player[] players;
	private static ArrayList<Square> board;

	public Chance(Player[] players, ArrayList<Square> board)
	{
		this.name = "Chance";
		if(players == null)
		{
			this.players = players;
		}
		if(cards == null)
		{
			InitialiseDeck();
		}
		if(board == null)
		{
			this.board = board;
		}
	}

	public void InitialiseDeck()
	{
		//Add all the cards here
		cards = new Deck();
		cards.AddCard(new Card(this.GetName(), "Advance to Go (Collect £200)", new AdvanceTo("Go")));
		cards.AddCard(new Card(this.GetName(), "Advance to Trafalgar Square", new AdvanceTo("Trafalgar Square")));
		cards.AddCard(new Card(this.GetName(), "Advance to Pall Mall", new AdvanceTo("Pall Mall")));
		cards.AddCard(new Card(this.GetName(), "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.", new AdvanceToUtility()));
		cards.AddCard(new Card(this.GetName(), "Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", new AdvanceToRailroad()));
		cards.AddCard(new Card(this.GetName(), "Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", new AdvanceToRailroad()));
		cards.AddCard(new Card(this.GetName(), "Bank pays you dividend of £50", new AddMoney(50)));
		cards.AddCard(new Card(this.GetName(), "Get out of Jail Free – This card may be kept until needed", new GetOutOfJailFree()));
		cards.AddCard(new Card(this.GetName(), "Go Back 3 Spaces", new MoveBack(3)));
		cards.AddCard(new Card(this.GetName(), "Go to Jail – Go directly to Jail – Do not pass Go, do not collect £200", new SendToJail()));
		cards.AddCard(new Card(this.GetName(), "Make general repairs on all your property – For each house pay £25 – For each hotel £100", new BuildingRepairs(25, 100)));
		cards.AddCard(new Card(this.GetName(), "Pay poor tax of £15", new RemoveMoney(15)));
		cards.AddCard(new Card(this.GetName(), "Take a trip to Kings Cross Station - If you pass Go, collect £200", new AdvanceTo("Kings Cross Station")));
		cards.AddCard(new Card(this.GetName(), "Take a walk on the Mayfair – Advance token to Mayfair", new AdvanceTo("Mayfair")));
		cards.AddCard(new Card(this.GetName(), "You have been elected Chairman of the Board – Pay each player £50", new PayEachPlayer(50, players)));
		cards.AddCard(new Card(this.GetName(), "Your building loan matures - Collect £150", new AddMoney(150)));
		cards.AddCard(new Card(this.GetName(), "You have won a crossword competition - Collect £100", new AddMoney(100)));
		cards.ShuffleCards();
	}

	public void LandOn(Player player, ArrayList<Square> board)
	{
		System.out.println(player.GetName() + " has landed on " + this.name);
		System.out.println(player.GetName() + " takes a card from the community chest");
		Card theCard = cards.RemoveCard();
		System.out.println("Card: " + theCard.GetDescription());
		theCard.Execute(player, board);

		//if it isn't a get out of jail card, add it back to the cards
		if(theCard.GetAction() instanceof GetOutOfJailFree)
		{
			player.GiveJailCard(theCard);
		}
		else
		{
			cards.AddCard(theCard);
		}
		cards.ShuffleCards();
	}
}
