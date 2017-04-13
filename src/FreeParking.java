import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 13/04/2017.
 */
public class FreeParking extends Square
{
	public FreeParking()
	{
		super("Free Parking");
	}

	public void LandOn(Player player, ArrayList<Square> board)
	{
		System.out.println(player.GetName() + " has landed on " + this.name);
		System.out.println("Nothing else happens");
	}
}
