package com.gss.gmo.cao.integration.xmpp.component.service;

import java.util.LinkedList;
import java.util.List;

import org.xmpp.packet.IQ;

public class IQProcessorChain implements IQProcessor {

	private LinkedList<IQProcessor> iqProcessors = new LinkedList<IQProcessor>();

	public IQProcessorChain(List<IQProcessor> iqProcessors) {
		for (IQProcessor iqProcessor : iqProcessors) {
			if (!this.iqProcessors.isEmpty()) {
				this.iqProcessors.getLast().setSuccessor(iqProcessor);
			}
			this.iqProcessors.add(iqProcessor);
		}

		IQProcessor safetyNetIQProcessor = new SafetyNetIQProcessor();
		if (!this.iqProcessors.isEmpty()) {
			this.iqProcessors.getLast().setSuccessor(safetyNetIQProcessor);
		}
		this.iqProcessors.add(safetyNetIQProcessor);
	}

	@Override
	public IQ process(IQ iq) {
		return iqProcessors.getFirst().process(iq);
	}

	@Override
	public void setSuccessor(IQProcessor successor) {
		throw new IllegalAccessError("Not Implemented.");
	}

}
