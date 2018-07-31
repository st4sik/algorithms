package com.algo.quiz;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Extensions to NFA. Add to NFA.java the ability to handle multiway or, wildcard, and the + closure
 * operator.
 *
 * https://algs4.cs.princeton.edu/54regexp/NFA.java.html
 */
public class NFA {

  private Digraph graph;     // digraph of epsilon transitions
  private String regexp;     // regular expression
  private final int m;       // number of characters in regular expression

  /**
   * Initializes the NFA from the specified regular expression.
   *
   * @param regexp the regular expression
   */
  public NFA(String regexp) {
    this.regexp = regexp;
    m = regexp.length();
    Stack<Integer> ops = new Stack<>();
    graph = new Digraph(m + 1);
    for (int i = 0; i < m; i++) {
      int lp = i;
      if (regexp.charAt(i) == '(' || regexp.charAt(i) == '|') {
        ops.push(i);
      } else if (regexp.charAt(i) == ')') {
        int or = ops.pop();

        if (regexp.charAt(or) == '|') {
          Bag<Integer> ors = new Bag<>();
          while (ops.peek() == '|') {
            ors.add(ops.pop());
          }
          lp = ops.pop();
          for (int op : ops) {
            graph.addEdge(lp, op + 1);
            graph.addEdge(op, i);
          }
        } else if (regexp.charAt(or) == '(') {
          lp = or;
        } else {
          assert false;
        }
      }

      // closure operator (uses 1-character lookahead)
      if (i < m - 1 && regexp.charAt(i + 1) == '*') {
        graph.addEdge(lp, i + 1);
        graph.addEdge(i + 1, lp);
      }

      if (i < m - 1 && regexp.charAt(i + 1) == '+') {
        graph.addEdge(i + 1, lp);
      }

      if (regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')') {
        graph.addEdge(i, i + 1);
      }
    }
    if (ops.size() != 0) {
      throw new IllegalArgumentException("Invalid regular expression");
    }
  }

  /**
   * Returns true if the text is matched by the regular expression.
   *
   * @param txt the text
   * @return {@code true} if the text is matched by the regular expression, {@code false} otherwise
   */
  public boolean recognizes(String txt) {
    DirectedDFS dfs = new DirectedDFS(graph, 0);
    Bag<Integer> pc = new Bag<Integer>();
    for (int v = 0; v < graph.V(); v++) {
      if (dfs.marked(v)) {
        pc.add(v);
      }
    }

    // Compute possible NFA states for txt[i+1]
    for (int i = 0; i < txt.length(); i++) {
      if (txt.charAt(i) == '*' || txt.charAt(i) == '|' || txt.charAt(i) == '('
          || txt.charAt(i) == ')') {
        throw new IllegalArgumentException(
            "text contains the metacharacter '" + txt.charAt(i) + "'");
      }

      Bag<Integer> match = new Bag<Integer>();
      for (int v : pc) {
        if (v == m) {
          continue;
        }
        if ((regexp.charAt(v) == txt.charAt(i)) || regexp.charAt(v) == '.') {
          match.add(v + 1);
        }
      }
      dfs = new DirectedDFS(graph, match);
      pc = new Bag<Integer>();
      for (int v = 0; v < graph.V(); v++) {
        if (dfs.marked(v)) {
          pc.add(v);
        }
      }

      // optimization if no states reachable
      if (pc.size() == 0) {
        return false;
      }
    }

    // check for accept state
    for (int v : pc) {
      if (v == m) {
        return true;
      }
    }
    return false;
  }

  /**
   * Unit tests the {@code NFA} data type.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    String regexp = "(" + args[0] + ")";
    String txt = args[1];
    NFA nfa = new NFA(regexp);
    StdOut.println(nfa.recognizes(txt));
  }

}
