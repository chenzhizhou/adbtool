package mainbody;

/*
import java.io.FileInputStream;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
*/

public class JmeterTest {
//	public static void main(String[] argv) throws Exception {
//		StandardJMeterEngine jmeter = new StandardJMeterEngine();
//		JMeterUtils.loadJMeterProperties("./jmeter/bin/jmeter.properties");
//        JMeterUtils.setJMeterHome("./jmeter");
//        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
//        JMeterUtils.initLocale();
//     // Initialize JMeter SaveService
//        SaveService.loadProperties();
//
//        // Load existing .jmx Test Plan
//        FileInputStream in = new FileInputStream("./jmeter/SimulatorInit20_userDefine.jmx");
//        HashTree testPlanTree = SaveService.loadTree(in);
//        in.close();
//        // Run JMeter Test
//        jmeter.configure(testPlanTree);
//        jmeter.run();
//	}
	
	
	
	
	
	/*
	public static void runScript(String scriptPath)throws Exception{
		StandardJMeterEngine jmeter = new StandardJMeterEngine();
		JMeterUtils.loadJMeterProperties("./jmeter/bin/jmeter.properties");
        JMeterUtils.setJMeterHome("./jmeter");
        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
        JMeterUtils.initLocale();
     // Initialize JMeter SaveService
        SaveService.loadProperties();

        // Load existing .jmx Test Plan
        FileInputStream in = new FileInputStream(scriptPath);
        HashTree testPlanTree = SaveService.loadTree(in);
        in.close();
        // Run JMeter Test
        jmeter.configure(testPlanTree);
        jmeter.run();
		
	}
	*/
}
