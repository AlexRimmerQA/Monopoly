import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class SendToJail extends Action
{
	public void Execute(Player player, ArrayList<Square> board)
	{
		for(Square square : board)
		{
			if(square.GetName().equalsIgnoreCase("Jail"))
			{
				player.SetPosition(board.indexOf(square));
				player.SetJailed(true);
			}
		}
	}
}
