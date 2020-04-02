
package state;
public class Operator {
	int i, j;
	
	public Operator(int i, int j) { this.i = i; this.j = j; }
	
	public static Operator[] OPERATORS = new Operator[] {
		new Operator(1, 2),
		new Operator(1, 3),
		new Operator(2, 1),
		new Operator(2, 3),
		new Operator(3, 1),
		new Operator(3, 2),
	};
	
	int M[] = {0, 5, 3, 2};

	public boolean applicable(State state) {
		int a[] = state.a;
		return a[i] > 0 && a[j] < M[j];
	}
	
	public State apply(State state) {
		State newState = new State();
		int n[] = newState.a;
		int a[] = state.a;
		
		int m = Math.min(a[i], M[j]-a[j]);
		for (int k=1; k<=3; k++) {
			if ( k==j )
				n[k] = a[k] + m;
			else if ( k==i )
				n[k] = a[k] - m;
			else
				n[k] = a[k];
		}
		return newState;
	}
	
	@Override
	public String toString() {
		return i + " -> " + j;
	}
}
