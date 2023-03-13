import crashtable.CrashTable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class Main {
	static CrashTable table = new CrashTable();

	public static void main(String[] args) throws IOException {

		// The format is [["Key", "Value"]]
		List<String[]> list = new ArrayList<>();

		// Table path argument provided
		if (args.length > 0) {
			list = Files.lines(Path.of(args[0].trim()))
					.map(l -> l.split("\\s+=>\\s+"))
					.collect(Collectors.toList());
		}

		var table = new CrashTable();


		testCaseFillTheArray(list);
		testCase();



	}
		//fill array from the list

	private static void testCaseFillTheArray(List<String[]> list) {

		for (int i = 0; i < 4945; i++) {
			String[] stringArray = list.get(i);
			var first = stringArray[0];
			var last = stringArray[1];
			table.put(first, last);

		}

		System.out.println("--------------------------------------");
		table.printTable();
		System.out.println("--------------------------------------");
		System.out.println("We can check hash(), put() and resize() function from given table.");
		System.out.println();



	}

	private static void testCase() {


		System.out.println("KEYS:");
		System.out.println(Arrays.toString(table.getKeys()));
		System.out.println();




		if (table.get("Batuhan")== null) {
			System.out.println("Testing Get Function");
			System.out.println(".....................");
			System.out.println("Get function is correct when key is not present");
			System.out.println("PASSED");
			System.out.println();


		}

		if(table.get("Batuhan", table.get("Adara")) == table.get("Adara")) {
			System.out.println("Testing Get Function");
			System.out.println(".....................");
			System.out.println("Get function with two input is correct.");
			System.out.println("PASSED");
			System.out.println();

		}


		if (table.get("Aaren") != null ) {

			System.out.println("Aaren is present ");
		}
		table.remove("Aaren");
		if (table.get("Aaren") == null ) {
			System.out.println("Testing remove Function");
			System.out.println(".....................");
			System.out.println("Aaren is deleted after remove. Remove works correctly");
			System.out.println("PASSED");
			System.out.println();

		}

	}
}
