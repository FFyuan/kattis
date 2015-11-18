import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class linijie {
	
	static int[] matchL = new int[510];
	static int[] matchR = new int[510];
	static int[] cnt_x = new int[510];
	static int[] cnt_y = new int[510];
	static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
	static boolean[] seen = new boolean[510];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for(int i=0;i<=500;i++){
			edges.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < n; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			cnt_x[x]++;cnt_y[y]++;
			edges.get(x).add(y);
		}
		
		maxMatching();
		
		for(int i=0;i<=500;i++){
			if(cnt_x[i]>0 && matchL[i]==-1){
				System.out.println("Mirko");
				return;
			}
			if(cnt_y[i]>0 && matchR[i]==-1){
				System.out.println("Mirko");
				return;
			}
		}
		System.out.println("Slavko");
	}

	static void maxMatching(){
		Arrays.fill(matchL, -1);
		Arrays.fill(matchR, -1);
		
		int count=0;
		for(int i=0;i<=500;i++){
			Arrays.fill(seen, false);
			if(bpm(i)) count++;
		}
	}
	
	static boolean bpm(int u){
		ArrayList<Integer> egs = edges.get(u);
		for(int v:egs){
			if(seen[v]) continue;
			seen[v] = true;
			if(matchR[v] == -1 || bpm(matchR[v])){
				matchL[u] = v;
				matchR[v] = u;
				return true;
			}
		}
		return false;
	}
}
