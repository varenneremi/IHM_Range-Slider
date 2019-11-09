package markingMenu;

public abstract class Tool extends Menu {

	public Tool(String name) {
		super(name);
	}

	public abstract void exec();
}
