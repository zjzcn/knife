package com.chenjw.knife.client;

public class ClosePacket extends ObjectPacket<Object> {
	static final byte CODE = 2;

	public ClosePacket() {
		super();
	}

	@Override
	public byte getCode() {
		return CODE;
	}

}
