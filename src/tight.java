import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class tight {

	static int K, N;
	static BigInteger[][] ans;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			K = in.nextInt();
			N = in.nextInt();
			ans = new BigInteger[N][K + 1];
			for (int i = 0; i <= K; i++)
				ans[0][i] = new BigInteger("1");
			for (int i = 1; i < N; i++) {
				for (int j = 0; j <= K; j++) {
					ans[i][j] = ans[i - 1][j].add(
							(j > 0 ? ans[i - 1][j - 1] : new BigInteger("0")))
							.add(j < K ? ans[i - 1][j + 1]
									: new BigInteger("0"));
				}
			}
			BigInteger total = new BigInteger("0");
			for (int i = 0; i <= K; i++) {
				total = total.add(ans[N - 1][i]);
			}
			total=total.multiply(new BigInteger("100"));
			BigInteger p = new BigInteger("1");
			BigInteger k1 = new BigInteger((new Integer(K+1)).toString());
			for (int i=0;i<N;i++){
				p = p.multiply(k1);
			}
			BigDecimal ans = (new BigDecimal(total)).divide(new BigDecimal(p), 20, BigDecimal.ROUND_HALF_UP);
			System.out.printf("%.15f\n",ans.doubleValue());
		}
	}

}
