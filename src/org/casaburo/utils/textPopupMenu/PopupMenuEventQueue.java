// <editor-fold defaultstate="collapsed" desc="license">
/*
 *  Copyright 2010 Rocco Casaburo.
 *  mail address: rcp.nbm.casaburo at gmail.com
 *  Visit projects homepage at http://sites.google.com/site/nbmprojects
 *
 *  Licensed under the GNU General Public License, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *       http://www.gnu.org/licenses/gpl-3.0.html
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
// </editor-fold>
package org.casaburo.utils.textPopupMenu;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.text.*;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

/**
 * how to use: it is enabled adding the following code row into your own code:
 * Toolkit.getDefaultToolkit().getSystemEventQueue().push( new PopupMenuEventQueue());
 *<p>
 *Utilizzo: viene abilitato in automatico aggiungendo la seguente riga di codice:
 *Toolkit.getDefaultToolkit().getSystemEventQueue().push(new PopupMenuEventQueue());
 *
 */
class PopupMenuEventQueue extends EventQueue {
    

    public JPopupMenu popup;
    // JTable table;
    public BasicAction cut, copy, paste, selectAll;
    private final String cutString;
    private final String CUT_ICON_PATH;
    private final ImageIcon cutIcon;
    private final String copyString;
    private final String COPY_ICON_PATH;
    private final ImageIcon copyIcon;
    private final String pasteString;
    private final String PASTE_ICON_PATH;
    private final ImageIcon pasteIcon;
    private final ResourceBundle bundle;

    PopupMenuEventQueue() {
        //createPopupMenu();
        cutString = NbBundle.getMessage(org.openide.actions.CutAction.class, "Cut").replace("&", "");
        CUT_ICON_PATH = "org/openide/resources/actions/cut.gif";
        cutIcon = (ImageUtilities.loadImageIcon(CUT_ICON_PATH, true));
        
        copyString = NbBundle.getMessage(org.openide.actions.CopyAction.class, "Copy").replace("&", "");
        COPY_ICON_PATH = "org/openide/resources/actions/copy.gif";
        copyIcon = (ImageUtilities.loadImageIcon(COPY_ICON_PATH, true));

        pasteString = NbBundle.getMessage(org.openide.actions.PasteAction.class, "Paste").replace("&", "");
        PASTE_ICON_PATH = "org/openide/resources/actions/paste.gif";
        pasteIcon = (ImageUtilities.loadImageIcon(PASTE_ICON_PATH, true));

        bundle = ResourceBundle.getBundle("org/casaburo/utils/textPopupMenu/resources/PopUpBundle");

    }

    public void createPopupMenu(JTextComponent text) {

        //TODO: completare con l'internazionalizzazione del comando Select All

        cut = new PopCutAction(cutString, cutIcon);//(messages.getString("CutString"),null);
        copy = new PopCopyAction(copyString, copyIcon);//(messages.getString("CopyString"), null);
        paste = new PopPasteAction(pasteString, pasteIcon);//(messages.getString("PasteString"),null);
        selectAll = new PopSelectAllAction(bundle.getString("SelectAllString"), null);
        cut.setTextComponent(text);
        copy.setTextComponent(text);
        paste.setTextComponent(text);
        selectAll.setTextComponent(text);
        
        popup = new JPopupMenu();
        popup.add(cut);
        popup.add(copy);
        popup.add(paste);
        popup.addSeparator();
        popup.add(selectAll);
    }

    public void showPopup(Component parent, MouseEvent mouseEvt) {
        popup.validate();
        popup.show(parent, mouseEvt.getX(), mouseEvt.getY());
    }

    //TODO: rendere indipendente dalla piattaforma le shortcuts dei comandi
    @Override
    protected void dispatchEvent(AWTEvent event) {
        super.dispatchEvent(event);
        if (!(event instanceof MouseEvent)) {
            return;
        }
        MouseEvent me = (MouseEvent) event;
        if (!me.isPopupTrigger()) {
            return;
        }
        if (!(me.getSource() instanceof Component)) {
            return;
        }
        Component comp = SwingUtilities.getDeepestComponentAt((Component) me.getSource(), me.getX(), me.getY());
        if (!(comp instanceof JTextComponent)) {
            return;
        }
        if (MenuSelectionManager.defaultManager().getSelectedPath().length > 0) {
            return;
        }
        comp.requestFocus(); //Thanks to wsc719@yahoo.com.cn for this contribution
        createPopupMenu((JTextComponent) comp);
        showPopup((Component) me.getSource(), me);
    }
}


