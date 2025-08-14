package erstesProjekt;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class KonfettiPanel extends JPanel {
    private final Random random = new Random();

    public KonfettiPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 150; i++) {
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            int size = random.nextInt(10) + 5;
            g.fillOval(x, y, size, size);
        }
    }
}

