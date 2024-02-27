package cardGame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Card {
	// Still need to add Attack Effects (i.e. Before, Hit, After, etc), Boosts

	private int ownerId;
	private BufferedImage cardImage = null;
	private String name;
	private int cost;
	private int minRange;
	private int maxRange;
	private int power;
	private int speed;
	private int armor;
	private int guard;
	private boolean cancelable;
	
	
	public Card() {}
	
	public Card(int ownerId,String name, int cost, int minRange, int maxRange, int power, int speed, int armor, int guard, boolean cancelable) {
		this.ownerId = ownerId;
		this.name = name;
		this.cost = cost;
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.power = power;
		this.speed = speed;
		this.armor = armor;
		this.guard = guard;
		this.cancelable = cancelable;
		InputStream is = getClass().getResourceAsStream("/cards/" + name + ".png");
		try {
			cardImage = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void describe() {
		System.out.println(name.toUpperCase());
		if (minRange == maxRange) { System.out.println("Range: " + minRange);
		} else { System.out.println("Range: " + minRange + "—" + maxRange); }
		System.out.println("Power: " + power);
		System.out.println("Speed: " + speed);
		if (armor != 0) {System.out.println("Armor: " + armor);}
		if (guard != 0) {System.out.println("Guard: " + guard);}
	}
	
	public String toString() {
		return name.toUpperCase();
	}
	
	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getMinRange() {
		return minRange;
	}

	public void setMinRange(int minRange) {
		this.minRange = minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}
	
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getGuard() {
		return guard;
	}

	public void setGuard(int guard) {
		this.guard = guard;
	}

	public boolean isCancellable() {
		return cancelable;
	}

	public void setCancellable(boolean cancellable) {
		this.cancelable = cancellable;
	}

	public Image getCardImage() {
		return cardImage;
	}
	
}
