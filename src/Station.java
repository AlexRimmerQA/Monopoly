import java.util.Map;

/**
 * Created by Alex Rimmer on 11/04/2017.
 */
public class Station extends Ownable
{
	static Map<String, Integer> playerOwned;

	public Station()
	{

	}

	public void LandOn(Player player)
	{
		System.out.println(player.GetName() + " has landed on " + this.name);
	}
}

