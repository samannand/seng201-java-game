import java.util.Scanner;

public class GameEnvironment {
	
	private String teamName;
	private int numCities;
	private int numHeros;
	private Scanner sc = new Scanner(System.in);
	private boolean stopGame = false;
	private Team gameTeam = new Team();
	
	public void introduction() {
		
		do {
			System.out.println("Pick a name for your team of heros between 2 and 10 character long.");
			System.out.print("Input('q' to exit): ");
			System.out.flush();
			String result = sc.next();
			if (result.equals(Character.toString('q'))) {
				System.out.println(farewell());
				stopGame = true;
				return;
			} else {
				teamName = result;
			}
		} while (!(teamName.length() >= 2 && teamName.length() <= 10));
		
		do {
			System.out.println("How many cities will your heros explore to find the super villain? 3, 4, 5, or 6");
			System.out.print("Input('q' to exit): ");
			System.out.flush();
			String result = sc.next();
			if (result.equals(Character.toString('q'))) {
				System.out.println(farewell());
				stopGame = true;
				return;
			} else {
				numCities = Integer.parseInt(result);
			}
		} while (!(numCities > 2 && numCities < 7));
		
		do {
			System.out.println("How many heros will be on your team? 1, 2, or 3");
			System.out.print("Input('q' to exit): ");
			System.out.flush();
			String result = sc.next();
			if (result.equals(Character.toString('q'))) {
				System.out.println(farewell());
				stopGame = true;
				return;
			} else {
				numHeros = Integer.parseInt(result);
			}
		} while (!(numHeros > 0 && numHeros < 4));
		
		System.out.println(teamName);
		System.out.println(numCities);
		System.out.println(numHeros);

	}
	
	public void buildTeam(String teamName, int numHeros) {
		gameTeam.setName(teamName);
		gameTeam.setMaxSize(numHeros);
		
		
		
	}
	
	public String farewell() {
		return "See you next time!";
	}
	
	public String farewell(String teamName) {
		return "Thanks for playing: " + teamName + "!";
	}
	
	
	public static void main(String[] args) {
		GameEnvironment myGame = new GameEnvironment();
		while (!myGame.stopGame) {
			myGame.introduction();
		}
	}
	

}
