package markingMenu;

public class MarkingMenu implements Menu{

	public String name;
	public Menu[] menus;
	
	public MarkingMenu(String name, Menu[] menus) {
		this.name = name;
		this.menus = menus;
	}
}
