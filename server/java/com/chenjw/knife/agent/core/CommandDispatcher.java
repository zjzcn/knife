package com.chenjw.knife.agent.core;

import java.util.Map;

import com.chenjw.knife.agent.args.ArgDef;
import com.chenjw.knife.core.Command;

public interface CommandDispatcher {
	public void dispatch(Command command);
	
	public Map<String, ArgDef> getArgDefMap();
}
