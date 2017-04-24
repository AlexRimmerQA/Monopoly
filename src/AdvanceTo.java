import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 24/04/2017.
 */
public class AdvanceTo extends Action
{
	private String position;

	public AdvanceTo(String position)
	{
		this.position = position;
	}

	public void Execute(Player player, ArrayList<Square> board)
	{
		int playerPosition = player.GetPosition();
		int goToPosition = 0;

		for(Square square : board)
		{
			if(square.GetName().equalsIgnoreCase(this.position))
			{
				goToPosition = board.indexOf(square);
			}
		}

		//If player is ahead of where they need to go
		if(playerPosition > goToPosition)
		{
			// Calculate and move the amount needed around the board
			player.Move(board.size() - (playerPosition - goToPosition), board);
		}
		else // otherwise
		{
			//Move by how many squares ahead the player the square is
			player.Move(goToPosition - playerPosition, board);
		}

	}
}
