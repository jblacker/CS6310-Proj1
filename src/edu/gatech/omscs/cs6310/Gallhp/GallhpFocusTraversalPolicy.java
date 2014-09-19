package edu.gatech.omscs.cs6310.Gallhp;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

public class GallhpFocusTraversalPolicy extends FocusTraversalPolicy {
	
	private Vector<Component> order;
	private int traversalSize;
	
	public GallhpFocusTraversalPolicy(Vector<Component> focusOrder) {
		this.order = focusOrder;
		this.traversalSize = focusOrder.size();
	}

	@Override
	public Component getComponentAfter(Container root, Component component) {
		int index = (order.indexOf(component) + 1) % traversalSize;
		return order.get(index);
	}

	@Override
	public Component getComponentBefore(Container root, Component component) {
		int index = (order.indexOf(component));
		if(index == 0)
			return order.lastElement();
		else
			return order.get(index - 1);
	}

	@Override
	public Component getDefaultComponent(Container root) {
		return this.getFirstComponent(root);
	}

	@Override
	public Component getFirstComponent(Container root) {
		return order.firstElement();
	}

	@Override
	public Component getLastComponent(Container root) {
		return order.lastElement();
	}
}
