/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package threaddemo.views.looktree;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import org.netbeans.spi.looks.LookSelector;

/**
 * A raw tree view of objects and looks.
 * @author Jesse Glick
 */
public class LookTreeView extends JTree {

    public LookTreeView(Object o, LookSelector s) {
        super(new LookTreeModel(o, s));
        setLargeModel(true);
        LookTreeCellRenderer r = new LookTreeCellRenderer();
        setCellRenderer(r);
        setCellEditor(new LookTreeCellEditor(this, r));
        setEditable(true);
        setShowsRootHandles(true);
        setRowHeight( 20 );
        addTreeExpansionListener(new TreeCollector());
        addMouseListener(new PopupHandler(this));
    }
    
    private LookTreeModel getLookTreeModel() {
        return (LookTreeModel)getModel();
    }
    
    public void addNotify() {
        getLookTreeModel().addNotify();
        super.addNotify();
    }
    
    public void removeNotify() {
        super.removeNotify();
        getLookTreeModel().removeNotify();
    }
    
    private static final class TreeCollector implements TreeExpansionListener {
        
        public void treeCollapsed(TreeExpansionEvent event) {
            LookTreeNode n = (LookTreeNode)event.getPath().getLastPathComponent();
            n.forgetChildren();
        }
        
        public void treeExpanded(TreeExpansionEvent event) {
            // ignored
        }
        
    }
    
}
