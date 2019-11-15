package paint;

import markingMenu.Menu;
import markingMenu.SubMenu;
import markingMenu.Tool;

public class MenuController {

	MenuModel model;
	MenuView view;
	
	public MenuController(MenuModel model, MenuView view) {
		this.model = model;
		this.view = view;
	}

	public void select(int menuIndex) {
		Menu menu = model.currentMenu[menuIndex];
		if(menu instanceof Tool) {
			Tool tool = (Tool) menu;
			tool.exec();
			view.finish();
		} else if(menu instanceof SubMenu){
			SubMenu item = (SubMenu) menu;
			model.setCurrentMenu(item.menus);
			view.setCenter(view.p);
		}
	}
}
