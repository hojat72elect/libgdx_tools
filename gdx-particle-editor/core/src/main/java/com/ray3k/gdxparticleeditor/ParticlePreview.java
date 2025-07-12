package com.ray3k.gdxparticleeditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.ray3k.gdxparticleeditor.Core.*;
import static com.ray3k.gdxparticleeditor.PreviewSettings.*;

/**
 * This class renders the particle preview.
 */
public class ParticlePreview {
    private static final Vector2 temp = new Vector2();
    public static boolean pause;

    public void render() {
        var isDrawing = spriteBatch.isDrawing();
        if (!isDrawing) {
            spriteBatch.setProjectionMatrix(previewViewport.getCamera().combined);
            spriteBatch.begin();
        }
        spriteBatch.setColor(Color.WHITE);

        //calculate world coordinates
        temp.set(previewViewport.getScreenX(), Gdx.graphics.getHeight() - previewViewport.getScreenY());
        previewViewport.unproject(temp);
        float left = temp.x;
        float bottom = temp.y;

        temp.set(previewViewport.getScreenX() + previewViewport.getScreenWidth(), Gdx.graphics.getHeight() - (previewViewport.getScreenY() + previewViewport.getScreenHeight()));
        previewViewport.unproject(temp);
        float right = temp.x;
        float top = temp.y;

        //draw background color
        shapeDrawer.setColor(getBackgroundColor());
        shapeDrawer.filledRectangle(left, bottom, right - left, top - bottom);

        //draw preview
        if (previewImageTexture != null) spriteBatch.draw(previewImageTexture, previewImageX, previewImageY, previewImageWidth, previewImageHeight);

        if (isGridEnabled()) {
            //draw major grid
            shapeDrawer.setColor(getGridColor());
            shapeDrawer.setDefaultLineWidth(2 * previewViewport.getUnitsPerPixel());
            for (float x = left - left % getGridMajorGridlines(); x < right; x += getGridMajorGridlines()) {
                shapeDrawer.line(x, bottom, x, top);
            }
            for (float y = bottom - bottom % getGridMajorGridlines(); y < top; y += getGridMajorGridlines()) {
                shapeDrawer.line(left, y, right, y);
            }

            //draw minor grid
            shapeDrawer.setColor(getGridColor());
            shapeDrawer.setDefaultLineWidth(1 * previewViewport.getUnitsPerPixel());
            for (float x = left - left % getGridMinorGridlines(); x < right; x += getGridMinorGridlines()) {
                shapeDrawer.line(x, bottom, x, top);
            }
            for (float y = bottom - bottom % getGridMinorGridlines(); y < top; y += getGridMinorGridlines()) {
                shapeDrawer.line(left, y, right, y);
            }
        }

        if (isAxesEnabled()) {
            //draw axes
            shapeDrawer.setColor(getAxesColor());
            shapeDrawer.setDefaultLineWidth(3 * previewViewport.getUnitsPerPixel());
            if (bottom <= 0) shapeDrawer.line(left, 0, right, 0);
            if (left <= 0) shapeDrawer.line(0, bottom, 0, top);
        }

        if (!pause) {
            particleEffect.update(Gdx.graphics.getDeltaTime() * getDeltaMultiplier());
            particleEffect.draw(spriteBatch);
        }

        if (!isDrawing) spriteBatch.end();
    }
}
