package processor.pipeline;

import generic.Simulator;
import processor.Processor;

public class RegisterWrite {
	Processor containingProcessor;
	MA_RW_LatchType MA_RW_Latch;
	IF_EnableLatchType IF_EnableLatch;
	
	public RegisterWrite(Processor containingProcessor, MA_RW_LatchType mA_RW_Latch, IF_EnableLatchType iF_EnableLatch)
	{
		this.containingProcessor = containingProcessor;
		this.MA_RW_Latch = mA_RW_Latch;
		this.IF_EnableLatch = iF_EnableLatch;
	}
	
	public void performRW()
	{
		if(MA_RW_Latch.isRW_enable())
		{
			//TODO
			int load_value=MA_RW_Latch.getloadvalue();
			int ALU_result=MA_RW_Latch.getAluResult();
			Integer opcode1=MA_RW_Latch.getOpCode();
			int opcode= Integer.parseInt(opcode1.toString(),2);
			int op2=MA_RW_Latch.getrd();
			if(opcode1==11101)
			{
				Simulator.setSimulationComplete(true);
			}
			else if(opcode1==10110)
			{
				System.out.println(op2);
				System.out.println("-------------------------------------------");
				System.out.println(load_value);
				containingProcessor.getRegisterFile().setValue(op2, load_value);
			}
			else if(opcode1==10111 || (opcode>>3)==3)
			{
				// nothing to be done
			}
			else
			{
				containingProcessor.getRegisterFile().setValue(op2, ALU_result);
				System.out.println(op2);
				System.out.println("-------------------------------------------");
				System.out.println(ALU_result);
			}
			// if instruction being processed is an end instruction, remember to call Simulator.setSimulationComplete(true);
			
			MA_RW_Latch.setRW_enable(false);
			IF_EnableLatch.setIF_enable(true);
		}
	}

}

