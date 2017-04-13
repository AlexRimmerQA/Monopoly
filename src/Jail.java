import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 13/04/2017.
 */
public class Jail extends Square
{
	public Jail()
	{
		super("Jail");
	}

	public void LandOn(Player player, ArrayList<Square> board)
	{
		System.out.println(player.GetName() + " has landed on " + this.name);
		System.out.println("But they are just visiting");
	}
}
