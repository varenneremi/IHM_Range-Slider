package paint;

import markingMenu.Menu;
import markingMenu.SubMenu;
import markingMenu.Tool;

public class MenuController {

	MenuModel model;
	
	public MenuController(MenuModel model) {
		this.model = model;
	}

	public void select(int menuIndex) {
		Menu menu = model.currentMenu[menuIndex];
		if(menu instanceof Tool) {
			Tool tool = (Tool) menu;
			tool.exec();
		} else if(menu instanceof SubMenu){
			SubMenu item = (SubMenu) menu;
			model.setCurrentMenu(item.menus);
		}
	}
}
