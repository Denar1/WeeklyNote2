package com.denar.listeners;

import com.denar.main.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventMouse extends MouseAdapter {
	
	Main main;
	
	public EventMouse(Main main) {
		this.main = main;
	}

	public void mouseClicked(MouseEvent me) {
		
		
	}
	
	class ActionEvents implements ActionListener {

		public ActionEvents(Main main) {
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			
		}
		
	}
	
}
