import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTac {
	
	static ArrayList<Integer> meinePosition = new ArrayList<Integer>();
	static ArrayList<Integer> computerPosition = new ArrayList<Integer>();

	public static void main(String[] args) {

			char[][] field = {{' ', '|', ' ', '|', ' '},
					{'-', '-', '-', '-', '-'},
					{' ', '|', ' ', '|', ' '},
					{'-', '-', '-', '-', '-'},
					{' ', '|', ' ', '|', ' '}};
			
		printField(field);
		
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Gebe ein freies Feld von 1-9 ein.");
			int me = scan.nextInt();
			while(meinePosition.contains(me) || computerPosition.contains(meinePosition)){
				System.out.println("Position ist besetzt.");
				me = scan.nextInt();
			}
			
			placePiece(field, me, "me");
			
			String ergebnis = searchWinner();
			if(ergebnis.length() > 0) {
				System.out.println(ergebnis);
				break;
			}
			Random rand = new Random();
			int computer = rand.nextInt(9) + 1;
			while(meinePosition.contains(computer) || computerPosition.contains(computer)){
				computer = rand.nextInt(9) + 1;
			}
			placePiece(field, computer, "computer");
	
			printField(field);
			
			ergebnis = searchWinner();
			if(ergebnis.length() > 0) {
				System.out.println(ergebnis);
				break;
			}
		}
	}
	public static void printField(char[][] spielfeld){
			for(char[] row : spielfeld) {
				for(char c : row) {
					System.out.print(c);
				}
				System.out.println();
			}
	}
	public static void placePiece(char[][] spielfeld, int position, String player) {
		
		char symbol = ' ';
		
		if(player.equals("me")) {
			symbol = 'X';
			meinePosition.add(position);
		}else if(player.equals("computer")) {
			symbol = 'O';
			computerPosition.add(position);
		}
		
	switch(position) {
			case 1:
				spielfeld[0][0] = symbol;
				break;
			case 2:
				spielfeld[0][2] = symbol;
				break;
			case 3:
				spielfeld[0][4] = symbol;
				break;
			case 4:
				spielfeld[2][0] = symbol;
				break;
			case 5:
				spielfeld[2][2] = symbol;
				break;
			case 6:
				spielfeld[2][4] = symbol;
				break;
			case 7:
				spielfeld[4][0] = symbol;
				break;
			case 8:
				spielfeld[4][2] = symbol;
				break;
			case 9:
				spielfeld[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	public static String searchWinner() {
		
		List spalteLinks = Arrays.asList(1, 4, 7);
		List spalteMitte = Arrays.asList(2, 5, 8);
		List spalteRechts = Arrays.asList(3, 6, 9);		
		List zeileOben = Arrays.asList(1, 2, 3);
		List zeileMitte = Arrays.asList(4, 5, 6);
		List zeileUnten = Arrays.asList(7, 8, 9);
		List diagonaleLinks = Arrays.asList(1, 5, 9);
		List diagonaleRechts = Arrays.asList(3, 5, 7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(spalteLinks);
		winning.add(spalteMitte);
		winning.add(spalteRechts);		
		winning.add(zeileOben);
		winning.add(zeileMitte);
		winning.add(zeileUnten);
		winning.add(diagonaleLinks);
		winning.add(diagonaleRechts);
		
		for(List l : winning) {
			if(meinePosition.containsAll(l)) {
				return "Gewonnen.";
			}else if (computerPosition.contains(l)) {
				return "Verloren.";
			}else if (meinePosition.size() + computerPosition.size() == 9) {
				return "Unentschieden.";
			}
		}
		return "";
	}
}

