package agh.ics.darvin.gui;

import javafx.scene.control.TextField;

public class NumberText extends TextField {  // mało wymowna nazwa
    public NumberText(int initial_value) {
        super(String.format("%d", initial_value));
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text))
            super.replaceText(start, end, text);
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text))
            super.replaceSelection(text);
    }

    private boolean validate(String text) {
        return text.matches("[0-9]*");
    }

    public int getInt() {
        try {
            return Integer.parseInt(this.getText());
        } catch (NumberFormatException ex) {
            return 0; // Box was empty
        }
    }
}
