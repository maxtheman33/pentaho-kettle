/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2014 by Pentaho : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.di.job.entry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;
import org.pentaho.di.job.entries.special.JobEntrySpecial;

public class JobEntryCopyTest {

  private JobEntryCopy originJobEntry;
  private JobEntryCopy copyJobEntry;
  private JobEntryInterface originEntry;

  @Before
  public void setUp() throws Exception {
    originJobEntry = new JobEntryCopy();
    copyJobEntry = new JobEntryCopy();

    originEntry = new JobEntrySpecial( "EntrySpecial", false, false );
    originEntry.setChanged( false );

    originJobEntry.setEntry( originEntry );
  }

  @Test
  public void testReplaceMetaCloneEntryOfOrigin() throws Exception {

    copyJobEntry.replaceMeta( originJobEntry );
    assertNotSame( "Entry of origin and copy JobEntry should be different objects: ", copyJobEntry.getEntry(),
        originJobEntry.getEntry() );
  }

  @Test
  public void testReplaceMetaDoesNotChangeEntryOfOrigin() throws Exception {

    copyJobEntry.replaceMeta( originJobEntry );
    assertEquals( "hasChanged in Entry of origin JobEntry should not be changed. ", false, originJobEntry.getEntry()
        .hasChanged() );
  }

  @Test
  public void testReplaceMetaChangesEntryOfCopy() throws Exception {

    copyJobEntry.replaceMeta( originJobEntry );
    assertEquals( "hasChanged in Entry of copy JobEntry should be changed. ", true, copyJobEntry.getEntry()
        .hasChanged() );
  }

}
