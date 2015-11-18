import java.util.Scanner;
//
public class correspondence {

	static String[] a;
	static String[] b;
	static int n;
	static boolean[] vis;
	static String res;
	static String ans;
	static int casenum = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			casenum++;
			n = in.nextInt();
			in.nextLine();
			a = new String[n];
			b = new String[n];
			for (int i = 0; i < n; i++) {
				String str = in.nextLine();
				String[] strs = str.split(" ");
				a[i] = strs[0];
				b[i] = strs[1];
			}
			vis = new boolean[n];
			res = "";
			ans = null;
			dfs("", "");
			if (ans == null) {
				System.out.println("Case " + casenum + ": IMPOSSIBLE");
			} else {
				System.out.println("Case " + casenum + ": " + ans);
			}
		}
		in.close();
	}

	private static void dfs(String sa, String sb) {
		if (ans != null && res.length() > ans.length()) {
			return;
		}
		for (int i = 0; i < n; i++) {
			if (vis[i])
				continue;
			String aa = sa + a[i];
			String bb = sb + b[i];
			if (aa.length() > bb.length() && aa.startsWith(bb)) {
				vis[i] = true;
				String oldres = res;
				res = res + bb;
				dfs(aa.substring(bb.length()), "");
				vis[i] = false;
				res = oldres;
			} else {
				if (bb.length() > aa.length() && bb.startsWith(aa)) {
					vis[i] = true;
					String oldres = res;
					res = res + aa;
					dfs("", bb.substring(aa.length()));
					vis[i] = false;
					res = oldres;
				} else {
					if (aa.length() == bb.length() && aa.equals(bb)) {
						String oldres = res;
						res = res + aa;
						update();
						res = oldres;
					}
				}
			}
		}
		return;
	}

	private static void update() {
		if (ans == null) {
			ans = res;
			return;
		}
		if (ans.length() > res.length()) {
			ans = res;
			return;
		}
		if (ans.length() == res.length() && ans.compareTo(res) > 0) {
			ans = res;
			return;
		}
	}
}
