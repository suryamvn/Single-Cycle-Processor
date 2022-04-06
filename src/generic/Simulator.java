package generic;

import processor.Clock;
import processor.Processor;
import processor.memorysystem.MainMemory;
import generic.Statistics;
import java.io.*;
import java.util.Scanner;

public class Simulator {
		
	static Processor processor;
	static boolean simulationComplete;
	
	public static void setupSimulation(String assemblyProgramFile, Processor p)
	{
		Simulator.processor = p;
		loadProgram(assemblyProgramFile);
		
		simulationComplete = false;
	}
	
	static void loadProgram(String assemblyProgramFile)
	{
		
		/*
		 * TODO
		 * 1. load the program into memory according to the program layout described
		 *    in the ISA specification
		 * 2. set PC to the address of the first instruction in the main
		 * 3. set the following registers:
		 *     x0 = 0
		 *     x1 = 65535
		 *     x2 = 65535
		 */
		 	MainMemory memory = processor.getMainMemory();
			/* 1. load the program into memory according to the program layout described
		                  in the ISA specification  */
		 	int i = 0;
		 	try
		 	{
			 	FileInputStream input = new FileInputStream(assemblyProgramFile);
				DataInputStream data = new DataInputStream(input);
				
				//address of the first instruction in the main
				int main = data.readInt();
				while(data.available() > 0)
				{
					memory.setWord(i, data.readInt());
					i++;
				}
				data.close();
				processor.setMainMemory(memory);
				
				/* 2. set PC to the address of the first instruction in the main*/
				processor.getRegisterFile().setProgramCounter(main);
				
				
				/*3. set the following registers:
			 	*     x0 = 0
			 	*     x1 = 65535
			 	*     x2 = 65535
			 	*/
				processor.getRegisterFile().setValue(0, 0);
				processor.getRegisterFile().setValue(1, 65535);
				processor.getRegisterFile().setValue(2, 65535);
			}
			catch(FileNotFoundException fe)
			{ 
				System.out.println("FileNotFoundException : " + fe);
			}
			catch(IOException ioe)
			{
				System.out.println("IOException : " + ioe);
			}
			
	}
	
	public static void simulate()
	{
		while(simulationComplete == false)
		{
			processor.getIFUnit().performIF();
			Clock.incrementClock();
			processor.getOFUnit().performOF();
			Clock.incrementClock();
			processor.getEXUnit().performEX();
			Clock.incrementClock();
			processor.getMAUnit().performMA();
			Clock.incrementClock();
			processor.getRWUnit().performRW();
			Clock.incrementClock();
		}
		
		// TODO
		// set statistics
	}
	
	public static void setSimulationComplete(boolean value)
	{
		simulationComplete = value;
	}
}
