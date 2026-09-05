package me.rplgame.miravia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import me.rplgame.miravia.MiraviaGame;

public class MainMenuScreen implements Screen{
	
	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 150;
	private static final int EXIT_BUTTON_Y = 50;
	private static final int PLAY_BUTTON_Y = 200;
	private static final int LOGO_WIDTH = 250;
	private static final int LOGO_HEIGHT = 250;
	
	
	
	final MiraviaGame game;
	
	OrthographicCamera camera;
	
	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture playButtonActive;
	Texture playButtonInactive;
	Texture gameLogo;
	Texture mainMenuBG;
	
	public MainMenuScreen(final MiraviaGame game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, MiraviaGame.WIDTH, MiraviaGame.HEIGHT);
		
		exitButtonActive = new Texture("exit(1).png");
		exitButtonInactive = new Texture("exit.png");
		playButtonActive = new Texture("start(1).png");
		playButtonInactive = new Texture("start.png");
		gameLogo = new Texture("title.png");
		mainMenuBG = new Texture("mainBG.jpg");
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
		
		game.batch.draw(mainMenuBG, 0, 0, MiraviaGame.WIDTH, MiraviaGame.HEIGHT);
		game.batch.draw(gameLogo, MiraviaGame.WIDTH/2 - LOGO_WIDTH/2, 400, LOGO_WIDTH, LOGO_HEIGHT);
		
		
		int x = MiraviaGame.WIDTH/2 - BUTTON_WIDTH/2;
		
		//exit game
		if (Gdx.input.getX() < x + BUTTON_WIDTH && 
				Gdx.input.getX() > x && 
				MiraviaGame.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + BUTTON_HEIGHT && 
				MiraviaGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
			game.batch.draw(exitButtonActive, x , EXIT_BUTTON_Y , BUTTON_WIDTH, BUTTON_HEIGHT);
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				Gdx.app.exit();
			}
		} else {
			game.batch.draw(exitButtonInactive,x , EXIT_BUTTON_Y , BUTTON_WIDTH, BUTTON_HEIGHT);
		}
		//start game
		if (Gdx.input.getX() < x + BUTTON_WIDTH && 
				Gdx.input.getX() > x && 
				MiraviaGame.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + BUTTON_HEIGHT && 
				MiraviaGame.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y) {
			game.batch.draw(playButtonActive, x , PLAY_BUTTON_Y , BUTTON_WIDTH, BUTTON_HEIGHT);
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				this.dispose();
				game.setScreen(new CharCreateScreen(game));
			}
		} else {
			game.batch.draw(playButtonInactive,x , PLAY_BUTTON_Y , BUTTON_WIDTH, BUTTON_HEIGHT);
			
		}
		
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}
