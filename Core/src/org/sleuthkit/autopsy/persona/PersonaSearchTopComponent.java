/*
 * Autopsy Forensic Browser
 *
 * Copyright 2020 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.persona;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.Mode;
import org.openide.windows.RetainLocation;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.centralrepository.datamodel.CentralRepoException;
import org.sleuthkit.autopsy.centralrepository.datamodel.Persona;

/**
 * Top component for the Personas tool
 *
 */
@TopComponent.Description(preferredID = "PersonasTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "personasearch", openAtStartup = false)
@RetainLocation("personasearch")
@SuppressWarnings("PMD.SingularField")
public final class PersonaSearchTopComponent extends TopComponent {

    @Messages({
        "PTopComponent_Name=Persona Search"
    })
    public PersonaSearchTopComponent() {
        initComponents();
        setName(Bundle.PTopComponent_Name());
        executeSearch();

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeSearch();
            }
        });
    }

    class PersonaFilterTableModel extends DefaultTableModel {

        PersonaFilterTableModel(Object[][] rows, String[] colNames) {
            super(rows, colNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    void updateResultsTable(Collection<Persona> results) {
        Object[][] rows = new Object[results.size()][2];
        int i = 0;
        for (Persona result : results) {
            rows[i] = new String[]{String.valueOf(result.getId()), result.getName()};
            i++;
        }
        DefaultTableModel updatedTableModel = new PersonaFilterTableModel(
                rows,
                new String[]{"ID", "Name"}
        );

        filterResultsTable.setModel(updatedTableModel);
        
        // Formatting
        filterResultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        filterResultsTable.getColumnModel().getColumn(0).setMaxWidth(100);
        filterResultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    void executeSearch() {
        Collection<Persona> results = Collections.EMPTY_LIST;
        try {
            results = Persona.getPersonaByName(searchField.getText());
        } catch (CentralRepoException ex) {
            Exceptions.printStackTrace(ex);
        }
        updateResultsTable(results);
    }

    @Override
    public void componentOpened() {
        super.componentOpened();
        WindowManager.getDefault().setTopComponentFloating(this, true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchButtonGroup = new javax.swing.ButtonGroup();
        searchPanel = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        searchNameRadio = new javax.swing.JRadioButton();
        searchAccountRadio = new javax.swing.JRadioButton();
        filterResultsPane = new javax.swing.JScrollPane();
        filterResultsTable = new javax.swing.JTable();
        searchBtn = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(400, 400));

        searchField.setText(org.openide.util.NbBundle.getMessage(PersonaSearchTopComponent.class, "PersonaSearchTopComponent.searchField.text")); // NOI18N

        searchButtonGroup.add(searchNameRadio);
        searchNameRadio.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(searchNameRadio, org.openide.util.NbBundle.getMessage(PersonaSearchTopComponent.class, "PersonaSearchTopComponent.searchNameRadio.text")); // NOI18N
        searchNameRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchNameRadioActionPerformed(evt);
            }
        });

        searchButtonGroup.add(searchAccountRadio);
        org.openide.awt.Mnemonics.setLocalizedText(searchAccountRadio, org.openide.util.NbBundle.getMessage(PersonaSearchTopComponent.class, "PersonaSearchTopComponent.searchAccountRadio.text")); // NOI18N

        filterResultsTable.setToolTipText(org.openide.util.NbBundle.getMessage(PersonaSearchTopComponent.class, "PersonaSearchTopComponent.filterResultsTable.toolTipText")); // NOI18N
        filterResultsTable.getTableHeader().setReorderingAllowed(false);
        filterResultsPane.setViewportView(filterResultsTable);
        if (filterResultsTable.getColumnModel().getColumnCount() > 0) {
            filterResultsTable.getColumnModel().getColumn(0).setMaxWidth(25);
            filterResultsTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(PersonaSearchTopComponent.class, "PersonaSearchTopComponent.filterResultsTable.columnModel.title0")); // NOI18N
            filterResultsTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(PersonaSearchTopComponent.class, "PersonaSearchTopComponent.filterResultsTable.columnModel.title1")); // NOI18N
        }

        org.openide.awt.Mnemonics.setLocalizedText(searchBtn, org.openide.util.NbBundle.getMessage(PersonaSearchTopComponent.class, "PersonaSearchTopComponent.searchBtn.text")); // NOI18N

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterResultsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(searchField)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(searchNameRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchAccountRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                        .addComponent(searchBtn)))
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchNameRadio)
                    .addComponent(searchAccountRadio)
                    .addComponent(searchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterResultsPane, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchNameRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchNameRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchNameRadioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane filterResultsPane;
    private javax.swing.JTable filterResultsTable;
    private javax.swing.JRadioButton searchAccountRadio;
    private javax.swing.JButton searchBtn;
    private javax.swing.ButtonGroup searchButtonGroup;
    private javax.swing.JTextField searchField;
    private javax.swing.JRadioButton searchNameRadio;
    private javax.swing.JPanel searchPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public List<Mode> availableModes(List<Mode> modes) {
        /*
         * This looks like the right thing to do, but online discussions seems
         * to indicate this method is effectively deprecated. A break point
         * placed here was never hit.
         */
        return modes.stream().filter(mode -> mode.getName().equals("personasearch"))
                .collect(Collectors.toList());
    }
}
