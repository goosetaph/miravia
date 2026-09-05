package me.rplgame.miravia.screens;
import me.rplgame.miravia.*;
import me.rplgame.miravia.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class CharCreateScreen implements Screen {
	
	
	
	private static final int CHAR_WIDTH = 120;
	private static final int CHAR_HEIGHT = 120;
	private static final int CHAR_Y = 160;
	private static final int ARCHER_X = (MiraviaGame.WIDTH/2) - CHAR_WIDTH/2;
	private static final int SWORDSMAN_X = (MiraviaGame.WIDTH/2)+200 - CHAR_WIDTH/2;
	private static final int WARRIOR_X = (MiraviaGame.WIDTH/2)-200 - CHAR_WIDTH/2;
	
	
	Deck defaultDeck = new Deck();
	Card card1 = new Card("tap");
	Card card2 = new Card("hit");
	Card card3 = new Card("smash");
	Card card4 = new Card("poke");
	Card card5 = new Card("slap");
	Card card6 = new Card("tap");
	Card card7 = new Card("hit");
	Card card8 = new Card("smash");
	
	final MiraviaGame game;
	
	OrthographicCamera camera;
	
	Texture warriorPNG;
	Texture archerPNG;
	Texture swordsmanPNG;
	Texture backButton;
	Texture charCreateBG;
	float textWidth;

	GlyphLayout layout;
	
	public CharCreateScreen(final MiraviaGame game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, MiraviaGame.WIDTH, MiraviaGame.HEIGHT);
		layout = new GlyphLayout();
		layout.setText(game.font, "Choose your character");
		textWidth = layout.width;
		
		warriorPNG = new Texture("macy.png");
		archerPNG = new Texture("archie.png");
		swordsmanPNG = new Texture("knightsword.png");
		backButton = new Texture("return.png");
		charCreateBG = new Texture("charCreateBG.jpg");
		defaultDeck.addCard(card1);
		defaultDeck.addCard(card2);
		defaultDeck.addCard(card3);
		defaultDeck.addCard(card4);
		defaultDeck.addCard(card5);
		
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.3f, 1);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(charCreateBG, 0, 0, MiraviaGame.WIDTH, MiraviaGame.HEIGHT);
		game.font.draw(game.batch, "Choose your character", MiraviaGame.WIDTH/2-textWidth/2, 400);
		
		//back to menu
		if(Gdx.input.getX()>30 && Gdx.input.getX()<30+50 
				&& MiraviaGame.HEIGHT - Gdx.input.getY()>MiraviaGame.HEIGHT-80 
				&& MiraviaGame.HEIGHT - Gdx.input.getY()<MiraviaGame.HEIGHT-30
				) {
			//backButton.dispose();
			game.batch.draw(backButton,25,MiraviaGame.HEIGHT-120,100,100);
			if(Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				
				this.dispose();
				game.setScreen(new MainMenuScreen(game));
				
			}
		} else {
			game.batch.draw(backButton,50,MiraviaGame.HEIGHT-80,50,50);
		}
		/* TODO more options and refinement for character creation
		 * eg: choosing deck, set stat, set name, etc
		 */
		//choose 'archer'
		if(Gdx.input.getX()>ARCHER_X && Gdx.input.getX()<ARCHER_X + CHAR_WIDTH
				&& MiraviaGame.HEIGHT - Gdx.input.getY()<CHAR_Y + CHAR_HEIGHT
				&& MiraviaGame.HEIGHT - Gdx.input.getY()>CHAR_Y
				) {
			game.batch.draw(archerPNG, ARCHER_X-10 , CHAR_Y, CHAR_WIDTH+20, CHAR_HEIGHT+20);
			if(Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				Player playerChar = new Player("archie",100,10,10,5,defaultDeck);		//create class player
				this.dispose();
				game.setScreen(new MapScreen(game, playerChar));
				
			}
		}else {
			game.batch.draw(archerPNG, ARCHER_X, CHAR_Y, CHAR_WIDTH, CHAR_HEIGHT);
		}
		
		//choose 'warrior'
		if(Gdx.input.getX()>WARRIOR_X && Gdx.input.getX()<WARRIOR_X + CHAR_WIDTH
				&& MiraviaGame.HEIGHT - Gdx.input.getY()<CHAR_Y + CHAR_HEIGHT
				&& MiraviaGame.HEIGHT - Gdx.input.getY()>CHAR_Y
				) {
			game.batch.draw(warriorPNG, WARRIOR_X-10 , CHAR_Y, CHAR_WIDTH+20, CHAR_HEIGHT+20);
			if(Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				Player playerChar = new Player("macy",100,10,10,4,defaultDeck);
				this.dispose();
				game.setScreen(new MapScreen(game, playerChar));
				
			}
		} else {
			game.batch.draw(warriorPNG, WARRIOR_X , CHAR_Y, CHAR_WIDTH, CHAR_HEIGHT);
		}
		
		//choose 'swordsman'
		if(Gdx.input.getX()>SWORDSMAN_X && Gdx.input.getX()<SWORDSMAN_X + CHAR_WIDTH
				&& MiraviaGame.HEIGHT - Gdx.input.getY()<CHAR_Y + CHAR_HEIGHT
				&& MiraviaGame.HEIGHT - Gdx.input.getY()>CHAR_Y
				) {
			game.batch.draw(swordsmanPNG, SWORDSMAN_X-10 , CHAR_Y, CHAR_WIDTH+20, CHAR_HEIGHT+20);
			if(Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				Player playerChar = new Player("swordsman",100,10,10,5,defaultDeck);
				this.dispose();
				game.setScreen(new MapScreen(game, playerChar));
				
			}
		} else {
			game.batch.draw(swordsmanPNG, SWORDSMAN_X , CHAR_Y, CHAR_WIDTH, CHAR_HEIGHT);
		}
		
		game.batch.end();
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
