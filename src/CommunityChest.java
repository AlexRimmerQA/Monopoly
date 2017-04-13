import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 12/04/2017.
 */
public class CommunityChest extends Square
{
	public CommunityChest()
	{
		this.name = "Community Chest";
	}

	public void LandOn(Player player, ArrayList<Square> board)
	{
		System.out.println(player.GetName() + " has landed on " + this.name);
	}
}
