package state;


public class State {
 int a[] = new int[4];
 
 public State() {
  a[1] = 5;  
  a[2] = 0;
  a[3] = 0;
 }
 
 public boolean isGoal() {return a[1] == 4;}
 
 // Math.abs in not needed, because di = -dj
 //public int cost(State s2) {
 //  int d1= a[1]-s2.a[1];
 //  int d2= a[2]-s2.a[2];
 //  int d3= a[3]-s2.a[3];
 //  return Math.max(d1,Math.max(d2,d3));
 //}  
 public int cost(State s2) {
   if (a[1]<s2.a[1]) return s2.a[1];
   if (a[2]<s2.a[2]) return s2.a[2];
   return s2.a[3];
 }
 
 public int heuristic(){
   if (this.isGoal()) return 0;
   int counter=1;
   if (a[1]==0) counter+=1;
   if (a[2]==0) counter+=1;
   if (a[3]==0) counter+=1;
   return counter;
 }
 @Override
 public boolean equals(Object obj) {
  int b[] = ((State)obj).a;
  return a[1]==b[1] && a[2]==b[2] && a[3]==b[3];
 }
 
 @Override
 public int hashCode() {return a[1]*100 + a[2]*10 + a[3];}
 
 @Override
 public String toString() {
  return "("+a[1]+","+a[2]+","+a[3]+")";
 }
}
