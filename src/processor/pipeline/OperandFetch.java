package processor.pipeline;

import processor.Processor;

public class OperandFetch {
	Processor containingProcessor;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;
	
	public OperandFetch(Processor containingProcessor, IF_OF_LatchType iF_OF_Latch, OF_EX_LatchType oF_EX_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.IF_OF_Latch = iF_OF_Latch;
		this.OF_EX_Latch = oF_EX_Latch;
	}
	
	public void performOF()
	{
		if(IF_OF_Latch.isOF_enable())
		{
			//TODO
			Integer new_instruction=IF_OF_Latch.getInstruction(),pc=containingProcessor.getRegisterFile().getProgramCounter();
			String NewInstruction= Integer.toBinaryString(new_instruction);
			System.out.println(NewInstruction);
			if(new_instruction  >= 0 )
			{
                NewInstruction = String.format("%32s", Integer.toBinaryString(new_instruction)).replace(' ', '0');
            }
			int opcode= Integer.parseInt(NewInstruction.substring(0,5),2);
			int rs1= Integer.parseInt(NewInstruction.substring(5,10),2);
			int rs2= Integer.parseInt(NewInstruction.substring(10,15),2);

			System.out.println(NewInstruction);

			int op1=containingProcessor.getRegisterFile().getValue(rs1);
			int op2=containingProcessor.getRegisterFile().getValue(rs2);
			int rd=0;
			int imm= Integer.parseInt(NewInstruction.substring(15,32),2); // immediate for R2I type
			int imm_jmp= Integer.parseInt(NewInstruction.substring(10,32),2); // immediate for RI type for jump conditions

			if((opcode & 1)==0 && ((opcode>>>4)==0 || (opcode>>>3)!=3)) // R3 type
			{
				rd=(Integer.parseInt(NewInstruction.substring(15,20),2));
				// rd=rd&31;
			}
			else if(opcode==24 || opcode==29) // RI type
			{
				rd=Integer.parseInt(NewInstruction.substring(5,10),2);
				// imm=imm_jmp;
			}
			else // R2I type
			{
				// System.out.println("Hello");
				rd=Integer.parseInt(NewInstruction.substring(10,15),2);
				imm_jmp=Integer.parseInt(NewInstruction.substring(15,32),2);
			}
			if(opcode==23 || opcode==22) //load and store
			{
				rd=Integer.parseInt(NewInstruction.substring(10,15),2);
			}

			// System.out.println(imm_jmp);
			
			int opcode1=Integer.parseInt(Integer.toBinaryString(opcode));
			//System.out.println(opcode1);
			OF_EX_Latch.setopcode(opcode1);
			OF_EX_Latch.setpc(pc);
			OF_EX_Latch.setop1(op1);
			OF_EX_Latch.setop2(op2);
			OF_EX_Latch.setimmx(imm);
			OF_EX_Latch.setbranchTarget(imm_jmp);
			OF_EX_Latch.setrd(rd);
			OF_EX_Latch.setinstruction(NewInstruction);
			
			IF_OF_Latch.setOF_enable(false);
			OF_EX_Latch.setEX_enable(true);
		}
	}

}
