package com.talosvfx.talos.editor.layouts;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public abstract class LayoutItem extends WidgetGroup {

	protected final LayoutGrid grid;
	protected final Skin skin;


	private float relativeWidth;
	private float relativeHeight;

	public LayoutItem (Skin skin, LayoutGrid grid) {
		this.skin = skin;
		this.grid = grid;
	}

	@Override
	public void draw (Batch batch, float parentAlpha) {
		if (clipBegin()) {
			super.draw(batch, parentAlpha);
			clipEnd();
		}
	}


	public abstract boolean isEmpty ();
	public abstract void removeItem (LayoutItem item);

	public abstract void exchangeItem (LayoutItem target, LayoutItem newItem);

	public float getRelativeWidth () {
		return relativeWidth;
	}

	public void setRelativeWidth (float relativeWidth) {
		this.relativeWidth = relativeWidth;
	}

	public float getRelativeHeight () {
		return relativeHeight;
	}

	public void setRelativeHeight (float relativeHeight) {
		this.relativeHeight = relativeHeight;
	}

	public void draggedResizeWidget (LayoutResizeWidget layoutResizeWidget, InputEvent event, float x, float y, int pointer) {
	}

	public void touchedTownResizeWidget (LayoutResizeWidget layoutResizeWidget, InputEvent event, float x, float y, int pointer) {

	}
}
