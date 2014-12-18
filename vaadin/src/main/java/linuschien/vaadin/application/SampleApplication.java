/* 
 * Copyright 2009 IT Mill Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package linuschien.vaadin.application;

import linuschien.vaadin.application.model.User;

import com.vaadin.Application;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * Demonstration application that shows how to use a simple custom client-side
 * GWT component, the ColorPicker.
 */
@SuppressWarnings("serial")
public class SampleApplication extends Application {
	Window main = new Window("Demo");

	/* Another component. */
	Label newUser;

	BeanItem<User> item = new BeanItem<User>(new User("", "", ""));

	TextField name = new TextField("名稱", item.getItemProperty("name"));
	TextField email = new TextField("信箱", item.getItemProperty("email"));
	TextField phone = new TextField("電話", item.getItemProperty("phone"));

	@Override
	public void init() {
		setMainWindow(main);

		// Listen for value change events in the custom component,
		// triggered when user clicks a button to select another color.

		// Add another component to give feedback from server-side code
		newUser = new Label("新增使用者：");
		main.addComponent(newUser);
		main.addComponent(name);
		main.addComponent(email);
		main.addComponent(phone);

		final Table table = new Table();
		final BeanItemContainer<User> itemContainer = new BeanItemContainer<User>(User.class);
		table.setContainerDataSource(itemContainer);

		// Server-side manipulation of the component state
		final Button button = new Button("新增");
		button.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				itemContainer.addBean(item.getBean().clone());
				table.setPageLength(itemContainer.size());
			}
		});
		main.addComponent(button);

		table.addGeneratedColumn("Delete", new ColumnGenerator() {
			@Override
			public Object generateCell(final Table source, final Object itemId, Object columnId) {
				Button button = new Button("del");
				button.addListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						source.getContainerDataSource().removeItem(itemId);
						table.setPageLength(itemContainer.size());
					}
				});
				return button;
			}
		});

		table.setVisibleColumns(new Object[] { "Delete", "name", "email", "phone" });
		table.setColumnHeaders(new String[] { "刪除", "名稱", "信箱", "電話" });
		table.setSortDisabled(true);
		table.setPageLength(itemContainer.size());
		
		main.addComponent(table);

		setTheme("mytheme");
	}
}
