package lab08.ex3_marco;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sky extends JFrame {
    private final List<Star> stars = new ArrayList<>();

    public void placeStar(Star star) {
        stars.add(star);
    }

    @Override
    public void paint(Graphics graphics) {
        for (Star star : stars) {
            star.draw(graphics);
        }
    }
}