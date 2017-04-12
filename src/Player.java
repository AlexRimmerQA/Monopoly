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

	public Player()
	{
		id = nextId;
		nextId++;

		money = 500;
		jailed = false;
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

	public String ToString()
	{
		return "Player " + this.id + "\n - Name: " + this.name + "\n - Money: " + this.money + "\n - Jailed: " + this.jailed;
	}
}
