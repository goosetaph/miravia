package me.rplgame.miravia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import me.rplgame.miravia.*;

class GameTest {
   @Test
   void testEnemyAttack() {
   	// Create a player and an enemy
   	Player player = new Player("John Doe", 10);
   	Enemy enemy = new Enemy("Goblin", 1);

   	// The enemy attacks the player
   	enemy.attack(player);

   	// Check if the player's health decreased after the attack
   	assertTrue(player.getHealth() < 10);
   }

   @Test
   void testStartBattle() {
   	// Create a player and an enemy
   	Player player = new Player("John Doe", 10);
   	Enemy enemy = new Enemy("Goblin", 1);

   	// Add the player and the enemy to a list of participants
   	List<Character> participants = new ArrayList<>();
   	participants.add(player);
   	participants.add(enemy);

   	// Start a battle
   	Battle battle = new Battle(participants);
   	battle.startBattle();

   	// Check if the battle ended
   	assertFalse(battle.isBattling());
   }
}


