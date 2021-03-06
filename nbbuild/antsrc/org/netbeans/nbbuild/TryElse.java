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

package org.netbeans.nbbuild;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * Try calling one target; if that fails, try another.
 * (If the second fails, halt the build.)
 */
public class TryElse extends Task {

    private String first, second;

    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    @Override
    public void execute() throws BuildException {
        // XXX could use CallTarget, but would it add any value?
        // (would insulate caller from properties set in subtarget)
        try {
            getProject().executeTarget(first);
        } catch (BuildException x) {
            log(x.getLocation() + x.getMessage(), Project.MSG_WARN);
            log("Target " + first + " failed, falling back to " + second, Project.MSG_WARN);
            getProject().executeTarget(second);
        }
    }

}
