import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public abstract class Action
{
	protected Action() {}
	public abstract void Execute(Player player, ArrayList<Square> board);
}
