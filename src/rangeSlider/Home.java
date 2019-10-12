package rangeSlider;

public class Home {

	int roomsNb; // number of rooms
	int price; // price
	int x; // coordinate on x
	int y; // coordinate on y
	int id; // id

	public Home(int x, int y, int roomsNb, int price) {
		this.x = x;
		this.y = y;
		this.roomsNb = roomsNb;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public int getRooms() {
		return roomsNb;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
