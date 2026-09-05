package me.rplgame.miravia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.*;

import me.rplgame.miravia.*;
import me.rplgame.miravia.Character;

public class MainGameScreen implements Screen {
	
	private static final int CHAR_WIDTH = 240;
	private static final int CHAR_HEIGHT = 240;
	private static final int CARD_WIDTH = 183;
	private static final int CARD_HEIGHT = 256;
	private static final int CARD_x = 400;
	private static final int CARD_y = 0;
	
	final MiraviaGame game;
	
	OrthographicCamera camera;
	
	Random rand;
	Player playerChar;
	Enemy enemy;
	Texture battleBG;
	Texture endTurnButton;
	Texture youwinMsg;
	Texture youlostMsg;
	Array<Card> handCards;
//	Deck hand = new Deck();
//	Deck selectedHand = new Deck();
	GlyphLayout layout;
	float textHeight;
	int startingHP,damageTaken;
	int ENstartingHP,ENdamageTaken;
	
	public MainGameScreen(final MiraviaGame game, Player playerChar) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, MiraviaGame.WIDTH, MiraviaGame.HEIGHT);
		layout = new GlyphLayout();
		game.font.getData().setScale(2);
		layout.setText(game.font, "Placeholder");
		textHeight = layout.height;
		
		Random rand1 = new Random();
		int enemyLevel = rand1.nextInt(9);
		
		this.playerChar = playerChar;
		this.enemy = new Enemy("barbarian",enemyLevel+1, this.playerChar);
		playerChar.getDeck().shuffle();
		playerChar.getDeck().shuffle();
		playerChar.getDeck().shuffle();
//		hand.addCard(playerChar.getDeck().drawCard());
//		hand.addCard(playerChar.getDeck().drawCard());
//		hand.addCard(playerChar.getDeck().drawCard());
		battleBG = new Texture("battle background.png");
		endTurnButton = new Texture("endTurn.png");
		youwinMsg = new Texture("youwin.png");
		youlostMsg = new Texture("youlost.png");
		rand = new Random();
		handCards = new Array<Card>();
	}
	
	private void drawFromDeck(Deck deck) {
		deck.shuffle();
		int id = rand.nextInt(deck.size());
		if (handCards.size < 3) {
			Card handCard = new Card(
					deck.getCards().get(id)
					.getName());
			handCard.setY(CARD_y);
			handCard.setX(CARD_x+(handCards.size*CARD_WIDTH));
			handCard.setHeight(CARD_HEIGHT);
			handCard.setWidth(CARD_WIDTH);
			handCards.add(handCard);
			//deck.getCards().remove(id);
			
			
		} 
		
	}
	
	private void endTurn(Array<Card> cardplay) {
		int damageDeal = 0;
		for(Card card : cardplay) {
			if (card.isSelected()) {
				damageDeal += card.getDamage();
			}
		}
		enemy.takeDamage(damageDeal);
		enemy.turn();
		cardplay.clear();
		playerChar.addScore(damageDeal*3);
		playerChar.setMana(playerChar.getMAX_MANA());
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0.3f, 1);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(battleBG, 0, 0, MiraviaGame.WIDTH, MiraviaGame.HEIGHT);
		game.batch.draw(playerChar.getTexture(),100,100,CHAR_WIDTH,CHAR_HEIGHT);
		game.batch.draw(enemy.getTexture(), 1180-240, 100, CHAR_WIDTH,CHAR_HEIGHT);
		game.font.draw(game.batch,"Health: "+playerChar.getHealth(), 100,100+CHAR_HEIGHT+2*(textHeight+10));
		game.font.draw(game.batch,"Action Points: "+playerChar.getMana(), 100,100+CHAR_HEIGHT+textHeight+10);
		game.font.draw(game.batch,"Enemy Health: "+enemy.getHealth(), 1180-240,100+CHAR_HEIGHT+3*(textHeight+10));
		game.font.draw(game.batch,"Enemy attacks in "+(enemy.getTurnsBeforeAttack()+1)+" turn", 1180-240,100+CHAR_HEIGHT+2*(textHeight+10));
		game.font.draw(game.batch,"dealing "+enemy.getAttack()+" damage", 1180-240,100+CHAR_HEIGHT+textHeight+10);
		
		
		if (Gdx.input.getX() < 900 + 200 && 
				Gdx.input.getX() > 900 && 
				MiraviaGame.HEIGHT - Gdx.input.getY() < Gdx.graphics.getHeight() - 180 + 200 && 
				MiraviaGame.HEIGHT - Gdx.input.getY() > Gdx.graphics.getHeight() - 180) {
			game.batch.draw(endTurnButton, 900, Gdx.graphics.getHeight() - 180, 200,100);
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				startingHP = playerChar.getHealth();
				damageTaken = 0;
				ENstartingHP = enemy.getHealth();
				ENdamageTaken = 0;
				endTurn(handCards);
				if (startingHP != playerChar.getHealth()) {
					damageTaken = startingHP - playerChar.getHealth();
				}
				if (ENstartingHP != enemy.getHealth()) {
					ENdamageTaken = ENstartingHP - enemy.getHealth();
				}
				
				 
			}
		} else {
			game.batch.draw(endTurnButton, 900, Gdx.graphics.getHeight() - 150, 200,100);
			
		}
		if (damageTaken != 0 ) {
			game.font.draw(game.batch,"Player takes "+damageTaken+" damage", 10, Gdx.graphics.getHeight()-20-textHeight-10);
		}
		if (ENdamageTaken != 0 ) {
			game.font.draw(game.batch,"Enemy takes "+ENdamageTaken+" damage", 10, Gdx.graphics.getHeight()-20);
		}
		
		
		
		while (handCards.size < 3) {
			drawFromDeck(playerChar.getDeck());
		}
		
		Iterator<Card> iter = handCards.iterator();
		while(iter.hasNext()) {
			Card handCard = iter.next();
			game.batch.draw(handCard.getTextureC1(),handCard.getX(),handCard.getY(),
					handCard.getWidth(),handCard.getHeight());
		}
		//win condition
		if(enemy.getHealth()<=0) {
			game.batch.draw(youwinMsg,400,500,360,176);
			game.font.draw(game.batch, "click here to continue", 400, 500);
			game.font.draw(game.batch, "you got "+20*enemy.getLevel()+" gold", 400, 450);
			
			if(Gdx.input.getX() < 400 + 360&& 
					Gdx.input.getX() > 400 && 
					MiraviaGame.HEIGHT - Gdx.input.getY() < 500 + 176&& 
					MiraviaGame.HEIGHT - Gdx.input.getY() > 500 &&
					Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				this.dispose();
				playerChar.addMoney(20*enemy.getLevel());
				playerChar.addScore(100);
				game.setScreen(new ShopScreen(game, playerChar));
				
			}
			
		}
		//lost condition
		if(playerChar.getHealth()<=0&&playerChar.getHealth()>-999) {
			//playerChar.addScore(playerChar.getMoney()/2);
			game.batch.draw(youlostMsg,400,500,360,176);
			game.font.draw(game.batch, "click here to go back", 400, 500);
			game.font.draw(game.batch, "your score "+playerChar.getScore(), 400, 450);
			
			if(Gdx.input.getX() < 400 + 360&& 
					Gdx.input.getX() > 400 && 
					MiraviaGame.HEIGHT - Gdx.input.getY() < 500 + 176&& 
					MiraviaGame.HEIGHT - Gdx.input.getY() > 500 &&
					Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				this.dispose();
				
				game.setScreen(new MainMenuScreen(game));
				
			}
//			playerChar.setHealth(-1000);
			
		}
		game.batch.end();
		
		if (handCards.get(0).getManacost()<=playerChar.getMana() &&
				Gdx.input.getX() < handCards.get(0).getX() + CARD_WIDTH && 
				Gdx.input.getX() > handCards.get(0).getX() && 
				MiraviaGame.HEIGHT - Gdx.input.getY() < handCards.get(0).getY()+ CARD_HEIGHT && 
				MiraviaGame.HEIGHT - Gdx.input.getY() > handCards.get(0).getY()) {
			
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				//if first card selected
				if (!handCards.get(0).isSelected()) {
					handCards.get(0).setY(CARD_y + 20);
					handCards.get(0).setSelected(true);
					playerChar.reduceMana(handCards.get(0).getManacost());
				} else {
					handCards.get(0).setY(CARD_y);
					handCards.get(0).setSelected(false);
					playerChar.addMana(handCards.get(0).getManacost());
				}
			}
		}
		
		if (handCards.get(1).getManacost()<=playerChar.getMana() &&
				Gdx.input.getX() < handCards.get(1).getX() + CARD_WIDTH && 
				Gdx.input.getX() > handCards.get(1).getX() && 
				MiraviaGame.HEIGHT - Gdx.input.getY() < handCards.get(1).getY()+ CARD_HEIGHT && 
				MiraviaGame.HEIGHT - Gdx.input.getY() > handCards.get(1).getY()) {
			
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				//if first card selected
				if (!handCards.get(1).isSelected()) {
					handCards.get(1).setY(CARD_y + 20);
					handCards.get(1).setSelected(true);
					playerChar.reduceMana(handCards.get(1).getManacost());
				} else {
					handCards.get(1).setY(CARD_y);
					handCards.get(1).setSelected(false);
					playerChar.addMana(handCards.get(1).getManacost());
				}
			}
		}
		if (handCards.get(2).getManacost()<=playerChar.getMana() &&
				Gdx.input.getX() < handCards.get(2).getX() + CARD_WIDTH && 
				Gdx.input.getX() > handCards.get(2).getX() && 
				MiraviaGame.HEIGHT - Gdx.input.getY() < handCards.get(2).getY()+ CARD_HEIGHT && 
				MiraviaGame.HEIGHT - Gdx.input.getY() > handCards.get(2).getY()) {
			
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				//if first card selected
				if (!handCards.get(2).isSelected()) {
					handCards.get(2).setY(CARD_y + 20);
					handCards.get(2).setSelected(true);
					playerChar.reduceMana(handCards.get(2).getManacost());
				} else {
					handCards.get(2).setY(CARD_y);
					handCards.get(2).setSelected(false);
					playerChar.addMana(handCards.get(2).getManacost());
				}
			}
		}
	}
	

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}
