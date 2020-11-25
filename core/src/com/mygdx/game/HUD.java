package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD {
	public Stage stage;
	private Viewport viewport;

	private static int worldTimer;
	private float timeCount;
	private static int score;
	private static int worldTimerFinal = 30;

	Label countdownLabel;
	static Label scoreLabel;
	Label timeLabel;
	Label scoreLabe;
	private Transporte game;

	public HUD(SpriteBatch sb, Transporte game) {
		this.game = game;
		worldTimer = worldTimerFinal;
		timeCount = 0;
		score = 0;

		viewport = new FitViewport(800, 640, new OrthographicCamera());
		stage = new Stage(viewport, sb);

		Table table = new Table();
		table.top();

		table.setFillParent(true);
		timeLabel = new Label("TIME:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		scoreLabe = new Label("SCORE:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

		table.add(scoreLabe).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);

		table.row();
		scoreLabel = new Label(String.format("%05d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		countdownLabel = new Label(String.format("%03d", worldTimer),
				new Label.LabelStyle(new BitmapFont(), Color.WHITE));

		table.add(scoreLabel).expandX().padTop(10);
		table.add(countdownLabel).expandX().padTop(10);

		stage.addActor(table);
	}

	public void update(float delta) {
		timeCount += delta;
		if (timeCount >= 1) {
			worldTimer -= 1;
			countdownLabel.setText(String.format("%03d", worldTimer));
			timeCount = 0;
			addScore(1);
			if(worldTimer <= 0) {
				game.setScreen(new FimDeJogo(game));
			}
		}
	}
	
	public static void addScore(int valor) {
		score += valor;
		scoreLabel.setText(String.format("%05d", score));
	}
	
	public static int getFinalScore() {
		return score + worldTimer * 5;
	}
	
	public static int getFinalTime() {
		return worldTimerFinal - worldTimer;
	}
	
}
