import java.util.ArrayList;

/**
 * Created by Alex Rimmer on 11/04/2017.
 */
public class Player
{
	private static int nextId = 1;
	private int id;
	private String name;
	private int money;
	private boolean jailed;
	private int boardPosition;
	private int lastRollTotal;

	public Player()
	{
		id = nextId;
		nextId++;

		money = 500;
		jailed = false;
		boardPosition = 0;
	}

	public int GetMoney()
	{
		return this.money;
	}

	public void AddMoney(int money)
	{
		this.money += money;
	}

	public void RemoveMoney(int money)
	{
		this.money -= money;
	}

	public void SetName(String name)
	{
		this.name = name;
	}

	public String GetName()
	{
		return this.name;
	}

	public int GetLastRollTotal()
	{
		return lastRollTotal;
	}

	public void Move(int amount, ArrayList<Square> board)
	{
		lastRollTotal = amount;
		while(amount != 0)
		{
			boardPosition++;
			if(boardPosition == board.size())
			{
				boardPosition = 0; // Reset to GO
				this.money += 200;
				System.out.println(this.name + " has passed GO and has collected £200");
			}
			amount--;
		}
		board.get(boardPosition).LandOn(this, board);
	}

	public void SetPosition(int boardPosition)
	{
		this.boardPosition = boardPosition;
	}

	public int GetPosition()
	{
		return this.boardPosition;
	}

	public void SetJailed(boolean jailed)
	{
		this.jailed = jailed;
	}

	public boolean GetJailed()
	{
		return this.jailed;
	}

	public String ToString()
	{
		return "Player " + this.id + "\n - Name: " + this.name + "\n - Money: " + this.money + "\n - Jailed: " + this.jailed;
	}
}
