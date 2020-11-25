package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu implements Screen {

	private Transporte game;
	Texture exitButton;
	Texture playButton;

	public MainMenu(Transporte game) {
		this.game = game;
		playButton = new Texture("play.jpg");
		exitButton = new Texture("exit.jpg");
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		
		int x = Transporte.width / 2 - 200 / 2;
		game.batch.draw(playButton, x, 400, 200, 75);
		if (isOverButton(x, 400, 200, 75)) {
			System.out.println();
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.setScreen(new Partida(game));
			}
		}
		x = Transporte.width / 2 - 200 / 2;
		if (isOverButton(x, 100, 200, 75)) {
			if (Gdx.input.isTouched()) {
				Gdx.app.exit();
			}
		}
		
		game.batch.draw(exitButton, x, 100, 200, 75);

		
		
		game.batch.end();
	}

	public boolean isOverButton(int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
		return Gdx.input.getX() < buttonX + buttonWidth 
				&& Gdx.input.getX() > buttonX
				&& Transporte.height - Gdx.input.getY() < buttonY + buttonHeight
				&& Transporte.height - Gdx.input.getY() > buttonY;
	}

	public int obterDimensaoBotao(Texture button) {
		return 0;
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

};