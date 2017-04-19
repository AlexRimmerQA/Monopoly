import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 13/04/2017.
 */
public class GoToJail extends Square
{
	public GoToJail()
	{
		super("Go To Jail");
	}

	public void LandOn(Player player, ArrayList<Square> board)
	{
		System.out.println(player.GetName() + " has landed on " + this.name);
		System.out.println("Go straight to jail. Do not pass GO. Do not collect £200");
		System.out.println(player.GetName() + " has gone to jail");

		for(Square square : board)
		{
			if(square.GetName() == "Jail")
			{
				player.SetPosition(board.indexOf(square));
				player.SetJailed(true);
			}
		}

	}
}
