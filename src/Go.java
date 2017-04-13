import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 12/04/2017.
 */
public class Go extends Square
{
	public Go()
	{
		this.name = "Go";
	}

	public void LandOn(Player player, ArrayList<Square> board)
	{
		System.out.println(player.GetName() + " has landed on " + this.name);
	}
}
