package me.rplgame.miravia;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Enemy extends Character {
	private Texture texture;
	
	private int level;
    private int turnsBeforeAttack;
    public int getTurnsBeforeAttack() {
		return turnsBeforeAttack;
	}

	public void setTurnsBeforeAttack(int turnsBeforeAttack) {
		this.turnsBeforeAttack = turnsBeforeAttack;
	}

	private Player player;
    
    public Enemy(String name, int level, Player player) {
        super(name, level * 10, level * 2, level * 2);
        this.level = level;
        this.player = player;
        if (level <= 2) {
        	setTexture(new Texture("flasher.png"));
        } else if( level <=4)
        {
        	setTexture(new Texture("axeman.png"));
        } else if( level <=6)
        {
        	setTexture(new Texture("crossy.png"));
        } else if( level <=8)
        {
        	setTexture(new Texture("skeleboy.png"));
        } else if( level <=10)
        {
        	setTexture(new Texture("kadal.png"));
        }
    }

    public void takeDamage(int damageTaken) {
    	super.health -= damageTaken;
    }
    public int getLevel() {
        return level;
    }

    public void attack(Player player, int attack) {
        int damage = attack;
        if (damage < 0) damage = 0;
        player.takeDamage(damage);
        System.out.println("The enemy attacked you for " + damage + " damage!");
    }
    
    public void turn() {
    	Random random = new Random();

    	   // Choose an action at random
    	   int action = random.nextInt(2);

    	   switch (action) {
    	       case 0:
    	           // Attack the player lightly
    	           this.attack(player, this.attack);
    	           break;
    	       case 1:
    	           // Start dealing huge damage to the player in 2 turns
    	           this.turnsBeforeAttack = 2;
    	           this.attack *= 2; // Double the attack power
    	           break;
    	   }

    	   // Apply the damage if it's time
    	   if (this.turnsBeforeAttack > 0) {
    	       this.turnsBeforeAttack--;
    	   }

    	   // Reset the attack power after the attack
    	   if (this.turnsBeforeAttack == 0) {
    	       this.attack = this.level * 2;
    	   }
        
    }

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
    
//    public void render(SpriteBatch batch) {
//    	batch.draw(texture, 1180-240, 100, 240,240);
//    }
}
