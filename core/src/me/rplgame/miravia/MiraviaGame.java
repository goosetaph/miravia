package me.rplgame.miravia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.rplgame.miravia.screens.MainMenuScreen;

public class MiraviaGame extends Game {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	public SpriteBatch batch;
	public BitmapFont font;
	
	public GlyphLayout layout;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
		font.getData().setScale(3);
		
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
	
}
