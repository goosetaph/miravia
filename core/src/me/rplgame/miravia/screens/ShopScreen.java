package me.rplgame.miravia.screens;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import me.rplgame.miravia.Card;
import me.rplgame.miravia.MiraviaGame;
import me.rplgame.miravia.Player;

public class ShopScreen implements Screen{
	private static final int CARD_WIDTH = 183;
	private static final int CARD_HEIGHT = 256;
	private static final int CARD_y = (MiraviaGame.HEIGHT/2)-256/2;
	
	final MiraviaGame game;
	
	OrthographicCamera camera;
	
	Player playerChar;
	Texture shopBG;
	Texture goButton;
	Texture health;
	Card cardShop1;
	Card cardShop2;
	Array<Card> shopCards;
	
	public ShopScreen(final MiraviaGame game, Player playerChar) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, MiraviaGame.WIDTH, MiraviaGame.HEIGHT);
		
		this.playerChar = playerChar;
		shopBG = new Texture("shopBG.jpg");
		goButton = new Texture("go.png");
		cardShop1 = new Card("mace");
		cardShop2 = new Card("slash");
		health = new Texture("potion.png");
		cardShop1.setHeight(256);
		cardShop2.setHeight(256);
		cardShop1.setWidth(183);
		cardShop2.setWidth(183);
		cardShop1.setX((MiraviaGame.WIDTH/3)-183/2);
		cardShop2.setX((2*MiraviaGame.WIDTH)/3 - 183/2);
		cardShop1.setY((MiraviaGame.HEIGHT/2)-256/2);
		cardShop2.setY((MiraviaGame.HEIGHT/2)-256/2);
		shopCards = new Array<Card>();
		shopCards.add(cardShop2, cardShop1);
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(0, 0, 0.3f, 1);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(shopBG, 0, 0, MiraviaGame.WIDTH, MiraviaGame.HEIGHT);
		game.font.draw(game.batch,"Gold: "+playerChar.getMoney(),50,50);
		Iterator<Card> iter = shopCards.iterator();
		while(iter.hasNext()) {
			Card shopCard = iter.next();
			game.batch.draw(shopCard.getTextureC1(),shopCard.getX(),shopCard.getY(),
					shopCard.getWidth(),shopCard.getHeight());
			game.font.draw(game.batch,"100",shopCard.getX()+183/2-10,shopCard.getY());
		}
		game.batch.draw(health,MiraviaGame.WIDTH/2-75, MiraviaGame.HEIGHT/2,150,150);
		game.font.draw(game.batch,"300",MiraviaGame.WIDTH/2, MiraviaGame.HEIGHT/2);
		
		game.batch.draw(goButton,MiraviaGame.WIDTH/2-100,50,200,100);
		
		game.batch.end();
		if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
			System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());
		}
		
		if (Gdx.input.getX() < 640 + 75&&
				Gdx.input.getX()>640&&
				MiraviaGame.HEIGHT - Gdx.input.getY() < 360+ 150 &&
				MiraviaGame.HEIGHT - Gdx.input.getY() > 360) {
			System.out.println("here");	
			if( 300<=playerChar.getMoney() && Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				playerChar.setHealth(playerChar.getMAX_HEALTH());
				playerChar.reduceMoney(300);
			}
		}
		
		if (100<=playerChar.getMoney() &&shopCards.size == 1 &&
				Gdx.input.getX() < shopCards.get(0).getX() + CARD_WIDTH && 
				Gdx.input.getX() > shopCards.get(0).getX() && 
				MiraviaGame.HEIGHT - Gdx.input.getY() < shopCards.get(0).getY()+ CARD_HEIGHT && 
				MiraviaGame.HEIGHT - Gdx.input.getY() > shopCards.get(0).getY()) {
			
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				//if first card selected
				playerChar.reduceMoney(100);
				playerChar.getDeck().addCard(shopCards.removeIndex(0));
			}
		}
		
		if (100<=playerChar.getMoney() && shopCards.size == 2 &&
				Gdx.input.getX() < shopCards.get(1).getX() + CARD_WIDTH && 
				Gdx.input.getX() > shopCards.get(1).getX() && 
				MiraviaGame.HEIGHT - Gdx.input.getY() < shopCards.get(1).getY()+ CARD_HEIGHT && 
				MiraviaGame.HEIGHT - Gdx.input.getY() > shopCards.get(1).getY()) {
			
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				playerChar.reduceMoney(100);
				playerChar.getDeck().addCard(shopCards.removeIndex(1));
			}
		}
		if (Gdx.input.getX() < MiraviaGame.WIDTH/2 + 200 && 
				Gdx.input.getX() > MiraviaGame.WIDTH/2 && 
				MiraviaGame.HEIGHT - Gdx.input.getY() < 50 + 100 && 
				MiraviaGame.HEIGHT - Gdx.input.getY() > 50) {
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				this.dispose();
				game.setScreen(new MapScreen(game,playerChar));
			}
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
