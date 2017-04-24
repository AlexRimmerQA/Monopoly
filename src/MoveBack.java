import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class MoveBack extends Action
{
	private int amount;

	public MoveBack(int amount)
	{
		this.amount = amount;
	}

	public void Execute(Player player, ArrayList<Square> board)
	{
		int playerPosition = player.GetPosition();

		if(playerPosition >= amount)
		{
			player.SetPosition(playerPosition - amount);
		}
		else
		{
			player.SetPosition(board.size() - (playerPosition - amount));
		}
		board.get(player.GetPosition()).LandOn(player, board);
	}
}
