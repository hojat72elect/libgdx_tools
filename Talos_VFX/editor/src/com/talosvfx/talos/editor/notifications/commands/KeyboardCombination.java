package com.talosvfx.talos.editor.notifications.commands;

import com.badlogic.gdx.Input;
import lombok.Getter;

public class KeyboardCombination extends AbstractCombinationWithModifier {

    @Getter
    private int regularKey;

    @Getter
    private boolean repeat;

    private float repeatStartTime = 0.5f;
    private float repeatTime = 0.1f;
    private float repeatCooldown = this.repeatStartTime;
    private boolean timerCooledDown = false;
    private boolean firstTimeDone = false;

    private boolean isRegularKeyPressed = false;

    public KeyboardCombination(int regularKey, boolean repeat, ModifierKey... modifierKeys) {
        super(modifierKeys);
        this.regularKey = regularKey;
        this.repeat = repeat;
    }

    @Override
    public KeyboardCombination copy() {
        KeyboardCombination keyboardCombination = new KeyboardCombination(regularKey, repeat);
        for (ModifierKey item : modifierKeys) {
            keyboardCombination.modifierKeys.add(item);
        }
        return keyboardCombination;
    }

    @Override
    public void resetState() {
    }

    @Override
    public void commandIsRun() {
        firstTimeDone = true;
    }

    @Override
    public CombinationType getCombinationType() {
        return CombinationType.KEYBOARD;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (repeat) {
            if (areAllButtonsPressed()) {
                repeatCooldown -= delta;
                timerCooledDown = false;
                if (repeatCooldown <= 0) {
                    repeatCooldown = repeatTime;
                    timerCooledDown = true;
                }
            }
        }
    }

    @Override
    public boolean shouldExecute() {
        boolean areAllButtonsPressed = areAllButtonsPressed();
        if (repeat) {
            if (!areAllButtonsPressed) {
                return false;
            }
            if (!firstTimeDone) {
                return true;
            }
            return timerCooledDown;
        } else {
            return areAllButtonsPressed && !firstTimeDone;
        }
    }

    private boolean areAllButtonsPressed () {
        return isRegularKeyPressed && super.shouldExecute();
    }

    @Override
    public void keyDown(int keycode) {
        super.keyDown(keycode);
        if (ModifierKey.getModifierFromKey(keycode) == null) {
            isRegularKeyPressed = false;
        }

        if (keycode == regularKey) {
            isRegularKeyPressed = true;
        }
    }

    @Override
    public void keyUp(int keycode) {
        super.keyUp(keycode);
        if (keycode == regularKey) {
            isRegularKeyPressed = false;
        }

        if (!areAllButtonsPressed()) {
            firstTimeDone = false;
            timerCooledDown = false;
            repeatCooldown = repeatStartTime;
        }
    }

    @Override
    public String toString() {
        String superString = super.toString();
        return superString + Input.Keys.toString(regularKey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyboardCombination that = (KeyboardCombination) o;

        if (super.equals(that)) {
            return false;
        }

        if (regularKey != that.regularKey) return false;
        return repeat == that.repeat;
    }

    @Override
    public int hashCode() {
        int result = regularKey;
        result = 31 * result + (repeat ? 1 : 0);
        return result;
    }
}
