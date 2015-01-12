import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class CircleButton extends JButton {
Shape shape;
Color bgColor = SystemColor.control;

public CircleButton() {
    this("circleButton", null);
}

public CircleButton(String label) {
    this(label, null);
}

public CircleButton(String label, Color bgColor) {
    super(label); 
    if (bgColor != null) {
      this.bgColor = bgColor;
    }
    Dimension size = this.getPreferredSize();
    size.width = size.height = Math.max(size.width, size.height);
    this.setPreferredSize(size);
    this.setContentAreaFilled(false); 
    this.setBorderPainted(false);
    this.setFocusPainted(false);
}

protected void paintComponent(Graphics g) {
    if (this.getModel().isArmed()) {
      g.setColor(java.awt.SystemColor.controlHighlight);
    } else {
      g.setColor(java.awt.SystemColor.controlShadow);
      g.setColor(this.bgColor);
    }
    g.fillOval(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    g.setColor(java.awt.SystemColor.controlShadow);
    g.drawOval(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    super.paintComponent(g);
}

public boolean contains(int x, int y) {
    if ((shape == null) || (!shape.getBounds().equals(this.getBounds()))) {
      this.shape = new Ellipse2D.Float(0, 0, this.getWidth(), this
          .getHeight());
    }
    return shape.contains(x, y);
}
}
