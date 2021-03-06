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

package org.netbeans.performance.enterprise.actions;

import org.netbeans.modules.performance.guitracker.ActionTracker;
import org.netbeans.modules.performance.utilities.PerformanceTestCase;
import org.netbeans.performance.enterprise.EPUtilities;
import org.netbeans.performance.enterprise.setup.EnterpriseSetup;
import org.netbeans.performance.enterprise.XMLSchemaComponentOperator;

import org.netbeans.jellytools.nodes.Node;
import org.netbeans.jellytools.actions.OpenAction;
import org.netbeans.jemmy.operators.ComponentOperator;
import org.netbeans.junit.NbTestSuite;
import org.netbeans.junit.NbModuleSuite;

/**
 *
 * @author rashid@netbeans.org, mmirilovic@netbeans.org
 */
public class SwitchToDesignViewTest  extends PerformanceTestCase {
    
    XMLSchemaComponentOperator schemaComponentOperator;
    private static String testSchemaFileName = "OTA_TravelItinerary.xsd";
    
    /** Creates a new instance of SwitchSchemaView */
    public SwitchToDesignViewTest(String testName) {
        super(testName);
        expectedTime = WINDOW_OPEN;
    }
    
    /** Creates a new instance of SwitchSchemaView */
    public SwitchToDesignViewTest(String testName, String performanceDataName) {
        super(testName,performanceDataName);
        expectedTime = WINDOW_OPEN;
    }

    public void testSwitchToDesignView() {
        doMeasurement();
    }

    public static NbTestSuite suite() {
        NbTestSuite suite = new NbTestSuite();
        suite.addTest(NbModuleSuite.create(NbModuleSuite.createConfiguration(EnterpriseSetup.class)
             .addTest(SwitchToDesignViewTest.class)
             .enableModules(".*").clusters(".*")));
        return suite;
    }

    @Override
    protected void initialize() {
        Node doc = new Node(new EPUtilities().getProcessFilesNode("TravelReservationService"), testSchemaFileName);
        doc.select();
        new OpenAction().perform(doc);
        schemaComponentOperator = XMLSchemaComponentOperator.findXMLSchemaComponentOperator(testSchemaFileName);
        schemaComponentOperator.getSchemaButton().push();        
        track_mouse_event = ActionTracker.TRACK_MOUSE_PRESS;        
    }
        
    public void prepare() {
    }
    
    public ComponentOperator open() {
        schemaComponentOperator.getDesignButton().pushNoBlock();
        return schemaComponentOperator;
    }
    
    @Override
    public void close() {
        if (schemaComponentOperator != null) {
            schemaComponentOperator.getSchemaButton().push();
        }
    }

}
