package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Sprite implements InputProcessor {
	private Vector2 velocidade = new Vector2();

	private float rapidez = 60 * 2;
	private TiledMapTileLayer collisionLayer;
	private String blockedKey = "blocked";
	public World world;
	public Body b2Body;
	private boolean hasPackage;

	public Player(Sprite sprite, TiledMapTileLayer collision) {
		super(sprite);
		this.collisionLayer = collision;
		setSize(20, 30);
	}

	@Override
	public void draw(Batch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}

	public void update(float delta) {
		System.out.println("x" + getX());
		System.out.println("y" + getY());
		float oldX = getX();
		float oldY = getY();
		float tileWidth = collisionLayer.getTileWidth();
		float tileHeight = collisionLayer.getTileHeight();

		boolean collisionX = false;
		boolean collisionY = false;

		setX(getX() + velocidade.x * delta);

		if (velocidade.x < 0) {
			collisionX = isCellBlocked(getX(), getY() + getHeight());

			if (!collisionX)
				collisionX = isCellBlocked(getX(), getY() + getHeight() / 2);

			if (!collisionX)
				collisionX = isCellBlocked(getX(), getY());
		} else if (velocidade.x > 0) {
			collisionX = isCellBlocked(getX() + getWidth(), getY() + getHeight());

			if (!collisionX)
				collisionX = isCellBlocked(getX() + getWidth(), getY() + getHeight() / 2);

			if (!collisionX)
				collisionX = isCellBlocked(getX() + getWidth(), getY());
		}

		if (collisionX) {
			setX(oldX);
			velocidade.x = 0;
		}

		setY(getY() + velocidade.y * delta);

		if (velocidade.y < 0) {

			collisionY = isCellBlocked(getX(), getY());

			if (!collisionY)
				collisionY = isCellBlocked(getX() + getWidth() / 2, getY());

			if (!collisionY)
				collisionY = isCellBlocked(getX() + getWidth(), getY());

		} else if (velocidade.y > 0) {

			collisionY = isCellBlocked(getX(), getY() + getHeight());

			if (!collisionY)
				collisionY = isCellBlocked(getX() + getWidth() / 2, getY() + getHeight());

			if (!collisionY)
				collisionY = isCellBlocked(getX() + getWidth(), getY() + getHeight());

		}

		if (collisionY) {
			setY(oldY);
			velocidade.y = 0;
		}
	}

	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x + getWidth() / collisionLayer.getTileWidth()),
				(int) (y + getHeight() / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile().getProperties().containsKey(blockedKey);
	}

	public Vector2 getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Vector2 velocidade) {
		this.velocidade = velocidade;
	}

	public float getRapidez() {
		return rapidez;
	}

	public void setRapidez(float rapidez) {
		this.rapidez = rapidez;
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	public String getBlockedKey() {
		return blockedKey;
	}

	public void setBlockedKey(String blockedKey) {
		this.blockedKey = blockedKey;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.W:
			velocidade.y = rapidez;
			break;
		case Keys.A:
			velocidade.x = -rapidez;
			break;
		case Keys.S:
			velocidade.y = -rapidez;
			break;
		case Keys.D:
			velocidade.x = rapidez;
			break;
		case Keys.SPACE:
			break;

		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.W:
			velocidade.y = 0;
			break;
		case Keys.A:
			velocidade.x = 0;
			break;
		case Keys.S:
			velocidade.y = 0;
			break;
		case Keys.D:
			velocidade.x = 0;
			break;

		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {

		return false;
	}

	public void setHasPackage(boolean value) {
		if (!hasPackage) {
			this.hasPackage = value;
			HUD.addScore(100);
		}
	}

	public boolean getHasPackage() {
		return hasPackage;
	}

}
