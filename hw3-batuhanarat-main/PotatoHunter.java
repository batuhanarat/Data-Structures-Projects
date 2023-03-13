import java.util.*;
import java.lang.*;
import java.io.*;

class PotatoHunter
{
	 static int potatoCounter= 0;
	 static int[][] visited;

	public static void main (String[] args) throws java.lang.Exception
	{

		int r, c, p;


		//File file = new File("inp1.txt");
		//File file = new File("inp2.txt");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader reader = new BufferedReader(new FileReader(file));


	//r :4  c:4 p:1


					r = Integer.parseInt(reader.readLine());
					c = Integer.parseInt(reader.readLine());

					char[][] farm = new char[r][c];
					for (int i = 0; i < r; i++) {
						farm[i] = reader.readLine().toCharArray();
					}

					p = Integer.parseInt(reader.readLine());

					visited = new int[r][c];
		for (int[] row: visited) {
			Arrays.fill(row, 0);

		}

					for (int i = 0; i < p; i++) {
						String[] line = reader.readLine().split(" ");
						int x = Integer.parseInt(line[0]);
						 int y = Integer.parseInt(line[1]);
						x= x-1;
						y=y-1;
						//x 2 y 2

					//	**** *--* *-** ****

						Integer result = 0;
						// print result
						dfs(x,y,farm);
						result=potatoCounter;
						System.out.println(result.toString());
						countPotatoes();
					}
				}




	

	
	public static void countPotatoes() {

		potatoCounter=0;


// Fill each row with 1.0
		for (int[] row: visited) {
			Arrays.fill(row, 0);

		}



	}
	private static void dfs(int x, int y,char[][] farm){

		// array index correction



		visited[x][y]=1;



		//right

		if ( farm[x][y+1] == '-'  && visited[x][y+1]!=1) {
			dfs(x,y+1, farm);
		} else if ( visited[x][y+1] == 0){
			potatoCounter++;
		}

		//down
		if ( farm[x+1][y] == '-'  && visited[x+1][y]!=1) {
			dfs(x+1,y, farm);
		} else if ( visited[x+1][y] == 0){
			potatoCounter++;
		}


		//left
		if ( (farm[x][y-1] == '-'  && visited[x][y-1]!=1) ) {
			dfs(x, y - 1, farm);

		} else if ( visited[x][y-1] == 0){
			potatoCounter++;

		}

		//up
		if ( farm[x-1][y] == '-'  && visited[x-1][y]!=1) {
			dfs(x-1,y, farm);
		} else if ( visited[x-1][y] == 0){
			potatoCounter++;
		}

	}

}
