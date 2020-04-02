/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.util.Comparator; 
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class TreeSearch {
 
 public List<Operator> solution(Node terminal) {
  LinkedList<Operator> solution = new LinkedList<Operator>();
  for (Node n=terminal; n.parent != null; n=n.parent)
   solution.addFirst(n.action);
  return solution;
 }
 
 static class Node {
   State state;
   Node parent;
   Operator action;
   int cost;
   int depth;
  
  public Node(State state, Node parent, Operator action) {
   this.state = state;
   this.parent = parent;
   this.action = action;
   this.cost = parent == null? 0 : parent.cost + state.cost(parent.state);
   this.depth = parent == null ? 0 : parent.depth + 1;
  }
  
  @Override
  public String toString() {
     if (this.action == null)
       return this.state.toString() + " xXx : "+Integer.toString(this.depth)+"/"
              +Integer.toString(this.cost)+"/"
              +Integer.toString(this.state.heuristic());
     else   
       return this.state.toString() +"  ["+ this.parent.state.toString() 
              +"/" +this.action.toString() 
              +"] "+Integer.toString(this.depth)+"/"
              +Integer.toString(this.cost)+"/"
              +Integer.toString(this.state.heuristic());
  }
 }
 
 static class myComparator implements Comparator<Node>{
   public int compare(Node n1, Node n2){
     // BFS - minimal depth  -----------------------------
     //Integer n1d=n1.depth;
     //return n1d.compareTo(n2.depth);

     // DFS - maximal depth  -----------------------------
     //Integer n2d=n2.depth;
     //return n2d.compareTo(n1.depth);  // reverse

     //Uniform cost - minimal cost -----------------------
     //Integer n1c = n1.cost;
     //return n1c.compareTo(n2.cost);

     //Best first - minimal heuristic  -------------------
     //Integer n1h = n1.state.heuristic();
     //return n1h.compareTo(n2.state.heuristic());

     //A* - minimal sum  ---------------------------------
     Integer n1gh = n1.cost+n1.state.heuristic();
     Integer n2gh = n2.cost+n2.state.heuristic();
     return n1gh.compareTo(n2gh);
    }
 }
 
 public List<Operator> search(myComparator c) {
  PriorityQueue<Node> fringe = new PriorityQueue<Node>(c);
  // 1. 
  State s = new State();
  fringe.add(new Node(s, null, null));
  while (true) {
   // 2.
   if ( fringe.isEmpty() ) {return null;}
   // Select 
   Node selected = fringe.poll();
   System.out.println("selected node: "+selected.toString());
   // 3.
   if ( selected.state.isGoal() ) {return solution(selected);}
   // Extend
   for (Operator o: Operator.OPERATORS){ 
    if ( o.applicable(selected.state)) {
     State newState = o.apply(selected.state);
     Node n = new Node(newState, selected, o);
     fringe.add(n);
     System.out.println("successor node:" + n.toString());
    }
   }
  }
 }

  public List<Operator> dls_search(myComparator c, int limit) {
  PriorityQueue<Node> fringe = new PriorityQueue<Node>(c);
  // 1. 
  State s = new State();
  fringe.add(new Node(s, null, null));
  while (true) {
   // 2.
   if ( fringe.isEmpty() ) {return null;}
   // Select 
   Node selected = fringe.poll();
   System.out.println("selected node: "+selected.toString());
   // 3.
   if ( selected.state.isGoal() ) {return solution(selected);}
   // Extend
   for (Operator o: Operator.OPERATORS) 
    if ( o.applicable(selected.state)) {
     State newState = o.apply(selected.state);
     Node n = new Node(newState, selected, o);
     if (n.depth <= limit) {
        fringe.add(n);
        System.out.println("successor node:" + n.toString());
      }
    }
  }
 }

 public static void main(String[] args) {
  List<Operator> m = new TreeSearch().search(new myComparator());
  //List<Operator> m = new TreeSearch().dls_search(new myComparator(), 4);
  if ( m!= null ) {
   State s = new State();
   for (Operator o:m) {
    System.out.println(s +"\t" + o);
    s = o.apply(s);
   }
   System.out.println(s);
  }
 }
}
