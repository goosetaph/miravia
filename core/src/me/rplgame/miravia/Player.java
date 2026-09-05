package me.rplgame.miravia;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Character {
	
	private Texture texture;
	
    private int level;
    private int score;
    private int money;
    private int mana;
    private Deck deck;
    private Deck hand;
    private boolean endTurn = false;
    private final int MAX_MANA;
    private final int MAX_HEALTH;
    //private Inventory inventory;

    public Player(String name, int health, int attack, int defense, int mana, Deck deck) {
        super(name, health, attack, defense);
        this.level = 1;
        this.score = 0;
        this.money = 100;
        this.deck = deck;
        this.mana = mana;
        this.MAX_HEALTH = health;
        this.MAX_MANA = mana;
        if (name == "archie") {
        	setTexture(new Texture("archie.png"));
        } else if (name == "macy") {
        	setTexture(new Texture("macy.png"));
        } else {
        	setTexture(new Texture("knightsword.png"));
        }
        
        //this.inventory = inventory;
    }

    public boolean isEndTurn() {
		return endTurn;
	}

	public void setEndTurn(boolean endTurn) {
		this.endTurn = endTurn;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setXp(int score) {
		this.score = score;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public int getLevel() {
        return level;
    }

    public int getXp() {
        return score;
    }

    public int getMoney() {
        return money;
    }

    public Deck getDeck() {
        return deck;
    }

    
    public void earnXP(int amount) {
        score += amount;
        System.out.println("You earned " + amount + " score");
    }

    public void addMoney(int amount) {
        money += amount;
        System.out.println("You earned " + amount + " money! Total money: " + money);
    }

    public void reduceMoney(int amount) {
        if (amount > money) {
            System.out.println("Not enough money!");
           
        }
        money -= amount;
        System.out.println("You spent " + amount + " money. Remaining money: " + money);
        
    }

    public void attack(Character target) {
        int damage = attack - target.getDefense();
        if (damage < 0) damage = 0;
        target.takeDamage(damage);
        System.out.println("You attacked the enemy for " + damage + " damage!");
    }

    public boolean endTurn() {
    	return this.endTurn = true;
    }
    
    public void turn() {
    	
    	while(!endTurn) {
    		
    	}
    	this.endTurn = false;
        // Check if the player has enough mana to play a card
        if (this.mana >= deck.getCards().get(0).getManacost()) {
            deck.shuffle();
            hand.addCard(deck.drawCard());
            hand.addCard(deck.drawCard());
            hand.addCard(deck.drawCard());
            
        	// Play the card
            //card.play();
        	System.out.println("this is player turn");
            // Reduce the player's mana by the card's mana cost
            this.mana -= deck.getCards().get(0).getManacost();
        } else {
            System.out.println("Not enough mana to play this card.");
        }
    }
    
    public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score += score;
	}
	
	public void levelUp() {
        level++;
        score = 0;
        health += 10;
        attack += 5;
        defense += 5;
        System.out.println("Congratulations! You leveled up to level " + level + "!");
    }
    
    public void update() {
    	
    }

	public Texture getTexture() {
		return texture;
	}

	public  void setTexture(Texture texture) {
		this.texture = texture;
	}
	public void addMana(int mana) {
		this.mana += mana;
	}
	public void reduceMana(int mana) {
		this.mana -= mana;
	}

	public int getMAX_MANA() {
		return MAX_MANA;
	}

	public int getMAX_HEALTH() {
		return MAX_HEALTH;
	}
    
//    public void render(SpriteBatch batch) {
//    	batch.draw(texture,super.x,super.y,super.width,super.height);
//    }
    
}
