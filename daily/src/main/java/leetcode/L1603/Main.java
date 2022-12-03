package leetcode.L1603;

public class Main {
	public static void main(String[] args) {

	}
}

class ParkingSystem {
	private int big;

	private int medium;

	private int small;

	public ParkingSystem(int big, int medium, int small) {
		this.small = small;
		this.medium = medium;
		this.big = big;
	}

	public boolean addCar(int carType) {
		switch (carType) {
			case 1:
				if (this.big >= 1) {
					this.big -= 1;
					return true;
				}
				break;
			case 2:
				if (this.medium >= 1) {
					this.medium -= 1;
					return true;
				}
				break;
			case 3:
				if (this.small >= 1) {
					this.small -= 1;
					return true;
				}
				break;
		}
		return false;
	}
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */
