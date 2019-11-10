package com.anabiozzze.sample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class TextSample extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stage;
	TextButton button;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;
	Skin skin;
	TextureAtlas atlas;
	Table menuTable;
	Label label;
	Label.LabelStyle labelStyle;
	boolean isPressed;

	float width, height, wCenter, hCenter, areaSize;
	
	@Override
	public void create () {

		menuTable = new Table();
		menuTable.setColor(1,1,0,1);
		menuTable.center();
		menuTable.setVisible(true);



		batch = new SpriteBatch();
		stage = new Stage();

		width = stage.getWidth();
		height = stage.getHeight();

		areaSize = height*0.90f;
		wCenter = width/2f - areaSize/2;
		hCenter = height/2f - areaSize/2;


		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont();

		atlas = new TextureAtlas();
		skin = new Skin(atlas);
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.fontColor = new Color(0,0,0,1);
		button = new TextButton("start", textButtonStyle);

		button.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				System.out.println("Button Pressed");
				isPressed = true;
			}
		});


		labelStyle = new Label.LabelStyle();
		labelStyle.font = font;
		labelStyle.fontColor = new Color(0,0,0,1);
		label = new Label("Lorem ipsum dolor sit amet,\n" +
				"consectetur adipiscing elit,\n" +
				"sed do eiusmod tempor incididunt\n" +
				"ut labore et dolore magna aliqua.", labelStyle);


		label.setAlignment((int)menuTable.getColumnWidth(1));


		menuTable.add(label).size(areaSize*0.8f,areaSize*0.78f);
		menuTable.row();
		menuTable.add(button).size(areaSize*0.8f,areaSize*0.20f);



		menuTable.setSize(areaSize, areaSize);
		menuTable.setPosition(wCenter, hCenter);

		stage.addActor(menuTable);

		menuTable.debug();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(207/255f, 226/255f, 243/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		stage.draw();

		label.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				if (isPressed) {
					label.setVisible(false);
					stage.addActor(menuTable);
				}
				return true;
			}
		});


		ShapeRenderer renderer = new ShapeRenderer();
		renderer.setAutoShapeType(true);
		renderer.begin();
		menuTable.drawDebug(renderer);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();
	}
}
