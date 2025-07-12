package com.talosvfx.talos.editor.widgets.ui.common;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.talosvfx.talos.editor.project2.SharedResources;
import lombok.Getter;

public class CollapsableWidget extends Table {
    protected final Table topSegment;
    protected final Cell contentCell;
    @Getter
    protected Table content;
    protected ArrowButton arrowButton;

    protected boolean isCollapsed = true;

    @Getter
    protected Label widgetLabel;

    public CollapsableWidget () {
        setBackground(ColorLibrary.obtainBackground(ColorLibrary.SHAPE_SQUIRCLE, ColorLibrary.BackgroundColor.DARK_GRAY));

        // init components
        topSegment = constructTopSegment();
        constructContent();

        // assemble widget
        add(topSegment).growX();
        row();
        contentCell = add().grow();

        addListeners();
    }

    public CollapsableWidget (String title) {
        this();
        setTitle(title);
    }

    public Table constructTopSegment () {
        // init components
        arrowButton = new ArrowButton(false);
        arrowButton.getCell(arrowButton.getArrowIcon()).pad(0);
        widgetLabel = new Label("", SharedResources.skin, "small");

        final Table topSegment = new Table();
        // NOTE: pads are added to top segment not the entire panel so the click listener also registered paddings
        topSegment.pad(5, 10, 5, 8);

        // assemble top segment
        topSegment.defaults().space(6);
        topSegment.add(arrowButton);
        topSegment.add(widgetLabel).expand().left();
        return topSegment;
    }

    public void setTitle (String title) {
        widgetLabel.setText(title);
    }

    protected void addListeners () {
        // make top segment collapse and open instead of icon, so it was more comfortable to click
        topSegment.setTouchable(Touchable.enabled);
        topSegment.addListener(initClickListener());
    }

    protected ClickListener initClickListener () {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                isCollapsed = !isCollapsed;

                if (isCollapsed) contentCell.setActor(null).pad(0);
                else contentCell.setActor(content).padLeft(topSegment.getPadLeft()).padRight(topSegment.getPadRight()).padBottom(8);

                arrowButton.toggle();
            }
        };
    }

    protected Table constructContent () {
        content = new Table();

        return content;
    }

    public void expand() {
        isCollapsed = false;
        contentCell.setActor(content).padLeft(topSegment.getPadLeft()).padRight(topSegment.getPadRight()).padBottom(8);
        arrowButton.setCollapsed(isCollapsed);
    }
}
