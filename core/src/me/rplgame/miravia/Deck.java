/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rplgame.miravia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Anaz
 */

public class Deck {
	private static Texture texture;   
	private List<Card> cards;

   public Deck() {
       this.cards = new ArrayList<>();
       texture = new Texture("logo2.jpg");
   }

   public List<Card> getCards() {
       return cards;
   }

   public void addCard(Card card) {
       cards.add(card);
   }

   public void removeCard(Card card) {
       cards.remove(card);
   }

   public void shuffle() {
       Collections.shuffle(cards);
   }

   public Card drawCard() {
       if (!cards.isEmpty()) {
           return cards.remove(cards.size() - 1);
       } else {
           System.out.println("Deck is empty");
           return null;
       }
   }

   public int size() {
       return cards.size();
   }
   
//   public void render(SpriteBatch batch) {
//	   batch.draw(texture,50,50,100,100);
//   }
}