package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Partida implements Screen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;
	private static final int width = 800;
	private static final int height = 640;
	public HUD hud;
	private Transporte game;

	public Partida(Transporte game) {
		this.game = game;
	}

	@Override
	public void show() {
		camera = new OrthographicCamera();

		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("untitled1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		hud = new HUD((SpriteBatch) renderer.getBatch(), game);

		player = new Player(new Sprite(new Texture("car_black_small_4.png")),
				(TiledMapTileLayer) map.getLayers().get(0));
		player.setPosition(11 * player.getCollisionLayer().getTileWidth(),
				38 * player.getCollisionLayer().getTileHeight());

		Gdx.input.setInputProcessor(player);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();
		renderer.getBatch().setProjectionMatrix(camera.combined);

		renderer.setView(camera);
		renderer.render();

		renderer.getBatch().begin();
		player.draw(renderer.getBatch());
		renderer.getBatch().end();
		hud.stage.draw();
		hud.update(delta);

		if ((int) player.getX() >= 604 && (int) player.getX() <= 650 && (int) player.getY() >= 614
				&& (int) player.getY() <= 660) {
			player.setHasPackage(true);
		}

		if ((int) player.getX() >= 19 && 
				(int) player.getX() <= 45 &&
				(int) player.getY() >= 220 && 
				(int) player.getY() <= 224 &&
				player.getHasPackage()) {
			game.setScreen(new FimDeJogo(game));
		}

	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
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
		dispose();
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		player.getTexture().dispose();
	}

}
