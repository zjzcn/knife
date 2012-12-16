package com.chenjw.knife.client.client;

import java.util.List;

import com.chenjw.knife.client.constants.Constants;
import com.chenjw.knife.client.core.Client;
import com.chenjw.knife.client.core.CommandService;
import com.chenjw.knife.client.core.VMConnection;
import com.chenjw.knife.client.core.VMConnector;
import com.chenjw.knife.client.model.VMDescriptor;
import com.chenjw.knife.core.Command;
import com.chenjw.knife.core.Packet;
import com.chenjw.knife.core.packet.ClosePacket;
import com.chenjw.knife.core.packet.CommandPacket;
import com.chenjw.knife.core.packet.ResultPacket;
import com.chenjw.knife.core.result.Result;

public class CommandClient implements Client {

	private volatile boolean isRunning = false;

	private CommandService commandService;

	public CommandClient(CommandService handler) {
		this.commandService = handler;
	}

	public void start(VMConnector connector) throws Exception {

		// 获得虚拟机列表
		List<VMDescriptor> vmList = connector.listVM();
		if (vmList.isEmpty()) {
			commandService.handleText("cant find vm process!");
			return;
		}
		for (int i = 0; i < vmList.size(); i++) {
			commandService.handleText(i + ". " + vmList.get(i).getName());
		}
		String msg = "input [0-" + (vmList.size() - 1) + "] to choose vm! ";
		commandService.handleText(msg);
		int n = 0;
		while (true) {
			n = commandService.waitVMIndex();
			if (n < 0 || n >= vmList.size()) {
				commandService.handleText(msg);
				continue;
			}
			break;
		}
		connector.attachVM(vmList.get(n).getId(), Constants.DEFAULT_AGENT_PORT);
		VMConnection conn = connector
				.createVMConnection(Constants.DEFAULT_AGENT_PORT);
		isRunning = true;
		// 获取命令列表
		Command c = new Command();
		c.setName("cmd");
		conn.sendPacket(new CommandPacket(c));
		startPacketReader(conn);
		startPacketSender(conn);
		while (isRunning) {
			Thread.sleep(3000);
		}
	}

	private void startPacketReader(VMConnection conn) {
		Thread t = new Thread(new PacketReader(conn), "knife-packet-reader");
		t.setDaemon(true);
		t.start();
	}

	private void startPacketSender(VMConnection conn) {
		Thread t = new Thread(new PacketSender(conn), "knife-packet-sender");
		t.setDaemon(true);
		t.start();
	}

	private class PacketSender implements Runnable {
		private VMConnection conn;

		public PacketSender(VMConnection conn) {
			this.conn = conn;
		}

		@Override
		public void run() {
			try {
				while (isRunning) {
					Command c = commandService.waitCommand();
					conn.sendPacket(new CommandPacket(c));
				}
			} catch (Exception e) {
			}
		}
	}

	private class PacketReader implements Runnable {
		private VMConnection conn;

		public PacketReader(VMConnection conn) {
			this.conn = conn;
		}

		@Override
		public void run() {
			try {
				while (isRunning) {
					Packet p = conn.readPacket();
					if (p instanceof ClosePacket) {
						conn.close();
						commandService.close();
						isRunning = false;
					} else if (p instanceof ResultPacket) {
						Result r = ((ResultPacket) p).getObject();
						commandService.handleResult(r);

					} else {
						commandService.handleText(p.toString());
					}

				}
			} catch (Exception e) {
			}
		}
	}

}