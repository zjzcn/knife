package com.chenjw.knife.agent.handler.log;

import com.chenjw.knife.agent.handler.log.listener.DefaultInvocationListener;
import com.chenjw.knife.utils.TimingHelper;

public class Profiler {
	private static final InvocationListener DEFAULT_LISTENER = new DefaultInvocationListener();
	public static InvocationListener listener = null;
	public static Thread checkThread = null;
	private static volatile boolean needLogging = true;

	public static void clear() {
		InvokeLogUtils.clear();
	}

	private static boolean canProfile() {
		return needLogging && isLogThread();
	}

	private static void enter() {
		needLogging = false;
		TimingHelper.stop();
	}

	private static void leave() {
		needLogging = true;
		TimingHelper.resume();
	}

	public static <T> void traceObject(T obj, String methodName) {
		// System.out.println("proxy " + methodName);
		if (obj == null) {
			return;
		}
		traceClass(obj.getClass(), methodName);
	}

	public static void traceClass(Class<?> clazz, String methodName) {
		// System.out.println("proxy " + methodName);
		if (clazz == null) {
			return;
		}
		trace(clazz, methodName);
	}

	/**
	 * add trace bytecode
	 * 
	 * @param dep
	 * @param clazz
	 */
	private static void trace(Class<?> clazz, String methodName) {
		if (!canProfile()) {
			return;
		}
		try {
			enter();
			// System.out.println("trace " + clazz.getName() + "." +
			// methodName);
			InvokeLogUtils.buildTraceMethod(clazz, methodName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			leave();
		}

	}

	public static void start(Object thisObject, String className,
			String methodName, Object[] arguments) {
		if (!canProfile()) {
			return;
		}
		try {
			enter();
			InvocationListener listener = getListener();
			listener.onStart(thisObject, className, methodName, arguments);
		} finally {
			leave();
		}
	}

	public static void returnEnd(Object thisObject, String className,
			String methodName, Object[] arguments, Object result) {
		if (!canProfile()) {
			return;
		}
		try {
			enter();
			InvocationListener listener = getListener();
			listener.onReturnEnd(thisObject, className, methodName, arguments,
					result);
		} finally {
			leave();
		}
	}

	public static void exceptionEnd(Object thisObject, String className,
			String methodName, Object[] arguments, Throwable e) {
		if (!canProfile()) {
			return;
		}
		try {
			enter();
			InvocationListener listener = getListener();
			listener.onExceptionEnd(thisObject, className, methodName,
					arguments, e);
		} finally {
			leave();
		}
	}

	private static boolean isLogThread() {
		if (checkThread == null) {
			return true;
		} else {
			if (checkThread == Thread.currentThread()) {
				return true;
			}
		}
		return false;
	}

	private static InvocationListener getListener() {
		InvocationListener listener = null;
		if (Profiler.listener != null) {
			listener = Profiler.listener;
		} else {
			listener = Profiler.DEFAULT_LISTENER;
		}
		return listener;
	}
}