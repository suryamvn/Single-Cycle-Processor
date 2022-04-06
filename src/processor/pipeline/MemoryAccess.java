package processor.pipeline;

import processor.Processor;

public class MemoryAccess {
	Processor containingProcessor;
	EX_MA_LatchType EX_MA_Latch;
	MA_RW_LatchType MA_RW_Latch;
	
	public MemoryAccess(Processor containingProcessor, EX_MA_LatchType eX_MA_Latch, MA_RW_LatchType mA_RW_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.EX_MA_Latch = eX_MA_Latch;
		this.MA_RW_Latch = mA_RW_Latch;
	}
	
	public void performMA()
	{
		//TODO
		int Opcode=EX_MA_Latch.getOpCode();
		//System.out.println(EX_MA_Latch.isMA_enable() + "WX_MA enable");
		if(EX_MA_Latch.isMA_enable())
		{
			Opcode = EX_MA_Latch.getOpCode();
			if(Opcode == 10110)
			{
				MA_RW_Latch.setloadvalue(containingProcessor.getMainMemory().getWord(EX_MA_Latch.getAluResult()));
			} 
			else if(Opcode == 10111)   
			{
				System.out.println("*****************************************");
				System.out.println(EX_MA_Latch.getAluResult());
				System.out.println("*****************************************");
				System.out.println(EX_MA_Latch.getOperand2());
				System.out.println("*****************************************");
				containingProcessor.getMainMemory().setWord(EX_MA_Latch.getOperand2(),EX_MA_Latch.getAluResult());
			}
			//System.out.println("Setting RWenable");
			MA_RW_Latch.setOpCode(Opcode);
			MA_RW_Latch.setOperand2(EX_MA_Latch.getOperand2());
			MA_RW_Latch.setAluResult(EX_MA_Latch.getAluResult());
			MA_RW_Latch.setrd(EX_MA_Latch.getrd());
			EX_MA_Latch.setMA_enable(false);
			MA_RW_Latch.setRW_enable(true);
		}
	}
}
