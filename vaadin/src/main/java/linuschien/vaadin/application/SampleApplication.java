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

import linuschien.vaadin.application.component.MasterDetailFormComponent;

import com.vaadin.Application;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class SampleApplication extends Application {
	private Window main = new Window("Demo");
	
	private MasterDetailFormComponent component = new MasterDetailFormComponent();

	@Override
	public void init() {
		setMainWindow(main);
		main.addComponent(component);
		setTheme("mytheme");
	}
}
