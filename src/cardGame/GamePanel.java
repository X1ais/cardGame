package cardGame;

import java.awt.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	int xOrigin, yOrigin, radius = 100, ballRadius = 20;
	double currentAngle;
	Thread gameThread;
	
	int FPS = 60;


	public GamePanel(int xOrigin, int yOrigin, double initialAngle) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;
		this.currentAngle = initialAngle;
		
		this.setPreferredSize(new Dimension(400,400));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(false);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 16.66 milliseconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) { 	
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
			
		}
	}
	
	public void update() {
		currentAngle += 2;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw Ball Path
		g.setColor(Color.WHITE);
		g.drawOval(xOrigin - radius, yOrigin - radius, radius * 2, radius * 2);
		
		
		int ballOriginX, ballOriginY;
		ballOriginX = (int) (radius * Math.cos(currentAngle * Math.PI / 180));
		ballOriginY = (int) (radius * Math.sin(currentAngle * Math.PI / 180));
		
		// Draw Ball
		g.setColor(Color.BLUE);
		g.fillOval(xOrigin + ballOriginX - ballRadius, yOrigin + ballOriginY - ballRadius, ballRadius * 2, ballRadius * 2);
		
		g.dispose();
	}
	
	
}
