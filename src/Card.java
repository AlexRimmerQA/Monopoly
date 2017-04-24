import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 19/04/2017.
 */
public class Card
{
	private String type;
	private String description;
	private Action action;

	public Action GetAction()
	{
		return this.action;
	}

	public String GetDescription()
	{
		return this.description;
	}

	public String GetType()
	{
		return this.type;
	}

	protected Card(String type, String description, Action action)
	{
		this.type = type;
		this.description = description;
		this.action = action;
	}

	public void Execute(Player player, ArrayList<Square> board)
	{
		action.Execute(player, board);
	}
}
