package test.java;
/**
+ * File Name: SchedPanelTest.java
+ * Tests some main functionality for the SchedPanel
+ * Author: Jesse Sabbath
+ * Date: April 18, 2018
+ * 
+ */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import main.java.memoranda.ui.SchedPanel;
import main.java.memoranda.ui.WorkPanel;

class SchedPanelTest {

    WorkPanel workPanel = new WorkPanel();
    SchedPanel testPanel = new SchedPanel(workPanel);
 
    
    @Test
    public void testForNulls() {
        assertNotNull(workPanel);
        assertNotNull(testPanel);
    }
    

}
