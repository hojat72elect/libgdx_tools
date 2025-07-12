package com.ray3k.gdxparticleeditor.widgets.styles;

import com.ray3k.stripe.DraggableList.DraggableListStyle;

import static com.ray3k.gdxparticleeditor.Core.skin;

public class PPdraggableListStyle extends DraggableListStyle {
    public PPdraggableListStyle() {
        dividerUp = skin.getDrawable("draggable-list-divider-10");
        dividerOver = skin.getDrawable("draggable-list-divider-over-10");
        transparencyOnDrag = .25f;
    }
}
