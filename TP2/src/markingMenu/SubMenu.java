package markingMenu;

public class SubMenu extends Menu {

	public Menu[] menus;
	
	public SubMenu(String name, Menu[] menus) {
		super(name);
		this.menus = menus;
	}
}
