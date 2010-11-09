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

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.KeyStroke;

class PopPasteAction extends BasicAction {

    public PopPasteAction(String text, Icon icon) {
        super(text, icon);
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl V"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        comp.paste();
    }

    @Override
    public boolean isEnabled() {
        Transferable content = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        return comp != null && comp.isEnabled() && comp.isEditable() && content.isDataFlavorSupported(DataFlavor.stringFlavor);
    }
}
