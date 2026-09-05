package me.rplgame.miravia;

import java.util.List;

public class Battle {
	  private List<Character> participants;

	  public Battle(List<Character> participants) {
	      this.participants = participants;
	  }

	  public void startBattle() {
	      boolean battling = true;
	      do {
	          for (Character participant : participants) {
	              if (participant instanceof Player) {
	                 ((Player) participant).turn();
	              } else if (participant instanceof Enemy) {
	                 ((Enemy) participant).turn();
	              }
	          }
	          battling = !checkWinner();
	      } while (battling);
	  }

	  private void turn(Character participant) {
	       if (participant instanceof Player) {
	           ((Player) participant).turn();
	       } else if (participant instanceof Enemy) {
	           ((Enemy) participant).turn();
	       }
	   }
	  
	  public boolean checkWinner() {
	      for (Character participant : participants) {
	          if (participant.getHealth() <= 0) {
	              System.out.println(participant.getName() + " has been defeated.");
	              return true;
	          }
	      }
	      return false;
	  }
	}