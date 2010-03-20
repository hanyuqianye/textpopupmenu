package org.casaburo.utils.textPopupMenu;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.text.JTextComponent;

abstract class BasicAction extends AbstractAction {

    JTextComponent comp;
    
    public BasicAction(String text, Icon icon) {
        super(text, icon);
        putValue(Action.SHORT_DESCRIPTION, text);
    }

    public void setTextComponent(JTextComponent comp) {
       this.comp = comp;


}}