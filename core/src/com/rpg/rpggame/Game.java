package com.rpg.rpggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		map = new TmxMapLoader().load("maps/reception.tmx");
		renderer = new OrthogonalTiledMapRenderer((map));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		renderer.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		map.dispose();
		renderer.dispose();
	}
}
