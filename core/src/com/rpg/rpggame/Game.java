package com.rpg.rpggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	TiledMap tiledMap;
	AssetManager manager;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	ArrayList<Rectangle> bounds;
	private Viewport gamePort;
	private Sprite sprite;
	private Texture texture;
	Vector2 position;
	InputProcessor inputProcessor;
	Player player;
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1,h/w);
		batch = new SpriteBatch();
		camera.update();
		tiledMap = new TmxMapLoader().load("maps/reception.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		texture = new Texture("raw/reception.png");
		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		sprite = new Sprite(region);
		sprite.setSize(0.9f,0.9f* sprite.getHeight()/sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2,-sprite.getHeight()/2);


		if(Gdx.files.local("player.dat").exists()){
			try {
				player = Player.readPlayer();
			} catch(ClassNotFoundException | IOException e){
				e.printStackTrace();
			}
		} else{
			player = new Player(new Vector2(w/2, h/2),"sprites/adam.png");
			try {
				Player.savePlayer(player);
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		player.update();


		/*tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		batch.setProjectionMatrix(camera.combined);*/
		batch.begin();
		//sprite.draw(batch,x,y);
		batch.draw(texture,player.getPosition().x,player.getPosition().y);
		batch.end();

	}
	
	@Override
	public void dispose () {
		try {
			Player.savePlayer(player);
		} catch (IOException e){
			e.printStackTrace();
		}
		batch.dispose();
		tiledMap.dispose();
		tiledMapRenderer.dispose();
	}
}
