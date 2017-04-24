import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class Deck
{
	private ArrayList<Card> cards;

	public Deck()
	{
		cards = new ArrayList<>();
	}

	public void AddCard(Card card)
	{
		cards.add(card);
	}

	public Card RemoveCard()
	{
		Card card = cards.get(0);
		cards.remove(0);
		return card;
	}

	public void ShuffleCards()
	{
		ArrayList<Card> shuffledCards = new ArrayList<>();
		for(int i = 0; i < cards.size(); i++)
		{
			int random = (int )(Math.random() * cards.size());
			shuffledCards.add(cards.get(random));
			cards.remove(random);
		}
		cards = shuffledCards;
	}
}
