package me.rplgame.miravia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.utils.ScreenUtils;

import me.rplgame.miravia.*;

public class MapScreen implements Screen {
	
	final MiraviaGame game;
	
	OrthographicCamera camera;
	Player playerChar;
	Texture mapBG;
	Texture goButton;

	public MapScreen(MiraviaGame game, Player playerChar) {
		
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, MiraviaGame.WIDTH, MiraviaGame.HEIGHT);
		
		this.playerChar = playerChar;
		this.mapBG =new Texture("map.jpg");
		goButton = new Texture("go.png");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(0.3f, 0.3f, 0.3f, 1);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		
		
		game.batch.draw(mapBG,0,0,MiraviaGame.WIDTH,MiraviaGame.HEIGHT);
		game.batch.draw(goButton,MiraviaGame.WIDTH/2-100,50,200,100);
		
		game.batch.end();

		if (Gdx.input.getX() < MiraviaGame.WIDTH/2 + 200 && 
				Gdx.input.getX() > MiraviaGame.WIDTH/2 && 
				MiraviaGame.HEIGHT - Gdx.input.getY() < 50 + 100 && 
				MiraviaGame.HEIGHT - Gdx.input.getY() > 50) {
			if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				this.dispose();
				game.setScreen(new MainGameScreen(game,playerChar));
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
