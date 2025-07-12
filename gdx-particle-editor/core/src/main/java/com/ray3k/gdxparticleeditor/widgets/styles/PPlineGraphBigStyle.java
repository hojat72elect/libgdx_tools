package com.ray3k.gdxparticleeditor.widgets.styles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.ray3k.gdxparticleeditor.widgets.LineGraph.LineGraphStyle;

import static com.ray3k.gdxparticleeditor.Core.skin;

public class PPlineGraphBigStyle extends LineGraphStyle {
    public PPlineGraphBigStyle() {
        background = skin.getDrawable("graph-big-bg-10");
        backgroundLabelStyle = skin.get("graph", LabelStyle.class);
        knobLabelStyle = skin.get("graph-node", LabelStyle.class);
        nodeUp = skin.getDrawable("graph-node-up");
        nodeDown = skin.getDrawable("graph-node-over");
        nodeOver = skin.getDrawable("graph-node-over");
        lineColor = Color.valueOf("0074ff");
        lineWidth = 2;
    }
}
