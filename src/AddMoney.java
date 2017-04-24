import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class AddMoney extends Action
{
	private int amount;

	public AddMoney(int amount)
	{
		this.amount = amount;
	}

	public void Execute(Player player, ArrayList<Square> board)
	{
		player.AddMoney(amount);
	}
}
