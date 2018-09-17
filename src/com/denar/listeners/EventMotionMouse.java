package com.denar.listeners;

import com.denar.main.Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventMotionMouse extends MouseAdapter {

	Main main;
	
	public EventMotionMouse(Main main) {
		this.main = main;
	}
	
	public void mouseEntered(MouseEvent me) {
		
	}
	
}
