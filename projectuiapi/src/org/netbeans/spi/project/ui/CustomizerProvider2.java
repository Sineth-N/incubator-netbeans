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

package org.netbeans.spi.project.ui;

/**
 * CustomizerProvider enhanced with ability to open customizer on given
 * category or subcategory.
 * (Moved here from org.netbeans.modules.java.api.common, where it is deprecated)
 * 
 * @since 1.71
 */
public interface CustomizerProvider2 extends CustomizerProvider {

    /**
     * Show customizer and preselect a category or subcategory.
     * @param preselectedCategory <b>category or subcategory</b> to be selected
     * @param preselectedSubCategory <b>not used</b>
     */
    void showCustomizer(String preselectedCategory, String preselectedSubCategory);

}
