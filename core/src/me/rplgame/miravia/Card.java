/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rplgame.miravia;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Anaz
 */
public class Card {
	private Texture textureC1;
	private String name;
	private int manacost;
	private int damage;
	private boolean selected;
	
	private float x,y;
	private float width,height;
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setManacost(int manacost) {
		this.manacost = manacost;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Texture getTextureC1() {
		return textureC1;
	}

	public void setTextureC1(Texture textureC1) {
		this.textureC1 = textureC1;
	}

	public Card(String name, int manacost, int damage) {
		this.name = name;
		this.manacost = manacost;
		this.damage = damage;
		if (name == "tap") textureC1 = new Texture("card1.png");
		if (name == "hit") textureC1 = new Texture("card2.png");
		if (name == "poke") textureC1 = new Texture("card3.png");
		if (name == "slap") textureC1 = new Texture("card4.png");
		if (name == "smash") textureC1 = new Texture("card5.png");
		
	}
	public Card(String name) {
		this.name = name;
		if (name == "tap") manacost = 1;
		if (name == "hit") manacost = 2;
		if (name == "poke") manacost = 1;
		if (name == "slap") manacost = 1;
		if (name == "smash") manacost = 2;
		if (name == "mace") manacost = 2;
		if (name == "slash") manacost = 1;
		if (name == "tap") damage = 2;
		if (name == "hit") damage = 4;
		if (name == "poke") damage = 1;
		if (name == "slap") damage = 2;
		if (name == "smash") damage = 5;
		if (name == "mace") damage = 6;
		if (name == "slash") damage = 3;
		if (name == "tap") textureC1 = new Texture("1.png");
		if (name == "hit") textureC1 = new Texture("2.png");
		if (name == "poke") textureC1 = new Texture("3.png");
		if (name == "slap") textureC1 = new Texture("4.png");
		if (name == "smash") textureC1 = new Texture("5.png");
		if (name == "mace") textureC1 = new Texture("6.png");
		if (name == "slash") textureC1 = new Texture("7.png");
		
	}

	public String getName() {
		return name;
	}

	public int getManacost() {
		return manacost;
	}

	public int getDamage() {
		return damage;
	}
	
//	public void render(SpriteBatch batch, int x, int y) {
//		   batch.draw(textureC1,x,y,183,256);
//	}
//	
//	public void renderSelected(SpriteBatch batch, int x, int y) {
//		   batch.draw(textureC1,x,y+50,183,256);
//	}
}
