import java.util.Scanner;


public class rebeldie {

	static int n;
	static int m;
	static int[] color;
	static int[] state; //0-top, 1-left, 2-up, 3-right, 4-down, 5-bottum
	static int[][] vis;
	static int[][] map;
	static boolean flag;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		color = new int[6];
		state = new int[6];
		for(int i=0;i<6;i++) {
			color[i] = 7;
			state[i] = i;
		}
		map = new int[m][n];
		vis = new int[m][n];
		for(int i = 0 ; i < m;i++){
			for (int j=0;j<n;j++){
				map[i][j] = in.nextInt();
//				map[i][j] = (i + j) % 6;
			}
		}
		flag = false;
		dfs(0,0);
		if(flag){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}
	
	static int encode(int[] color){
		int res = 0;
		for(int i=0;i<6;i++){
			res  = res*6 + color[res];
		}
		return res;
	}
	
	static void dfs(int x, int y){
//		System.out.println(x + " " + y);
		if(vis[x][y]!=0||flag) return;
		if(color[state[0]] != 7 && color[state[0]] != map[x][y]) return;
		if(x == m-1 && y == n-1) {
			flag = true;
			return;
		}
		int[] oldS = state.clone();
		int[] oldC = color.clone();
		vis[x][y] = 1;
		color[state[0]] = map[x][y];
		if(x < m-1){
			state = goRight(oldS);
			dfs(x+1, y);
			state = oldS;
		}
		if(x > 0){
			state = goLeft(oldS);
			dfs(x-1, y);
			state = oldS;
		}
		if(y < n-1){
			state = goDown(oldS);
			dfs(x, y+1);
			state = oldS;
		}
		if(y>0){
			state = goUp(oldS);
			dfs(x, y-1);
			state = oldS;
		}
		color = oldC;
		//vis[x][y] = 0;
	}
	
	static int[] goRight(int[] state){
		int[] newState = new int[6];
		newState[0] = state[1]; // top = previous left
		newState[1] = state[5]; // left = previous bottom
		newState[2] = state[2]; // up = previous up
		newState[3] = state[0]; // right = previou top
		newState[4] = state[4]; // down = previous down
		newState[5] = state[3]; // bottum = previous right
		return newState;		
	}
	
	static int[] goLeft(int[] state){
		int[] newState= new int[6];
		newState[0] = state[3];
		newState[1] = state[0];
		newState[2] = state[2];
		newState[3] = state[5];
		newState[4] = state[4];
		newState[5] = state[1];
		return newState;
	}
	
	static int[] goUp(int[] state){
		int[] newState = new int[6];
		newState[0] = state[4];
		newState[1] = state[1];
		newState[2] = state[0];
		newState[3] = state[3];
		newState[4] = state[5];
		newState[5] = state[2];
		return newState;
	}
	
	static int[] goDown(int[] state){
		int[] newState = new int[6];
		newState[0] = state[2];
		newState[1] = state[1];
		newState[2] = state[5];
		newState[3] = state[3];
		newState[4] = state[0];
		newState[5] = state[4];
		return newState;
	}
}
