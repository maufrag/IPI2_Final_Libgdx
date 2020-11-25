package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class FimDeJogo implements Screen {
	private Viewport viewport;
	private Stage stage;
	private Player player;
	private Transporte game;

	// TODO - adicionar labels com pontuação
	// Adicionar botao para voltar ao menu

	public FimDeJogo(Transporte game) {
		this.game = game;
		viewport = new FitViewport(800, 640, new OrthographicCamera());
		stage = new Stage(viewport, game.batch);

		Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

		Table table = new Table();
		table.top();
		table.setFillParent(true);
		Label fimDeJogoLabel = new Label("Fim de Jogo", font);
		table.add(fimDeJogoLabel);
		Label timeLabel = new Label("Time: ", font);
		Label scoreLabel = new Label("Score: ", font);
		table.center();
		table.row();
		table.add(scoreLabel);
		table.add(new Label(String.format("%05d", HUD.getFinalScore()), font));
		table.row();
		table.add(timeLabel);
		table.add(new Label(String.format("%03d", HUD.getFinalTime()), font));


		stage.addActor(table);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();

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
