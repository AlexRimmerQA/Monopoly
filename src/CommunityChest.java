import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 12/04/2017.
 */
public class CommunityChest extends Square
{
	private static Deck cards;
	private static Player[] players;
	private static ArrayList<Square> board;

	public CommunityChest(Player[] players, ArrayList<Square> board)
	{
		this.name = "Community Chest";
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
		cards.AddCard(new Card(this.GetName(), "Advance To Go (Collect £200)", new AdvanceTo("Go")));
		cards.AddCard(new Card(this.GetName(), "Bank error in your favor – Collect £200", new AddMoney(200)));
		cards.AddCard(new Card(this.GetName(), "Doctor's fees – Pay £50", new RemoveMoney(50)));
		cards.AddCard(new Card(this.GetName(), "From sale of stock you get £50", new AddMoney(50)));
		cards.AddCard(new Card(this.GetName(), "Get Out of Jail Free", new GetOutOfJailFree()));
		cards.AddCard(new Card(this.GetName(), "Go directly to jail – Do not pass Go – Do not collect £200", new SendToJail()));
		cards.AddCard(new Card(this.GetName(), "Grand Opera Night - Collect £50 from every player for opening night seats", new CollectEachPlayer(50, players)));
		cards.AddCard(new Card(this.GetName(), "Holiday fund matures - Receive £100", new AddMoney(100)));
		cards.AddCard(new Card(this.GetName(), "Income tax refund - Collect £20", new AddMoney(20)));
		cards.AddCard(new Card(this.GetName(), "It is your birthday - Collect £10 from each player", new CollectEachPlayer(10, players)));
		cards.AddCard(new Card(this.GetName(), "Life insurance matures - Collect £100", new AddMoney(100)));
		cards.AddCard(new Card(this.GetName(), "Pay hospital fees of £100", new RemoveMoney(100)));
		cards.AddCard(new Card(this.GetName(), "Pay school fees of £150", new RemoveMoney(150)));
		cards.AddCard(new Card(this.GetName(), "Receive £25 consultancy fee", new AddMoney(25)));
		cards.AddCard(new Card(this.GetName(), "You are assessed for street repairs - £40 per house - £115 per hotel", new BuildingRepairs(40, 115)));
		cards.AddCard(new Card(this.GetName(), "You have won second prize in a beauty contest - Collect £10", new AddMoney(10)));
		cards.AddCard(new Card(this.GetName(), "You inherit £100", new AddMoney(100)));
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
			cards.ShuffleCards();
		}
	}
}
