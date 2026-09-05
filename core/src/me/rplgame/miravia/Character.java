/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rplgame.miravia;

/**
 *
 * @author Anaz
 */
public abstract class Character {
	
	protected float x,y;
	protected float width,height;
	
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;

    public Character(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setName(String name) {
		this.name = name;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void takeDamage(int damage) {
        health -= damage;
        
    }

}
