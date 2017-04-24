import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class GetOutOfJailFree extends Action
{
	public void Execute(Player player, ArrayList<Square> board)
	{
		System.out.println(player.GetName() + " can keep this card until they need to use it");
	}
}
