package com.algo.assessment;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BaseballElimination {

  private String[] teams;
  private int[] wins;
  private int[] losses;
  private int[] remains;
  private int[][] games;

  public BaseballElimination(String filename) {
    if (filename == null || filename.isEmpty()) {
      throw new IllegalArgumentException();
    }
    initParams(filename);

  }

  public int numberOfTeams() {
    return teams.length;
  }

  public Iterable<String> teams() {
    return Arrays.asList(teams);
  }

  public int wins(String team) {
    if (team == null || team.isEmpty()) {
      throw new IllegalArgumentException();
    }
    int idTeam = getIdTeamByName(team);
    return wins[idTeam];
  }

  public int losses(String team) {
    if (team == null || team.isEmpty()) {
      throw new IllegalArgumentException();
    }
    int idTeam = getIdTeamByName(team);
    return losses[idTeam];
  }

  public int remaining(String team) {
    if (team == null || team.isEmpty()) {
      throw new IllegalArgumentException();
    }
    int idTeam = getIdTeamByName(team);
    return remains[idTeam];
  }

  public int against(String team1, String team2) {
    if (team1 == null || team1.isEmpty() || team2 == null || team2.isEmpty()) {
      throw new IllegalArgumentException();
    }
    int idTeam1 = getIdTeamByName(team1);
    int idTeam2 = getIdTeamByName(team2);
    return games[idTeam1][idTeam2];
  }

  public boolean isEliminated(String team) {
    Iterable<String> certificateOfElimination = certificateOfElimination(team);
    return certificateOfElimination != null;
  }

  public Iterable<String> certificateOfElimination(String team) {
    if (team.isEmpty()) {
      throw new IllegalArgumentException();
    }
    int idCurrentTeam = getIdTeamByName(team);
    Iterable<String> certificateOfElimination = trivialElimination(idCurrentTeam);
    if (Objects.nonNull(certificateOfElimination)) {
      return certificateOfElimination;
    }
    certificateOfElimination = nontrivialElimination(idCurrentTeam);
    return certificateOfElimination;
  }

  private Iterable<String> trivialElimination(int idCurrentTeam) {
    for (int i = 0; i < numberOfTeams(); i++) {
      if (idCurrentTeam != i && (wins[idCurrentTeam] + remains[idCurrentTeam]) < wins[i]) {
        return Arrays.asList(teams[i]);
      }
    }
    return null;
  }

  private Iterable<String> nontrivialElimination(int idCurrentTeam) {
    FlowNetwork G = buildFlowNetwork(idCurrentTeam);
    int s = 0;
    int t = G.V() - 1;
    FordFulkerson ff = new FordFulkerson(G, s, t);
    if (isEliminated(G, s)) {
      List<String> certificateOfElimination = new ArrayList<>();
      for (int i = 0; i < numberOfTeams(); i++) {
        if (i != idCurrentTeam && ff.inCut(t - numberOfTeams() + i)) {
          certificateOfElimination.add(teams[i]);
        }
      }
      return certificateOfElimination;
    }
    return null;
  }

  private boolean isEliminated(FlowNetwork flowNetwork, int s) {
    for (FlowEdge fe : flowNetwork.adj(s)) {
      int to = fe.other(s);
      if (fe.residualCapacityTo(to) > 0) {
        return true;
      }
    }
    return false;
  }

  private FlowNetwork buildFlowNetwork(int idCurrentTeam) {
    int numberOfTeams = numberOfTeams();
    int nbVert = 2 + numberOfTeams;
    for (int i = 0; i < numberOfTeams; i++) {
      for (int j = i + 1; j < numberOfTeams; j++) {
        if (games[i][j] > 0 && i != idCurrentTeam && j != idCurrentTeam) {
          nbVert++;
        }
      }
    }

    int s = 0;
    int t = nbVert - 1;

    FlowNetwork G = new FlowNetwork(nbVert);

    int cur = 1;
    for (int i = 0; i < numberOfTeams; i++) {
      for (int j = i + 1; j < numberOfTeams; j++) {
        if (games[i][j] > 0 && i != idCurrentTeam && j != idCurrentTeam) {

          FlowEdge edge = new FlowEdge(s, cur, games[i][j]);
          G.addEdge(edge);

          FlowEdge out1 = new FlowEdge(cur, t - numberOfTeams + i, Double.POSITIVE_INFINITY);
          G.addEdge(out1);
          FlowEdge out2 = new FlowEdge(cur, t - numberOfTeams + j, Double.POSITIVE_INFINITY);
          G.addEdge(out2);

          cur++;
        }
      }
    }

    addOutEdges(idCurrentTeam, numberOfTeams, t, G);
    return G;
  }

  private void addOutEdges(int idCurrentTeam, int numberOfTeams, int t, FlowNetwork g) {
    for (int i = 0; i < numberOfTeams; i++) {
      FlowEdge e = new FlowEdge(t - numberOfTeams + i, t,
          wins[idCurrentTeam] + remains[idCurrentTeam] - wins[i]);
      g.addEdge(e);
    }
  }

  private int getIdTeamByName(String team) {
    int x = Arrays.asList(teams).indexOf(team);
    if (x == -1) {
      throw new IllegalArgumentException();
    }
    return x;
  }

  private void initParams(String filename) {
    In in = new In(filename);
    int numberOfTeams = in.readInt();
    this.teams = new String[numberOfTeams];
    this.wins = new int[numberOfTeams];
    this.losses = new int[numberOfTeams];
    this.remains = new int[numberOfTeams];
    this.games = new int[numberOfTeams][numberOfTeams];

    loadFromFile(in, numberOfTeams);
    in.close();
  }

  private void loadFromFile(In in, int numberOfTeams) {
    for (int i = 0; i < numberOfTeams; i++) {
      String teamName = in.readString();
      teams[i] = teamName;
      wins[i] = in.readInt();
      losses[i] = in.readInt();
      remains[i] = in.readInt();
      for (int j = 0; j < numberOfTeams; j++) {
        games[i][j] = in.readInt();
      }
    }
  }

  public static void main(String[] args) {
    BaseballElimination division = new BaseballElimination(args.length == 0 ? null : args[0]);
    for (String team : division.teams()) {
      if (division.isEliminated(team)) {
        StdOut.print(team + " is eliminated by the subset R = { ");
        for (String t : division.certificateOfElimination(team)) {
          StdOut.print(t + " ");
        }
        StdOut.println("}");
      } else {
        StdOut.println(team + " is not eliminated");
      }
    }
  }
}
