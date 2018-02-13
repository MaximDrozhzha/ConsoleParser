package ConsoleParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

	public static void main(String[] args) throws IOException {
		Parser p = new Parser();
		ArrayList<ArrayList<String>> lineList = p.readData();
		ArrayList<ArrayList<String>> resultLineList = p.parse(lineList, args);
		p.write(resultLineList);
	}

	ArrayList<ArrayList<String>> readData() throws IOException {
		ArrayList<ArrayList<String>> lineList = new ArrayList<>();
		String s = "";
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			do {
				s = br.readLine();
				ArrayList<String> line = new ArrayList<>();
				String[] words = s.split("[,;:.!?\\s]+");
				line.addAll(Arrays.asList(words));
				lineList.add(line);
			} while (!s.equals(""));
			return lineList;
		} catch (Exception e) {
			throw e;
		}
	}

	ArrayList<ArrayList<String>> parse(ArrayList<ArrayList<String>> lineList, String... args) {
		ArrayList<ArrayList<String>> resultLineList = new ArrayList<>();
		for (ArrayList<String> line : lineList) {
			for (String word : line) {
				if (ismachedWord(word, args)) {
					resultLineList.add(line);
					break;
				}
			}
		}
		return resultLineList;
	}

	static boolean ismachedWord(String word, String... args) {
		for (String arg : args) {
			if (word.matches(arg)) {
				return true;
			}
		}
		return false;
	}

	void write(ArrayList<ArrayList<String>> lineList) {
		for (ArrayList<String> line : lineList) {
			for (String s : line) {
				System.out.print(s + " ");
			}
			System.out.println("");
		}
	}

}
