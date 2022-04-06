package processor.pipeline;

import processor.Processor;

public class Execute {
	Processor containingProcessor;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	
	public Execute(Processor containingProcessor, OF_EX_LatchType oF_EX_Latch, EX_MA_LatchType eX_MA_Latch, EX_IF_LatchType eX_IF_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.OF_EX_Latch = oF_EX_Latch;
		this.EX_MA_Latch = eX_MA_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
	}
	
	public void performEX()
	{
		//TODO
		int A,B,result,value;
		value = OF_EX_Latch.getbranchTarget();
		EX_IF_Latch.setBranchPC(value);
		//Setting branch PC
		//
		//System.out.println("value"+value);
		
		//Set A to OF_EX_Latch.getop1()
		A = OF_EX_Latch.getop1();
		
		//If it is an immediate set B to OF_EX_Latch.getimmx() else set B to OF_EX_Latch.getop2() 
		//It's the operation of a multiplexer
		if((OF_EX_Latch.getopcode() <= 10111 && OF_EX_Latch.getopcode() % 2 == 1) || (OF_EX_Latch.getopcode() == 10110) || (OF_EX_Latch.getopcode()==11000))
		{
			B = OF_EX_Latch.getimmx();
		}
		else
		{
			B = OF_EX_Latch.getop2();
		}
		//Computing result according to opcode recieved from Operand Fetch and set its result to EX_MA_Latch as well
		if(OF_EX_Latch.getopcode() == 0 || OF_EX_Latch.getopcode() == 1)
		{
			result = A+B;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode() == 10 || OF_EX_Latch.getopcode() == 11)
		{
			result = A-B;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode() == 100 || OF_EX_Latch.getopcode() == 101)
		{
			result = A*B;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode() == 110 || OF_EX_Latch.getopcode() == 111)
		{
			containingProcessor.getRegisterFile().setValue(31,A%B);
			result = A/B;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode() == 1000 || OF_EX_Latch.getopcode()==1001)
		{
			result = A&B;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode() == 1010 || OF_EX_Latch.getopcode() == 1011)
		{
			result = A|B;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode() == 1100 || OF_EX_Latch.getopcode() == 1101)
		{
			result = A^B;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode() == 1110 || OF_EX_Latch.getopcode() == 1111)
		{
			result = A<B?1:0;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode() == 10000 || OF_EX_Latch.getopcode() == 10001)
		{
			result = A<<B;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode() == 10010 || OF_EX_Latch.getopcode() == 10011)
		{
			result = A>>B;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode() == 10110|| OF_EX_Latch.getopcode() == 10111)
		{
			result = A+B;
			EX_MA_Latch.setAluResult(result);
		}
		if(OF_EX_Latch.getopcode()>=11001)
		{
			B=containingProcessor.getRegisterFile().getValue(OF_EX_Latch.getrd());	
		}
		//System.out.println("pc" + containingProcessor.getRegisterFile().getProgramCounter());
		//Initialise BranchTaken to false
		EX_IF_Latch.setBranchTaken(false);
		//If it is a branch, update BranchTaken to true
		int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
		if(OF_EX_Latch.getopcode() == 11000) {
			//System.out.println("pc" + containingProcessor.getRegisterFile().getProgramCounter());
			
			String instr=OF_EX_Latch.getinstruction();
			String rd_str=instr.substring(5,10);
			int rd_int=Integer.parseInt(rd_str,2);
			//System.out.println(rd_int);
			String instr1=instr.substring(10,32);
			String new_instr="";
			int instruct=0;
			
			// String msb=instr.substring(10,11);
			// System.out.println(msb);
			if(instr.charAt(10)=='1')
			{
				//System.out.println("What the HELL!");
				for(int i=0;i<instr1.length();i++)
				{
					if(instr1.charAt(i)=='1')
					{
						new_instr=new_instr+"0";
					}
					else if(instr1.charAt(i)=='0')
					{
						new_instr=new_instr+"1";
					}
				}
				instruct=Integer.parseInt(new_instr,2);
				instruct++;
				instruct=0-instruct;
			}
			else if(instr.charAt(10)=='0')
			{
				instruct=Integer.parseInt(instr1,2);
			}
			System.out.println(new_instr);
			// int opcode= Integer.parseInt(NewInstruction.substring(0,5),2);
			
			// int rd_reg=OF_EX_Latch.getrd();
			containingProcessor.getRegisterFile().setProgramCounter(currentPC +rd_int+instruct-1);
			EX_IF_Latch.setBranchTaken(true);
			EX_MA_Latch.setMA_enable(false);
		} 
		else if(OF_EX_Latch.getopcode() == 11001 && (A==B)) {
			//System.out.println("pc" + containingProcessor.getRegisterFile().getProgramCounter());
			containingProcessor.getRegisterFile().setProgramCounter(currentPC + value-1);
			EX_IF_Latch.setBranchTaken(true);
			EX_MA_Latch.setMA_enable(false);
		}
		else if(OF_EX_Latch.getopcode() == 11010 && (A!=B)) {
			//System.out.println("pc" + containingProcessor.getRegisterFile().getProgramCounter());
			containingProcessor.getRegisterFile().setProgramCounter(currentPC + value-1);
			EX_IF_Latch.setBranchTaken(true);
			EX_MA_Latch.setMA_enable(false);
		}
		else if(OF_EX_Latch.getopcode() == 11011 && (A<B)) {
			//System.out.println("pc" + containingProcessor.getRegisterFile().getProgramCounter());
			containingProcessor.getRegisterFile().setProgramCounter(currentPC + value-1);
			EX_IF_Latch.setBranchTaken(true);
			//System.out.println("branch taken");
			//System.out.println(A+"''''''''''"+B);
			EX_MA_Latch.setMA_enable(false);
			
		} 
		else if(OF_EX_Latch.getopcode() == 11100 && (A>B)) {
			//System.out.println("pc" + containingProcessor.getRegisterFile().getProgramCounter());
			containingProcessor.getRegisterFile().setProgramCounter(currentPC + value-1);
			EX_IF_Latch.setBranchTaken(true);
			EX_MA_Latch.setMA_enable(false);
		} 
		if(EX_IF_Latch.getBranchTaken())
		{
			
			// int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
			// System.out.println(EX_IF_Latch.getBranchPC());
			// System.out.println("pc" + containingProcessor.getRegisterFile().getProgramCounter());
			// containingProcessor.getRegisterFile().setProgramCounter(currentPC + value +B-2);

			//System.out.println("rd" + B);
			//System.out.println("value" + value);
			//System.out.println("pc" + containingProcessor.getRegisterFile().getProgramCounter());
		}
		// System.out.println("rd in OF " + OF_EX_Latch.getrd());
		EX_MA_Latch.setOpCode(OF_EX_Latch.getopcode());
		EX_MA_Latch.setOperand2(OF_EX_Latch.getop2());
		EX_MA_Latch.setrd(OF_EX_Latch.getrd());
		OF_EX_Latch.setEX_enable(false);
		EX_MA_Latch.setMA_enable(true);
		

	}

}
