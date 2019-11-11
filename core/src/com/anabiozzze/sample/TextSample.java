package com.anabiozzze.sample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class TextSample extends ApplicationAdapter {
	private SpriteBatch batch;
	private Stage stage;

	// Table for the assignment of elements
	private Table menuTable;

	// Buttons, text boxes, fonts, bg for table
	private BitmapFont font;
	private TextButton button;
	private TextButton.TextButtonStyle textButtonStyle;
	private Label label;
	private Label.LabelStyle labelStyle;
	private Skin skin;
	private NinePatch patch;
	private NinePatchDrawable background;


	private boolean isPressed; // Click flag, needed to stop, start and restart text animation
	private float width, height, wCenter, hCenter, areaSize; // Coordinates for the table in the stage
	private String text; // Original text
	private String result; // Here we will add the resulting text
	private int iter = 0; // Allows not exceeding the allowed number of characters

	private int count; // Iteration counter for render()

	@Override
	public void create () {
		count = 0;
		text = ("Lorem ipsum dolor sit amet,\n" +
				"consectetur adipiscing elit,\n" +
				"sed do eiusmod tempor incididunt\n" +
				"ut labore et dolore magna aliqua.");

		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		width = stage.getWidth();
		height = stage.getHeight();

		// Sides, coordinates of the table on center of stage
		areaSize = height*0.90f;
		wCenter = width/2f - areaSize/2;
		hCenter = height/2f - areaSize/2;

		// Create a table, set size and coordinates
		menuTable = new Table();
		menuTable.center();
		menuTable.setSize(areaSize, areaSize);
		menuTable.setPosition(wCenter, hCenter);

		skin = new Skin(Gdx.files.internal("uiskin.json"));

		button = new TextButton("start", skin);
		button.getStyle().fontColor = new Color(0.1f,0.1f,0.1f,0.9f);

		// Allows to restart the animation with any click
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				isPressed = true;
				iter =0;
			}
		});

		// While the label is empty, fill it in showText()
		label = new Label("", skin);
		label.getStyle().fontColor = new Color(0.1f,0.1f,0.1f,0.9f);
		label.setAlignment((int)menuTable.getColumnWidth(1));

		menuTable.add(label).size(areaSize,areaSize*0.80f);
		menuTable.row();
		menuTable.add(button).size(areaSize*0.50f,areaSize*0.10f);

		// setting table bg style
		patch = new NinePatch(new Texture(Gdx.files.internal("background.png")), 3, 3, 3, 3);
		background = new NinePatchDrawable(patch);
		menuTable.setBackground(background);

		stage.addActor(menuTable);
//		menuTable.debug();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(207/255f, 226/255f, 243/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		stage.draw();

		// Only for displaying table fields (work area), like a debug-mode
//		ShapeRenderer renderer = new ShapeRenderer();
//		renderer.setAutoShapeType(true);
//		renderer.begin();
//		menuTable.drawDebug(renderer);

		// Main actions
		count++;
		showText();

		batch.end();
	}

	// Shows text's chars 10 times per second
	private void showText() {
		if (isPressed) {
			if (count%6==0 && iter<text.length()) {
				result = text.substring(0, iter + 1);

				// It is important to interrupt the method after each added char!
				iter++; return;
			}
		}
		label.setText(result);
	}


	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();
	}


	// GETTERS, JUST IN CASE

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getwCenter() {
		return wCenter;
	}

	public float gethCenter() {
		return hCenter;
	}

	public float getAreaSize() {
		return areaSize;
	}

	public String getText() {
		return text;
	}

	public String getResult() {
		return result;
	}
}